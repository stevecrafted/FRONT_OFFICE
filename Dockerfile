# Build stage
FROM maven:3.9-openjdk-8 AS build

WORKDIR /app

# Copy pom.xml first for better Docker layer caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:8-jre-slim

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/spring-init-test-1.0.0.jar app.jar

# Copy webapp directory
COPY --from=build /app/src/main/webapp ./webapp

# Expose port (Koyeb will set PORT env var)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:${PORT:-8081}/ || exit 1

# Run the application
CMD ["java", "-jar", "app.jar"]