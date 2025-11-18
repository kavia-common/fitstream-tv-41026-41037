# Android TV Frontend - Gradle Wrapper Setup

CI reported:
```
Error: bash: line 1: ./gradlew: No such file or directory
```

This indicates the Android TV app container is missing the Gradle wrapper scripts. To fix:

1) Generate Gradle wrapper (one-time, requires Gradle locally or via container):
   - If you have Gradle installed, run in the android_tv_fitness_frontend root:
     ```
     gradle wrapper
     ```
   - Or use the Gradle Docker image:
     ```
     docker run --rm -v "$PWD":/work -w /work gradle:8.7-jdk17 gradle wrapper
     ```

2) Commit the following generated files:
   - gradlew
   - gradlew.bat
   - gradle/wrapper/gradle-wrapper.jar
   - gradle/wrapper/gradle-wrapper.properties

3) Ensure gradlew is executable:
   ```
   chmod +x ./gradlew
   ```

4) CI should then run:
   ```
   ./gradlew clean test
   ```
   or your configured Gradle commands.

Notes:
- If mobile checks are not in scope for your change, temporarily skip mobile Gradle checks in CI to unblock backend-only changes.
- Keep Gradle version aligned with your Android Gradle Plugin requirements.
