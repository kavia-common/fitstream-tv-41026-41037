# Skip Mobile Build

The Android TV mobile workspace does not yet contain a complete Android project or Gradle wrapper.

CI/CD systems should skip running Gradle tasks in this workspace until the Android project is scaffolded
and the Gradle wrapper (`./gradlew`) is present.

Suggested CI condition (pseudo):
- if file `SKIP_MOBILE_BUILD.md` exists OR `./gradlew` is not present -> skip mobile build steps.

Action items to enable mobile build:
1) Scaffold Android TV project (Android Studio or CLI).
2) Ensure the Gradle wrapper is generated and committed (`./gradlew`, `gradlew.bat`, `gradle/wrapper/*`).
3) Remove this file once mobile build steps are active.
