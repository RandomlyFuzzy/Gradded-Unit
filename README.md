# Gradded-Unit

Gradded-Unit is a Java platformer project created by Future Pixels (2018–2019).

Engine
------

- This project uses a custom engine, **LiamEngine**. You can find the engine repository here: https://github.com/RandomlyFuzzy/LiamEngine
- The repository includes a helper script at `scripts/update_engine.sh` which fetches the latest `LiamEngine.jar` (or builds it from source) and places it into `Gradded-unit/Gradded-unit/Libaries/`.

Key Features
------------

- Lightweight Java game engine integration (LiamEngine)
- Single distributable JAR with bundled libraries
- Cross-platform packaging: ZIP bundles for Linux and Windows with bundled JRE
- Simple launcher scripts: `run.sh` (Linux/macOS) and `run.bat` (Windows) included
- Asset-driven levels and resources under `Gradded-unit/Gradded-unit/resources`
- Ant-based build for reliable reproducible builds

Usage
-----

- Run locally: make sure JDK 8 is installed and run:

	./run.sh

- Or run the built jar directly (after building):

	java -Xmx512m -jar Gradded-unit/Gradded-unit/dist/Gradded-unit.jar

CI / Releases
--------------

- A GitHub Actions workflow at `.github/workflows/release.yml` builds the project, packages platform bundles (including a JRE, resources and launchers) and creates a GitHub Release with ZIP artifacts when you push a `v*.*.*` tag.

Contact
-------

- Maintained by the Future Pixels team.


