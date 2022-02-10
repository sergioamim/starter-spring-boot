FROM amazoncorretto:11-alpine

ARG elastic_version=1.28.1
ADD "https://repo1.maven.org/maven2/co/elastic/apm/elastic-apm-agent/${elastic_version}/elastic-apm-agent-${elastic_version}.jar" elastic-apm-agent.jar

ARG jar
COPY ${jar} app.jar

ENTRYPOINT java \
    -javaagent:elastic-apm-agent.jar \
    -jar app.jar