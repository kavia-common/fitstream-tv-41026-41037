#!/usr/bin/env sh
# Helper script for CI environments where the executable bit on ./gradlew
# may not be preserved. This script invokes the Gradle wrapper using sh.
# Usage: ./run-gradle.sh <gradle args...>
set -eu

SCRIPT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
WRAPPER="$SCRIPT_DIR/gradlew"

# If gradlew is not executable, try running via sh explicitly.
if [ ! -x "$WRAPPER" ]; then
  # Best effort: fetch wrapper jar if missing (mirrors logic in gradlew)
  PROPS_FILE="$SCRIPT_DIR/gradle/wrapper/gradle-wrapper.properties"
  JAR="$SCRIPT_DIR/gradle/wrapper/gradle-wrapper.jar"
  if [ ! -f "$JAR" ] && [ -f "$PROPS_FILE" ]; then
    DIST_URL=$(grep distributionUrl "$PROPS_FILE" | sed -e 's/.*=//')
    WRAPPER_URL="${DIST_URL%/*}/gradle-wrapper.jar"
    mkdir -p "$SCRIPT_DIR/gradle/wrapper"
    if command -v curl >/dev/null 2>&1; then
      curl -fsSL "$WRAPPER_URL" -o "$JAR"
    elif command -v wget >/dev/null 2>&1; then
      wget -q "$WRAPPER_URL" -O "$JAR"
    fi
  fi
  cd "$SCRIPT_DIR"
  exec sh "$WRAPPER" "$@"
else
  cd "$SCRIPT_DIR"
  exec "$WRAPPER" "$@"
fi
