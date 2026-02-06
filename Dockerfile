# Dockerfile
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copier le pom.xml et installer le framework local
COPY pom.xml .
COPY src/main/webapp/WEB-INF/lib/spring-init-framework-1.0.0.jar /tmp/framework.jar

RUN mvn install:install-file \
    -Dfile=/tmp/framework.jar \
    -DgroupId=com.framework \
    -DartifactId=spring-init-framework \
    -Dversion=1.0.0 \
    -Dpackaging=jar

# Copier le code source
COPY src ./src

# Compiler l'application
RUN mvn clean package -DskipTests

# ============================================
# Stage 2: Runtime
# ============================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le JAR compilé
COPY --from=build /app/target/spring-init-test-1.0.0.jar app.jar

# Copier le répertoire webapp pour les JSP
COPY src/main/webapp ./src/main/webapp

# Exposer le port
EXPOSE 8080

# Démarrer l'application
CMD ["java", "-jar", "app.jar"]
 