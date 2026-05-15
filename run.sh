#!/usr/bin/env bash
set -euo pipefail

DIR="$(cd "$(dirname "$0")" && pwd)"

# Locate built jar
JAR_PATH="$(find "$DIR/Gradded-unit/Gradded-unit/dist" -maxdepth 1 -type f -name '*.jar' 2>/dev/null | head -n1 || true)"
if [ -z "$JAR_PATH" ]; then
  echo "No built jar found."
  if [ -f "$DIR/Gradded-unit/Gradded-unit/build.xml" ]; then
    if ! command -v ant >/dev/null 2>&1; then
      cat >&2 <<'MSG'
Ant is not installed or not on PATH. To build the project install Ant and re-run this script.

Installation examples:
  Debian/Ubuntu: sudo apt-get update && sudo apt-get install -y ant
  macOS (Homebrew): brew install ant
  Windows (Chocolatey): choco install ant

Alternatively, run the build on CI or obtain a prebuilt jar and place it in Gradded-unit/Gradded-unit/dist/
MSG
      exit 1
    fi
    echo "Building with Ant..."
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
# Build classpath including libraries next to the jar
LIB_DIR="$DIR/Gradded-unit/Gradded-unit/Libaries"
CP_ITEMS=()
CP_ITEMS+=("$JAR_PATH")
if [ -d "$LIB_DIR" ]; then
  shopt -s nullglob
  for f in "$LIB_DIR"/*.jar; do
    CP_ITEMS+=("$f")
  done
  shopt -u nullglob
fi

if [ "${#CP_ITEMS[@]}" -eq 0 ]; then
  echo "No jars found for classpath. Exiting." >&2
  exit 1
fi

# Join classpath items with ':' (POSIX)
IFS=':'
CP="${CP_ITEMS[*]}"
unset IFS

# Main class
MAIN_CLASS="com.FuturePixels.Entry"

echo "Classpath: $CP"
exec java -Xmx512m -cp "$CP" "$MAIN_CLASS" "$@"
