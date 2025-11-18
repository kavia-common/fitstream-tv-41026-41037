# Android TV Fitness Frontend

This directory is intended for the Android TV app frontend. The current CI error:
```
bash: line 1: ./gradlew: No such file or directory
```
indicates that the Gradle wrapper is missing from this project. The backend changes are unrelated to this error.

To initialize the Android project locally and add the Gradle wrapper:

1) Create the Android project structure (if not yet created) using Android Studio or `gradle init`:
   - Recommended: Use Android Studio to create a new Android TV project (Empty Activity or TV Activity).
   - Ensure the project is placed at:
     fitstream-tv-41026-41037/android_tv_fitness_frontend

2) Generate/ensure the Gradle wrapper is committed:
   - If using Gradle directly:
     ```
     gradle wrapper
     ```
     This will create:
     - ./gradlew
     - ./gradlew.bat
     - ./gradle/wrapper/gradle-wrapper.jar
     - ./gradle/wrapper/gradle-wrapper.properties

3) CI Requirements:
   - CI expects to run `./gradlew` from this directory.
   - Make sure the wrapper files are present and executable (chmod +x gradlew).
   - Include a settings.gradle, build.gradle (or build.gradle.kts) and module-level build files (app/build.gradle).

4) If you intend to disable mobile build temporarily:
   - Adjust CI configuration to skip the frontend container until the Android project is fully bootstrapped.

Note:
- Do not commit secrets or keystores.
- For reproducible builds, lock Gradle and Android Gradle Plugin versions in gradle-wrapper.properties and build files.

Once the Android TV project is added and the wrapper is present, the CI “./gradlew” error will be resolved.
