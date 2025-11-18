#!/bin/bash
cd /home/kavia/workspace/code-generation/fitstream-tv-41026-41037/android_tv_fitness_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

