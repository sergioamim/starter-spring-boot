#!/bin/bash
waitForContainer() {
  end=30
  for i in $(eval echo "{1..$end}"); do
    echo "[${i}/${end}] waiting $1"
    if [ $(docker ps --filter "health=healthy" --filter "publish=$2" -q) ]; then
      echo "$1 is UP"
      return
    fi
    sleep 5
  done
  echo "FATAL ERROR: '$1' is not avaliable"
  exit 1
}
waitForUrlHealthCheck(){
  end=30
  for i in $(eval echo "{1..$end}"); do
    curl --fail --silent "$2" | grep -q '"UP"' && return;
    echo "[${i}/${end}] waiting $1"
    sleep 5
  done
  echo "FATAL ERROR: '$1' is not avaliable"
  exit 1
}

ensureVarIsNotEmpty(){
  for i in "$@"; do
    if [ -z "${!i}" ]; then
      echo "FATAL ERROR: '$i' is required!"
      exit 1
    fi;
  done
}
ensureFileExists(){
  for i in "$@"; do
    if [ ! -f "${i}" ]; then
      echo "FATAL ERROR: '$i' file cannot be found!"
      exit 1
    fi;
  done
}
localSonarStart(){
  docker network prune -f
  docker-compose -f ./util/docker/docker-compose.yml -f ./util/docker/sonar.docker-compose.yml up -d
  waitForUrlHealthCheck "Local Sonar" "http://127.0.0.1:9000/api/system/status"
  ./mvnw sonar:sonar
}

localSonarStop(){
  docker-compose -f ./util/docker/docker-compose.yml -f ./util/docker/sonar.docker-compose.yml down --remove-orphans --volumes
  docker network prune -f
}
azDockerAuth(){
  ensureVarIsNotEmpty "AZURE_APP_ID" "AZURE_APP_PASS" "AZURE_TENANT_ID"
  az login -u $AZURE_APP_ID -p $AZURE_APP_PASS --service-principal --tenant $AZURE_TENANT_ID
  az acr login --name imagesdasa
}
resolveDockerImage(){
  ensureVarIsNotEmpty "DOCKER_REPOSITORY" "ENVIRONMENT" "PROJECT_NAME" "DOCKER_REVISION"
  export DOCKER_IMAGE="$DOCKER_REPOSITORY/$ENVIRONMENT/$PROJECT_NAME:$DOCKER_REVISION"
  export DOCKER_IMAGE_LATEST="$DOCKER_REPOSITORY/$ENVIRONMENT/$PROJECT_NAME:latest"
}
dockerBuild(){
  ensureVarIsNotEmpty "JAR_PATH" "DOCKER_IMAGE"
  ensureFileExists "${JAR_PATH}"

  docker build -t "${DOCKER_IMAGE}" \
    --build-arg jar="${JAR_PATH}" \
    -f ./util/docker/images/app.Dockerfile .
  if [ -n "${DOCKER_IMAGE_LATEST}" ]; then
    docker tag "${DOCKER_IMAGE}" "${DOCKER_IMAGE_LATEST}"
  fi;
}
dockerPush(){
  ensureVarIsNotEmpty "DOCKER_IMAGE"

  docker push "${DOCKER_IMAGE}"
  if [ -n "${DOCKER_IMAGE_LATEST}" ]; then
    docker push "${DOCKER_IMAGE_LATEST}"
  fi;
}
k8sLoadVars(){
  ensureVarIsNotEmpty "ENVIRONMENT" "PROJECT_GROUP" "PROJECT_NAME"

  DEFAULT_ENV_FILE="./util/pipeline/k8s/${PROJECT_NAME}/env/default.env"
  ENV_FILE="./util/pipeline/k8s/${PROJECT_NAME}/env/${ENVIRONMENT}.env"
  ensureFileExists "${DEFAULT_ENV_FILE}" "${ENV_FILE}"

  set -o allexport;
  . "${DEFAULT_ENV_FILE}"
  . "${ENV_FILE}"
  set +o allexport
}
k8sApply(){
  ensureVarIsNotEmpty "KUBECONFIG"

  K8S_TEMPLATE_FILE="./util/pipeline/k8s/app-template.yaml"
  ensureFileExists "${K8S_TEMPLATE_FILE}" "${KUBECONFIG}"

  # pq insecure-skip-tls-verify?
  # exigência COETECH: https://jiradasa.atlassian.net/wiki/spaces/COET/pages/2715812190/NOVO+-+Clusters+com+Dynatrace
  envsubst < "${K8S_TEMPLATE_FILE}" | kubectl apply --insecure-skip-tls-verify -f -
}
k8sDeploy(){
  set -e
  azDockerAuth
  resolveDockerImage
  dockerBuild
  dockerPush
  k8sLoadVars
  k8sApply
}