FROM openjdk:8-jdk-alpine
# /tmp is where a Spring Boot application creates working directories for Tomcat by default. Uncomment this line if you would like to write to the file system.
#VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]