package StepDefinations;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.assignment.appiumTest.Drivers.Drivers;
import com.assignment.appiumTest.Drivers.IniUtilities;
import com.assignment.appiumTest.Drivers.Report;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks extends Drivers{
	public static String iNIFile = "src/main/resources/resultConfig.ini";
	@Before
	public void getscenarioName(Scenario scenario) throws InterruptedException, IOException{
		String  PlateformName = IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "PlatformName", "");
		System.out.println("Before hooks");
		String TestcaseName = scenario.getName();
		IniUtilities.WriteIni(iNIFile, "Scenario","CurrentScenarioValue" , TestcaseName);
		IniUtilities.WriteIni(iNIFile, "Scenario","ExceptionScreenCounter" , 0);
		startServer(PlateformName);
		Thread.sleep(2000);
		Report.addTestCases(TestcaseName);
		openApplication();
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
			driverQuit();
			Report.endTestCases();
			
		}

}
