# CI Usage for Android TV Fitness Frontend

If your CI pipeline tries to run `./gradlew` directly and fails with:
```
bash: line 1: ./gradlew: No such file or directory
```
use the provided CI-friendly wrapper script instead:

From repository root:
```
sh fitstream-tv-41026-41037/ci-run-android-gradle.sh help
```

Common tasks:
- List tasks:
  ```
  sh fitstream-tv-41026-41037/ci-run-android-gradle.sh tasks
  ```
- Build app:
  ```
  sh fitstream-tv-41026-41037/ci-run-android-gradle.sh :app:assemble
  ```

Notes:
- The script will bootstrap the Gradle wrapper jar via the distribution URL if it is missing.
- If your CI restricts network access, you may need to commit `gradle/wrapper/gradle-wrapper.jar`.
- Alternatively, ensure your CI step sets execute permission on `gradlew`:
  ```
  chmod +x fitstream-tv-41026-41037/android_tv_fitness_frontend/gradlew
  ```
