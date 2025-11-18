@ECHO OFF
REM Delegating Gradle wrapper for CI: forwards to module's gradlew

SET MODULE_DIR=%~dp0android_tv_fitness_frontend
SET WRAPPER=%MODULE_DIR%\gradlew.bat

IF EXIST "%WRAPPER%" (
    CALL "%WRAPPER%" %*
    EXIT /B %ERRORLEVEL%
) ELSE (
    ECHO Error: Gradle wrapper not found at %WRAPPER%
    EXIT /B 127
)
