FROM maven:3.9.5-eclipse-temurin-11 AS builder
WORKDIR /app2/backend
COPY pom.xml /app2/backend/pom.xml

COPY src /app2/backend/src
RUN mvn install

CMD ["mvn", "spring-boot:run"]