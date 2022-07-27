#!groovy

//FORTIFY - Params - DOCS: https://jiradasa.atlassian.net/wiki/spaces/COET/pages/1567883292/Fortify+no+Pipeline
//****************
env.TYPE_COMPILE_LANG = "mvn" //options: mvn / gradlew / npm / bar / python / php.  Ref: https://bitbucket.org/dasa_desenv/sec-fortify/src/master/bin/languages.reference
env.DASA_FORTIFY_DATA = "dados_sensiveis_medicos" //options: sem_dados_sensiveis, dados_sensiveis_medicos, dados_sensiveis_pessoais, dados_sensiveis_outros
env.DASA_FORTIFY_ACCESSIBILITY = "internalnetwork" //options: internalnetwork, externalpublicnetwork
env.DASA_FORTIFY_EXCLUSIONS = "util/" // Especificar os arquivos (ex: folder/example.js) ou pastas (ex.: example/) que devem ser excluídas do scaneamento (separadas por virgula ','). Wildcards aceitos (ex.: */.test.js).
//****************
//FORTIFY - Report Params
DASA_FORTIFY_REPORT_RUN = false
DASA_FORTIFY_REPORT_CRITICAL_ISSUE_FAIL_BUILD = false
//****************
//DON'T CHANGE BELOW
env.DASA_FORTIFY_DEV_PHASE = "Active"
env.DASA_FORTIFY_URL = "http://${PROJECT_NAME}.azr-apoio-prd.dasaexp.io"
env.DASA_FORTIFY_PRODUCT = "Apoio"
env.REVISION = "${env.GIT_COMMIT[0..7]}"
env.DASA_FORTIFY_VERSION = env.ENVIRONMENT ?: "dev"
env.DASA_FORTIFY_VERSION = (env.DASA_FORTIFY_VERSION == "prd") ? "1.0.0" : env.DASA_FORTIFY_VERSION
//
echo "Checking out Fortify..."
dir('devops/fortify') {
    git url: "git@bitbucket.org:dasa_desenv/sec-fortify.git", poll: false
}
withCredentials([
        string(credentialsId: 'dasa_fortify_token', variable: 'DASA_FORTIFY_TOKEN'),
        string(credentialsId: 'FORTIFY_CLIENT_TOKEN', variable: 'FORTIFY_CLIENT_TOKEN'),
        string(credentialsId: 'PRISMA_KEY', variable: 'PRISMA_KEY'),
        string(credentialsId: 'PRISMA_SECRET', variable: 'PRISMA_SECRET'),
        string(credentialsId: 'NEXUS_IQ_TOKEN', variable: 'NEXUS_IQ_TOKEN')
]) {
    echo "Starting Fortify Regular Scanner..."
    sh "$WORKSPACE/devops/fortify/fortify.sh -s"
    if(DASA_FORTIFY_REPORT_RUN) {
        echo "Starting Fortify Reporting..."
        REPORT_FORTIFY = sh(returnStdout: true, script: "$WORKSPACE/devops/fortify/fortify.sh -r")
        echo "$REPORT_FORTIFY"
        if (DASA_FORTIFY_REPORT_CRITICAL_ISSUE_FAIL_BUILD && !REPORT_FORTIFY.contains("- 0 Criticas (Novas)")) {
            currentBuild.result = 'FAILURE'
            slackSend(color: '#FF0000', message: "Error on build  ${env.BUILD_URL} due to critical security issues (Fortify)", channel: "${env.SLACK_CHANNEL}")
            error("Build failed because critical security issues detected in Fortify Report")
        }
    }
}