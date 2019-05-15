# Appium
Android And IOS Testing Framework

# Test cases
1. User verify the scrolling on application
2. Register a new User and Verify Users details
3. User verify the Webview of application and validate the data
4. Verify Unhandled exception throw (application crash)

# Installation Pre-requisites:
Following tools must be installed to run the test case on desired machine:
## For Mobile Automation
```bash
Install JDK (v1.8.144)
Install Node Js
Install Eclipse (mars)
Install appium "npm install -g Appium" installed latest version(v1.13)
```
## For Android
```bash
Install Android SDK
Install Android API version 23
```
## For IOS
```bash
Install XCode (version-12)
Add Developer Account
```

# Steps to execute the project
Write your device configurations in Excel, Excel Path (src\main\resources\Testdata.xls).
## Using Eclipse
1. Open project in Eclipse
2. Right Click on project and Select Maven and select Maven Test

## Using CMD
1. Open cmd and go to POM.Xml path
4. Enter command 'mvn test'


## Emualtor or Simualtor
1. For android: setup a emulator using AVD manager.
2. For IOS : xCode should be installed on mac machine.

 In this project No need to open emualtor or simulator it will automatically open the emulator or simulator.
 
# Reports
Report will be generated with latest date and time folder in the following folder: target\cucumber-reports.
Complete logs will be saved in src\main\resources\logfile.txt
