@echo off
setlocal enabledelayedexpansion

echo ======================================
echo   DEPLOIEMENT TEST APPLICATION
echo ======================================

REM Configuration
set TOMCAT_WEBAPPS=C:\xampp\tomcat\webapps
set JAR_NAME=spring-init-test-1.0.0.jar

echo.
echo 1. Compilation du framework...
cd ..\BackOffice\FRAMEWORK_Railway
call mvn clean install -DskipTests -q
if errorlevel 1 (
    echo ERREUR: Echec du framework
    pause
    exit /b 1
)

echo.
echo 2. Compilation du TEST...
cd ..\TEST
call mvn clean package -q
if errorlevel 1 (
    echo ERREUR: Echec du TEST
    pause
    exit /b 1
)

echo.
echo 3. Lancement de l'application...
if exist "target\%JAR_NAME%" (
    echo Application demarre sur http://localhost:8080
    java -jar "target\%JAR_NAME%"
) else (
    echo ERREUR: JAR non trouve
    pause
    exit /b 1
)

mvn clean package
java -jar target/spring-init-test-1.0.0.jar