SHELL := /bin/bash
.ONE_SHELL:
.SILENT:

.EXPORT_ALL_VARIABLES:
PROJECT_NAME=ap-starter-model
MAVEN_OPTS = -Drevision=1.0.0
COMPOSE_DOCKER_CLI_BUILD = 1
DOCKER_BUILDKIT = 1
COMPOSE_FILES = -f ./util/docker/docker-compose.yml -f
# K8S-DEBUG
# KUBECONFIG=$${HOME}/.dasa/kube-config-dev.yaml

## Usage: make [target]
##
## targets:
##
## ---
## [help] show this help :)
.PHONY : help
help : Makefile
	sed -n 's/^##//p' $<
##
## > Maven (dev mode)
##

## [build-quick] build the apps, but skip the tests
build-quick:
	./mvnw install -Dmaven.test.skip=true

## [build] build the apps with all tests
build:
	./mvnw clean install -ff

## [local-sonar-start] build and collect metrics to a local sonar (http://localhost:9000)
local-sonar-start: local-sonar-stop build
	. ./util/functions.sh; \
	localSonarStart

## [local-sonar-stop] stop local sonar
local-sonar-stop:
	. ./util/functions.sh; \
	localSonarStop

##
## > Run apps (dev mode)
##

## [run] run app
run: compose-up build-quick
	SPRING_PROFILES_ACTIVE=test java -jar target/$$PROJECT_NAME.jar

##
## > Docker Compose
##

## [compose-down] stop all containers
compose-down:
	docker-compose $$COMPOSE_FILES down --remove-orphans --volumes

## [compose-up] start all containers
compose-up:
	docker network prune -f
	docker-compose $$COMPOSE_FILES up -d --remove-orphans
	. ./util/functions.sh;

##
## > CI/CD (Jenkins)
##

## [ci] build the app, run all tests and quality metrics
ci: build ci-sonar

## [cd] build the images and deploy the apps on k8s
cd:
    JAR_PATH="./target/ap-exams-api.jar"; \
	. ./util/functions.sh; \
	k8sDeploy

# [ci-sonar] Collect and upload metrics to Sonar
ci-sonar:
	./mvnw sonar:sonar