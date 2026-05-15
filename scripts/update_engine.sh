#!/usr/bin/env bash
set -eu

# Downloads the latest LiamEngine jar into the project's Libaries folder.
LIB_DIR="Gradded-unit/Gradded-unit/Libaries"
mkdir -p "$LIB_DIR"
ENGINE_URL="https://github.com/RandomlyFuzzy/LiamEngine/releases/latest/download/LiamEngine.jar"

echo "Attempting to download LiamEngine from $ENGINE_URL"
if curl --head --silent --fail "$ENGINE_URL" >/dev/null 2>&1; then
  curl -L "$ENGINE_URL" -o "$LIB_DIR/LiamEngine.jar"
  echo "Downloaded LiamEngine to $LIB_DIR/LiamEngine.jar"
else
  echo "Release download not available. Cloning source and attempting to build as fallback."
  tmpdir=$(mktemp -d)
  git clone --depth 1 https://github.com/RandomlyFuzzy/LiamEngine "$tmpdir/LiamEngine"
  if [ -f "$tmpdir/LiamEngine/build.gradle" ]; then
    (cd "$tmpdir/LiamEngine" && ./gradlew --no-daemon jar)
    cp "$tmpdir/LiamEngine/build/libs/"*.jar "$LIB_DIR/" || true
  elif [ -f "$tmpdir/LiamEngine/pom.xml" ]; then
    (cd "$tmpdir/LiamEngine" && mvn -q package)
    cp "$tmpdir/LiamEngine/target/"*.jar "$LIB_DIR/" || true
  else
    echo "Could not automatically build LiamEngine. Please build manually and copy the jar to $LIB_DIR"
    exit 1
  fi
  echo "Updated LiamEngine artifacts in $LIB_DIR"
fi
