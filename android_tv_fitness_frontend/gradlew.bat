@ECHO OFF
SETLOCAL
set DIR=%~dp0
set APP_BASE_DIR=%DIR%
set CLASSPATH=%APP_BASE_DIR%gradle\wrapper\gradle-wrapper.jar
set PROPS_FILE=%APP_BASE_DIR%gradle\wrapper\gradle-wrapper.properties
IF NOT EXIST "%CLASSPATH%" (
  for /f "usebackq tokens=1,* delims==" %%a in ("%PROPS_FILE%") do (
    if "%%a"=="distributionUrl" set DIST_URL=%%b
  )
  for /f "tokens=1,2 delims=!" %%a in ("%DIST_URL%") do set WRAPPER_URL=%DIST_URL:bin/=bin/gradle-wrapper.jar%
  powershell -Command "if (Get-Command curl.exe -ErrorAction SilentlyContinue) { curl -sSL %WRAPPER_URL% -o %CLASSPATH% } else { (New-Object Net.WebClient).DownloadFile('%WRAPPER_URL%', '%CLASSPATH%') }"
)
set JAVA_EXE=java
"%JAVA_EXE%" -Dorg.gradle.appname=gradlew -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
ENDLOCAL
