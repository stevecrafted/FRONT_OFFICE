@echo off
setlocal enabledelayedexpansion

call mvn clean package
call java -jar target/spring-init-test-1.0.0.jar

endlocal