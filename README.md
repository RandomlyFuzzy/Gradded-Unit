# Gradded-Unit
Gradded-unit 2018-2019<br>
Created by Future pixels<br>
&nbsp;  AKA <br>
&nbsp;&nbsp;&nbsp;&nbsp;      Liam Woolley<br>
&nbsp;&nbsp;&nbsp;&nbsp;      Jack Teviotdale<br>
&nbsp;&nbsp;&nbsp;&nbsp;       Liam Rickman<br>
&nbsp;&nbsp;&nbsp;&nbsp;       Ricky Swankie<br>
Made for java version "1.8.0_201"

CI and engine update
---------------------

 - A GitHub Actions workflow has been added at `.github/workflows/release.yml` to build a runnable JAR using Java `1.8.0_201` and Ant. The workflow will attempt to download the latest `LiamEngine.jar` before building.
 - To update the `LiamEngine` locally, run the helper script `scripts/update_engine.sh` which downloads the latest release (or builds from source if needed) and places the jar into `Gradded-unit/Gradded-unit/Libaries/`.

