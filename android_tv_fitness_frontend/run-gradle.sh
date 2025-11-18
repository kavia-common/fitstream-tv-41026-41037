#!/usr/bin/env sh
# Helper to invoke the placeholder Gradle wrapper even if executable bits are not preserved by the environment.
# CI can run: sh run-gradle.sh check
SCRIPT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
sh "$SCRIPT_DIR/gradlew" "$@"
