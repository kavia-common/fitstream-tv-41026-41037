# Android TV Fitness Frontend

This module is currently a placeholder and does not contain a full Android project yet.

CI note:
- The Gradle wrapper (./gradlew, gradlew.bat and gradle/wrapper/*) is not present, so any Gradle-based checks will fail.
- To enable CI checks for this module, initialize a proper Android project here and include the Gradle wrapper files, or adjust CI to skip this module until initialization.

Suggested steps to initialize:
1. Create an Android project in this directory with Android Studio or `gradle init`.
2. Ensure these files exist and are committed:
   - `gradlew`, `gradlew.bat`
   - `gradle/wrapper/gradle-wrapper.properties`
   - `gradle/wrapper/gradle-wrapper.jar`
3. Configure module `app/` with proper package, minSdk, targetSdk.
4. Add baseline tests so `./gradlew check` can pass.

Backend status:
- The backend CORS parsing has been hardened to handle empty and non-JSON env values safely.
