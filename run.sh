#!/usr/bin/env bash
set -euo pipefail

DIR="$(cd "$(dirname "$0")" && pwd)"

# Find a Java 8 runtime to use (prefer JAVA_HOME if set)
JAVA_CMD=""
if [ -n "${JAVA_HOME:-}" ] && [ -x "$JAVA_HOME/bin/java" ]; then
  # prefer JAVA_HOME if provided
  JAVA_CMD="$JAVA_HOME/bin/java"
fi
if [ -z "$JAVA_CMD" ]; then
  if command -v java >/dev/null 2>&1; then
    JAVA_VER_STR="$((java -version) 2>&1 | head -n1)"
    if echo "$JAVA_VER_STR" | grep -q "1\.8"; then
      JAVA_CMD="java"
    fi
  fi
fi
if [ -z "$JAVA_CMD" ]; then
  # common OpenJDK 8 locations
  for p in "/usr/lib/jvm/java-8-openjdk-amd64/bin/java" "/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java" "/usr/lib/jvm/java-8-openjdk/bin/java" "/usr/lib/jvm/java-8-openjdk/jre/bin/java"; do
    if [ -x "$p" ]; then
      JAVA_CMD="$p"
      break
    fi
  done
fi
if [ -z "$JAVA_CMD" ]; then
  cat >&2 <<'MSG'
Java 8 (JRE/JDK) not found. Install it and re-run this script.

Example (Debian/Ubuntu):
  sudo apt-get update && sudo apt-get install -y openjdk-8-jdk-headless

Or set JAVA_HOME to a Java 8 installation before running this script.
MSG
  exit 1
fi

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

# JVM options: disable loading of platform Assistive Technology implementations
# which can cause AWT to fail on some Linux setups (AtkWrapper missing).
JVM_OPTS="-Djavax.accessibility.assistive_technologies="

echo "Classpath: $CP"
# Ensure working directory and required resource dirs exist (prevents FileUtils IO errors)
WORK_DIR="$DIR/Gradded-unit/Gradded-unit"
mkdir -p "$WORK_DIR/resources" \
         "$WORK_DIR/resources/data" \
         "$WORK_DIR/resources/savedata" \
         "$WORK_DIR/resources/savedata/coop" || true

cd "$WORK_DIR"

exec "$JAVA_CMD" -Xmx512m $JVM_OPTS -cp "$CP" "$MAIN_CLASS" "$@"
