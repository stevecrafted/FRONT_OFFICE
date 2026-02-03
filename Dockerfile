FROM tomcat:9.0-jdk11

# Copier le WAR dans webapps
COPY target/SpringInitTest-1.0.war /usr/local/tomcat/webapps/

# Exposer le port 8080
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]