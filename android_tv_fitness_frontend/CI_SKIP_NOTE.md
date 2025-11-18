# CI Note: Gradle wrapper missing

Mobile CI failed with:
```
bash: line 1: ./gradlew: No such file or directory
```

This repository currently does not include the Gradle wrapper for the Android TV app.
Until the wrapper is added (gradlew, gradlew.bat, gradle/wrapper/*), mobile Gradle checks will fail.

Action options:
- Add Gradle wrapper files (recommended) and make ./gradlew executable.
- Or configure CI to skip mobile checks for now when validating backend-only changes.

Backend status:
- fitness_api_backend imports successfully (settings parsing hardened for CORS).
