@ECHO OFF
REM Gradle startup script for Windows

SET DIR=%~dp0
SET APP_HOME=%DIR%

SET WRAPPER_JAR=%APP_HOME%gradle\wrapper\gradle-wrapper.jar

IF EXIST "%WRAPPER_JAR%" (
    REM Use Gradle wrapper jar
) ELSE (
    ECHO Gradle wrapper JAR not found at %WRAPPER_JAR%
    ECHO Attempting to run system gradle if available...
    WHERE gradle >NUL 2>&1
    IF %ERRORLEVEL%==0 (
        gradle %*
        EXIT /B %ERRORLEVEL%
    ) ELSE (
        ECHO Error: Neither gradle wrapper JAR nor system gradle found.
        ECHO Please add gradle/wrapper files or install gradle.
        EXIT /B 127
    )
)

REM Use JAVA_HOME if set
IF DEFINED JAVA_HOME (
  SET "JAVACMD=%JAVA_HOME%\bin\java.exe"
) ELSE (
  SET "JAVACMD=java.exe"
)

SET WRAPPER_MAIN_CLASS=org.gradle.wrapper.GradleWrapperMain

"%JAVACMD%" -classpath "%WRAPPER_JAR%" %WRAPPER_MAIN_CLASS% %*
EXIT /B %ERRORLEVEL%
