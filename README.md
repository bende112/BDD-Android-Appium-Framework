# appium-androidThis

project is a Kotlin application that runs appium launch via TestNG. A list of test classes that are run from this test suite  [testng.xml]

### Requirements ###

* IntelliJ
* Gradle
* ## Building the test suite ##
* 
* This test suite should be built with gradle running the following command
```
./gradlew shadowJar
./gradlew runWithAppium
 ```

 The output of this function is output to the following directory
```/build/reports```
 and it will contain the following
 ```
  scripts/             > The internal bash scripts required to run the router agent launch
  testng-launch.jar     > The compiled Java code
  testng.xml           > The TestNG XML file for configuring which classes to run in this test
  ```
## Running the test suite ##

The launch can be run via the JAR file with the following command on the JAR file: 

```java -DUDID=<device UDID> -DAPP='<location of APK>' -DAPP_ID=<app identifier for app> -DPLATFORM_VERSION=<android platform version> -jar testng-launch.jar testng.xml
```
If the System Properties (-D arguments) aren't set it will revert to internal default values.
```
===============================================
AppiumAndroidSuite
Total launch run: 3, Passes: 3, Failures: 0, Skips: 0
===============================================
```

A detailed output can be found by viewing the following in a browser.
```
/build/reports/test-output/index.html
```
### Running the test suite - Dependencies ###
  