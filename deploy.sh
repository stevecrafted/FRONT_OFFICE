#!/bin/bash

echo "======================================"
echo "   DEPLOIEMENT TEST TOMCAT"
echo "======================================"

# Configuration
WAR_NAME="SpringInitTest-1.0.war"
CATALINA_HOME="/opt/tomcat"
WEBAPPS_DIR="$CATALINA_HOME/webapps"

echo ""
echo "1. Mise a jour du framework parent..."
cd ../FRAMEWORK_Railway
mvn clean install -q
if [ $? -ne 0 ]; then
    echo "ERREUR: Echec de la compilation du framework"
    exit 1
fi

echo ""
echo "2. Compilation du TEST..."
cd ../TEST
mvn clean package -q
if [ $? -ne 0 ]; then
    echo "ERREUR: Echec de la compilation du TEST"
    exit 1
fi

echo ""
echo "3. Deploiement vers Tomcat..."
if [ -f "target/$WAR_NAME" ]; then
    cp "target/$WAR_NAME" "$WEBAPPS_DIR/"
    echo "WAR deploie avec succes!"
else
    echo "ERREUR: WAR non trouve"
    exit 1
fi

echo ""
echo "4. Redemarrage Tomcat..."
sudo systemctl stop tomcat 2>/dev/null
sleep 2
sudo systemctl start tomcat 2>/dev/null

echo "======================================"
echo "DEPLOIEMENT TERMINE!"
echo "http://localhost:8080/SpringInitTest-1.0/"
echo "======================================"