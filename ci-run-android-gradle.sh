#!/usr/bin/env sh
# CI entrypoint to run Gradle tasks for the Android frontend without requiring ./gradlew to be executable.
# Usage examples:
#   sh fitstream-tv-41026-41037/ci-run-android-gradle.sh help
#   sh fitstream-tv-41026-41037/ci-run-android-gradle.sh tasks
#   sh fitstream-tv-41026-41037/ci-run-android-gradle.sh :app:assemble
set -eu

BASE_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
APP_DIR="$BASE_DIR/android_tv_fitness_frontend"

if [ ! -d "$APP_DIR" ]; then
  echo "Android frontend directory not found at $APP_DIR" >&2
  exit 1
fi

exec sh "$APP_DIR/run-gradle.sh" "$@"
