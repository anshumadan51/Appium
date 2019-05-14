package com.assignment.appiumTest.runner;


import java.io.File;

import com.assignment.appiumTest.Drivers.IniUtilities;
import com.assignment.appiumTest.Drivers.Report;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks {
	public static String iNIFile = "src/main/resources/resultConfig.ini";
	@Before
	public void getscenarioName(Scenario scenario){
		System.out.println("Before hooks");
		String TestcaseName = scenario.getName();
		IniUtilities.WriteIni(iNIFile, "Scenario","CurrentScenarioValue" , TestcaseName);
		IniUtilities.WriteIni(iNIFile, "Scenario","ExceptionScreenCounter" , 0);
		Report.addTestCases(TestcaseName);
	}

		@After(order = 0)
		public void screenshots(Scenario scenario) {
			if (scenario.isFailed()) {
				String screenshotName = scenario.getName().replaceAll(" ", "_");
				String  scenarioId = scenario.getId();
				String scenarioNumber = scenarioId.split(";")[3];
				String ScreenShotsPath = IniUtilities.ReadIni(iNIFile, "Sceanrio","ReportFolder" , "");
				File destinationPath = new File(ScreenShotsPath + File.separator + screenshotName +"_" + scenarioNumber +".png");
				//getScreenshot(screenshotName, destinationPath);
			}
		}
		
		@After(order=1)
		public void afterScenario(){
			//driverQuit();
			Report.endTestCases();
			
		}

}
