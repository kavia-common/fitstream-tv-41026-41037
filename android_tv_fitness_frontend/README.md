# Android TV Fitness Frontend

A minimal Android TV app using Leanback to avoid a blank screen on launch. The app launches into a BrowseSupportFragment showing a simple "Featured" row of cards, ensuring the preview renders visible content immediately.

What was fixed:
- MainActivity inflates a layout and attaches HomeFragment (Leanback) so startup is not blank.
- AndroidManifest uses a TV-appropriate theme (Theme.Leanback), sets banner and icon, and declares the LEANBACK_LAUNCHER category.
- Added a minimal HomeFragment with a BrowseSupportFragment and sample cards.
- Gradle configuration consolidated to build.gradle.kts (removed duplicate Groovy build file).
- Min SDK set to 26 and dependencies updated (Leanback, Fragment KTX, etc.).

Build locally (CI-friendly):
- From repository root, run:
  - sh fitstream-tv-41026-41037/ci-run-android-gradle.sh :app:assembleDebug

Notes:
- Java 17 is required (AGP 8.x).
- If your CI environment restricts network egress and gradle-wrapper.jar cannot be fetched, commit the jar into gradle/wrapper.
- The HomeFragment is a placeholder; replace with actual TV UI (e.g., recommendations, workouts, categories) as you build features.
