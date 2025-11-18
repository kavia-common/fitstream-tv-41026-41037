#!/usr/bin/env sh
# Portable Gradle bootstrap for CI environments where executable bit may be stripped.
# Prefer the delegating gradlew if present; otherwise fallback to module wrapper or system gradle.

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
DELEGATE="$ROOT_DIR/gradlew"
MODULE_WRAPPER="$ROOT_DIR/android_tv_fitness_frontend/gradlew"

if [ -f "$DELEGATE" ]; then
  # Call the delegating wrapper using sh to avoid exec permissions issues
  exec sh "$DELEGATE" "$@"
fi

if [ -f "$MODULE_WRAPPER" ]; then
  exec sh "$MODULE_WRAPPER" "$@"
fi

# Fallback to system gradle if available
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi

echo "Error: No gradle wrapper or system gradle available."
exit 127
