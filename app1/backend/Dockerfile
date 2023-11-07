FROM maven:3.9.5-eclipse-temurin-11 AS builder
WORKDIR /app1/backend
COPY pom.xml /app1/backend/pom.xml

COPY src /app1/backend/src
RUN mvn install

CMD ["mvn", "spring-boot:run"]