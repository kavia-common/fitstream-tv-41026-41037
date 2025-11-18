#!/usr/bin/env sh
# Portable delegating Gradle wrapper for CI environments that may strip executable bits.
# This script is intentionally compatible with `sh ./gradlew` invocation.
# It delegates to the module's gradle wrapper if present, otherwise falls back to system gradle.

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
MODULE_WRAPPER="$ROOT_DIR/android_tv_fitness_frontend/gradlew"

# Prefer module wrapper if present
if [ -f "$MODULE_WRAPPER" ]; then
  # Use sh to execute in case executable bit is not set
  exec sh "$MODULE_WRAPPER" "$@"
fi

# Fallback to system gradle
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi

echo "Error: No module gradle wrapper or system gradle available."
exit 127
