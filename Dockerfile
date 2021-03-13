# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

MAINTAINER Valentin Ivanov <valyo95@abv.bg>

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]