FROM maven:3.6.3-jdk-8-slim AS build

EXPOSE 8080

ADD target/java-apply-api-0.0.1-SNAPSHOT.jar java-apply-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","java-apply-api-0.0.1-SNAPSHOT.jar"]