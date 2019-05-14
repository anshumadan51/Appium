# Appium
Android And IOS Testing Framework

# Test cases
1. Verify Gesture Functionality
2. Register a new User
3. Verify WebView
4. Verify Exception

# Installation Pre-requisites:
Following tools must be installed to run the test case on desired machine:
## For Mobile Automation
```bash
Install JDK
Install Node Js
Install Eclipse
Install appium "npm install -g Appium"
```
## For Android
```bash
Install Android SDK
Install Android API version
```
## For IOS
```bash
Install XCode
Add Developer Account
```

# Steps to execute the project
1. Open project in Eclipse
2. Edit excel sheet devices with your device details, Excel Path (src\main\resources\Testdata.xls).
3. Open cmd and go to POM.Xml path
4. Enter command 'mvn test'


## Emualtor or Simualtor
1. For android: need to setup a emulator using AVD manager
2. For IOS : xCode should installed in mac machine.

 In this project No need to open emualtor or simulator it will automatically open the emulator or simulator.
 
# Reports
Report will be generated with latest date and time folder in the following folder: target\cucumber-reports.
Complete logs will be saved in src\main\resources\logfile.txt
