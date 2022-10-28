FROM amazoncorretto:17-alpine-jdk as builder
WORKDIR application
ARG JAR_FILE=build/libs/usuarios-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar", "application.jar"]