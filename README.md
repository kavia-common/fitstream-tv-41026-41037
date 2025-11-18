# Android TV Fitness Frontend

This workspace currently does not contain a full Android project and is missing the Gradle wrapper (`./gradlew`).
As a result, CI checks that attempt to run `./gradlew` will fail with:

```
bash: line 1: ./gradlew: No such file or directory
```

Next steps to resolve:
- Scaffold an Android project with the Gradle wrapper:
  - Using Android Studio: Create a new Android TV project and copy the generated files here.
  - Or via CLI: `gradle wrapper` after initializing a Gradle project, which generates `./gradlew` and associated files.
- Alternatively, adjust CI temporarily to skip mobile checks until the Android project is added.

This note is informational and does not affect the backend build, which now imports successfully.
