FROM openjdk:8-jre-slim

COPY target/*.jar java-server.jar
ENTRYPOINT ["java","-jar","java-server.jar"]
