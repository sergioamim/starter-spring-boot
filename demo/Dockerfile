FROM adoptopenjdk/openjdk11:alpine
MAINTAINER Sergio Amim<sergioamim@gmail.com>
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]