FROM amazoncorretto:11-alpine
ARG jar
COPY ${jar} app.jar

ENTRYPOINT java \
    -jar app.jar