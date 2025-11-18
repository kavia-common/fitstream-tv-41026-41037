# Android TV Build Notes

If CI tries to run `./gradlew` directly and fails due to permissions or wrapper JAR bootstrap, use the CI helper:

From repository root:
- sh fitstream-tv-41026-41037/ci-run-android-gradle.sh :app:assembleDebug

This script will bootstrap the Gradle wrapper JAR if missing using the configured distribution URL.
Ensure Java 17 is available for AGP 8.x.
