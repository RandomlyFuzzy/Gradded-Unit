#!/usr/bin/env bash
set -euo pipefail

DIR="$(cd "$(dirname "$0")" && pwd)"

# Locate built jar
JAR_PATH="$(find "$DIR/Gradded-unit/Gradded-unit/dist" -maxdepth 1 -type f -name '*.jar' 2>/dev/null | head -n1 || true)"
if [ -z "$JAR_PATH" ]; then
  echo "No built jar found. Attempting to build with Ant..."
  if [ -f "$DIR/Gradded-unit/Gradded-unit/build.xml" ]; then
    (cd "$DIR/Gradded-unit/Gradded-unit" && ant -f build.xml clean jar)
    JAR_PATH="$(find "$DIR/Gradded-unit/Gradded-unit/dist" -maxdepth 1 -type f -name '*.jar' 2>/dev/null | head -n1 || true)"
  else
    echo "No build.xml present. Cannot build." >&2
    exit 1
  fi
fi

if [ -z "$JAR_PATH" ]; then
  echo "Build did not produce a jar. Exiting." >&2
  exit 1
fi

echo "Running: $JAR_PATH"
exec java -Xmx512m -jar "$JAR_PATH" "$@"
