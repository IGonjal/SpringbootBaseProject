FROM openjdk:13-jdk-alpine
LABEL Author="Ismael Gonjal"

# Installing softare required
RUN apk update $$ apk upgrade
RUN apk add openjdk11

# Jar management
ARG JAR_FILE=target/SpringBootBase-*.jar
COPY ${JAR_FILE} app.jar
RUN chmod -R +r app.jar

# Network management
EXPOSE 8080/tcp

# Spring user and jar config
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENTRYPOINT ["java", "-jar", "/app.jar"]