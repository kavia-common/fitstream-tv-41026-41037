@ECHO OFF
REM -----------------------------------------------------------------------------
REM Gradle start up script for Windows
REM This is a minimal wrapper to bootstrap Gradle in CI.
REM -----------------------------------------------------------------------------

SETLOCAL

SET APP_HOME=%~dp0

IF NOT "%JAVA_HOME%"=="" (
  SET JAVA_EXE=%JAVA_HOME%\bin\java.exe
  IF EXIST "%JAVA_EXE%" GOTO javaFound
  ECHO ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
  EXIT /B 1
)

SET JAVA_EXE=java.exe

:javaFound

SET WRAPPER_JAR=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar
SET WRAPPER_PROPERTIES=%APP_HOME%\gradle\wrapper\gradle-wrapper.properties

IF NOT EXIST "%WRAPPER_PROPERTIES%" (
  ECHO gradle-wrapper.properties not found at %WRAPPER_PROPERTIES% 1>&2
  EXIT /B 1
)

IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO gradle-wrapper.jar missing; CI requires it committed. 1>&2
  EXIT /B 1
)

"%JAVA_EXE%" -jar "%WRAPPER_JAR%" %*
ENDLOCAL
