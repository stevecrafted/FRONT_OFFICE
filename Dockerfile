# Build stage
FROM maven:3.9.6-amazoncorretto-11 AS build

WORKDIR /app

# Copier le JAR du framework
COPY src/main/webapp/WEB-INF/lib/spring-init-framework-1.0.0.jar /app/lib/

# Installer le framework dans le repo local Maven
RUN mvn install:install-file \
    -Dfile=/app/lib/spring-init-framework-1.0.0.jar \
    -DgroupId=com.framework \
    -DartifactId=spring-init-framework \
    -Dversion=1.0.0 \
    -Dpackaging=jar

# Copier TOUT le projet
COPY . .

# FORCER la recompilation complète
RUN mvn clean compile package -DskipTests

# Runtime stage
FROM amazoncorretto:11-alpine

WORKDIR /app

# Copier le JAR construit
COPY --from=build /app/target/spring-init-test-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]