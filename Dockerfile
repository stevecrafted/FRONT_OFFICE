FROM amazoncorretto:11-alpine
WORKDIR /app
# Juste copier le JAR (construit localement)
COPY target/spring-init-test-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

