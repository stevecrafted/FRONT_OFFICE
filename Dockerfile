FROM maven:3.9.6-eclipse-temurin-11 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:11-jre

WORKDIR /app
ENV PORT=8080

COPY --from=build /app/target /app/target
COPY --from=build /app/src/main/webapp /app/src/main/webapp

EXPOSE 8080
CMD ["java", "-jar", "/app/target/spring-init-test-1.0.0.jar"]
