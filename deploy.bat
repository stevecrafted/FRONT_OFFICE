@echo off
setlocal enabledelayedexpansion

echo ======================================
echo   DEPLOIEMENT TEST TOMCAT
echo ======================================

REM Configuration des variables
set WAR_NAME=SpringInitTest-1.0.war
set TOMCAT_WEBAPPS=C:\xampp\tomcat\webapps

echo.
echo 1. Mise a jour du framework parent...
cd ..\FRAMEWORK_Railway
call mvn clean install -q
if errorlevel 1 (
    echo ERREUR: Echec de la compilation du framework
    pause
    exit /b 1
)

echo.
echo 2. Compilation du TEST...
cd ..\TEST
call mvn clean package -q
if errorlevel 1 (
    echo ERREUR: Echec de la compilation du TEST
    pause
    exit /b 1
)

echo.
echo 3. Deploiement vers Tomcat...
if exist "target\%WAR_NAME%" (
    copy "target\%WAR_NAME%" "%TOMCAT_WEBAPPS%\" /Y
    echo WAR deploie avec succes!
) else (
    echo ERREUR: WAR non trouve
    pause
    exit /b 1
)

echo.
echo 4. Redemarrage Tomcat...
call net stop Tomcat9 2>nul
timeout /t 2 /nobreak >nul
call net start Tomcat9 2>nul

echo ======================================
echo DEPLOIEMENT TERMINE!
echo http://localhost:8080/SpringInitTest-1.0/
echo ======================================