package com.assignment.appiumTest.Drivers;

import com.assignment.appiumTest.Drivers.IniUtilities;
import com.assignment.appiumTest.Drivers.Report;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.TouchAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Drivers extends IntitializeFramework {

	public static String commandIOs = "/bin/bash -l -c appium --session-override -p 4723";
	public static String commandAndroid = "appium -a 127.0.0.1 -p 4723";
	public String DeviceConfigINIpath = "src/main/resources/DeviceConfig.ini";
	public static MobileDriver driver;
	public WebElement element;
	public static String iNIFile = "src/main/resources/ResultConfig.ini";
	public static int screenshotsCounter;
	public static String resultfolderpath;
	public static int currentLogID;

	/**
	 * @author anshulmadan
	 * @Description: Start appium Server
	 * @param command
	 *            String command to run
	 * @return It return String appium started
	 */
	public static String startServer(String device) throws InterruptedException, IOException

	{
		String command = null;
		if (device.equalsIgnoreCase("ANDROID")) {
			// command = "appium -a 127.0.0.1 -p 4723";
			command = "cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override";
		} else if (device.equalsIgnoreCase("IOS")) {
			command = "/bin/bash -l -c appium --session-override -p 4723";
		}
		// command =
		// "C:\Users\anshulmadan\Downloads\AnshulMadan_3146398_CucumberAssignment
		// (1).zip\AnshulMadan_3146398_CucumberAssignment\BDDAssignment\src\main\resources\IEDriverServer.exe";
		Process p = Runtime.getRuntime().exec(command);
		p.waitFor();
		Thread.sleep(5000);
//		 BufferedReader r = new BufferedReader(new
//		 InputStreamReader(p.getInputStream()));
//		 String line = "";
//		 String allLine = "";
//		 while ((line = r.readLine()) != null) {
//		 allLine = allLine + "" + line + "\n";
//		 if (line.contains("started on"))
//		 break;
//		 }
		return "";
	}

	/**
	 * @author anshulmadan
	 * @Description: Stop appium Server
	 * @param command
	 *            String command to run
	 * @return It return String appium Stop
	 * @throws IOException
	 */
	public static void stopServer(String device) throws IOException {
		Process executeCommand = null;
		if (device.equalsIgnoreCase("ANDROID")) {
			String command = "taskkill /F /IM node.exe";
			executeCommand = Runtime.getRuntime().exec(command);
		} else if (device.equalsIgnoreCase("IOS")) {
			String[] command = { "/usr/bin/killall", "-KILL", "node" };
			executeCommand = Runtime.getRuntime().exec(command);
		}
		// try {
		//// BufferedReader r = new BufferedReader(new
		// InputStreamReader(executeCommand.getInputStream()));
		//// String line = "";
		//// String allLine = "";
		//// while ((line = r.readLine()) != null) {
		//// allLine = allLine + "" + line + "\n";
		//// if (line.contains("SUCCESS"))
		//// break;
		//// }
		//
		// System.out.println("Appium server stopped.");
		//
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }

	}

	/**
	 * @author anshulmadan
	 * @Description: Stop appium Server
	 * @param command
	 *            String command to run
	 * @return It return boolean device is started or not
	 * @throws InterruptedException
	 */
	public static boolean startEmulator(String deviceName) throws InterruptedException {
		String commandAVD = "emulator -avd " + deviceName;
		boolean deviceStart = false;
		try {

			Process p = Runtime.getRuntime().exec(commandAVD);

			boolean asdf = emulatorVerify();
			// BufferedReader r = new BufferedReader(new
			// InputStreamReader(p.getInputStream()));
			// String line = "";
			// String allLine = "";
			// while ((line = r.readLine()) != null) {
			// allLine = allLine + "" + line + "\n";
			// if (line.contains("emulator: Serial number")){
			// deviceStart = true;
			// break;
			// }
			//
			// }
			return true;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;

	}//

	/**
	 * @author anshulmadan
	 * @Description: Stop emulator
	 */
	public static void stopEmulator() {
		String commandAVD = "adb emu kill";

		try {
			Process emulatorClose = Runtime.getRuntime().exec(commandAVD);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @author anshulmadan
	 * @Description: Stop appium Server
	 * @param command
	 *            String command to run
	 * @return It return boolean device is started or not
	 * @throws InterruptedException
	 */
	public static boolean emulatorVerify() throws InterruptedException {
		String commandAdb = "cmd.exe /c start cmd.exe /k \"adb devices";
		boolean deviceStart = false;
		try {
			Process p = Runtime.getRuntime().exec(commandAdb);
			p.waitFor();
			// BufferedReader r = new BufferedReader(new
			// InputStreamReader(p.getInputStream()));
			// String line = "";
			// String allLine = "";
			// while ((line = r.readLine()) != null) {
			// allLine = allLine + "" + line + "\n";
			// if (line.contains("emulator")){
			// deviceStart = true;
			// break;
			// }
			// }
			return deviceStart;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void getDeviceConfiguration() {
		try {

			Excel ObjExcel = new Excel(getExcelProperties("ExcelDataPath"));
			String sSheet = getExcelProperties("DeviceSheet");
			int iRow = ObjExcel.getRowCount(sSheet);
			int DeviceRow = 0;
			boolean bExecute = false;
			for (int i = 2; i <= iRow; i++) {
				String execute = ObjExcel.getCellData(sSheet, "Execute", i);
				if (execute.contains("YES")) {
					DeviceRow = i;
					bExecute = true;
					break;
				}
			}
			if (bExecute) {
				// Device configuration from Excel
				// Write device configuration in ini file
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "Device",
						ObjExcel.getCellData(sSheet, "Device", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "DeviceName",
						ObjExcel.getCellData(sSheet, "Device_Name", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "AppiumVersion",
						ObjExcel.getCellData(sSheet, "AppiumVersion", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "DevicePlatformVersion",
						ObjExcel.getCellData(sSheet, "DevicePlatformVersion", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "PlatformName",
						ObjExcel.getCellData(sSheet, "PlatformName", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "ApplicationPath",
						ObjExcel.getCellData(sSheet, "ApplicationPath", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "UDID",
						ObjExcel.getCellData(sSheet, "UDID", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "AutomationName",
						ObjExcel.getCellData(sSheet, "AutomationName", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "BundleName",
						ObjExcel.getCellData(sSheet, "BundleName", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "AppiumDriverServer",
						ObjExcel.getCellData(sSheet, "AppiumDriverServer", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "DeviceMode",
						ObjExcel.getCellData(sSheet, "Device_Mode", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "appPackage",
						ObjExcel.getCellData(sSheet, "appPackage", DeviceRow));
				IniUtilities.WriteIni(DeviceConfigINIpath, "Device", "appActivity",
						ObjExcel.getCellData(sSheet, "appActivity", DeviceRow));

			}

		} catch (Exception ex) {

		}
	}

	public void openApplication() throws MalformedURLException {
		try {
			String device = IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "Device", "");
			String plateFormName = IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "PlatformName", "");
			String appiumSrerverURL = IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "AppiumDriverServer", "");
			// Capabilities
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("appium-version",
					IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "AppiumVersion", ""));
			cap.setCapability("platformVersion",
					IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "DevicePlatformVersion", ""));
			cap.setCapability("platformName", IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "PlatformName", ""));
			cap.setCapability("deviceName", IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "DeviceName", ""));
			cap.setCapability("orientation", IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "DeviceMode", ""));
			cap.setCapability("app", IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "ApplicationPath", ""));

			// cap.setCapability("autoLaunch", false);
			if (!((device.contains("Simulator")) || (device.contains("emulator")))) {
				cap.setCapability("udid", IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "UDID", ""));
			} else {
				cap.setCapability("connectHardwareKeyboard", false);
			}

			if (plateFormName.equalsIgnoreCase("ANDROID")) {
				// cap.setCapability("avd", "Nexus6");
				cap.setCapability("appPackage", IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "appPackage", ""));
				cap.setCapability("appActivity",
						IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "appActivity", ""));
				// cap.setCapability("automationName",
				// IniUtilities.ReadIni(DeviceConfigINIpath, "Device",
				// "AutomationName", ""));
				cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
				// cap.setCapability(AndroidMobileCapabilityType.Au, true);
				cap.setCapability("autoAcceptAlerts", "true");
				cap.setCapability("autoDismissAlerts", "true");
				driver = new AndroidDriver<AndroidElement>(new URL(appiumSrerverURL), cap);
				// driver.switchTo().alert();
			} else if (plateFormName.equalsIgnoreCase("IOS")) {
				cap.setCapability("bundleID", IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "BundleName", ""));
				cap.setCapability("automationName",
						IniUtilities.ReadIni(DeviceConfigINIpath, "Device", "AutomationName", ""));
				cap.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, "10000");
				driver = new IOSDriver(new URL(appiumSrerverURL), cap);
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Report.logMessage("Pass", "Launching the application");
			Thread.sleep(2000);
		} catch (Exception ex) {
			ex.printStackTrace();
			Report.logMessage("Fail", "Unable to Launch the application");
		}
	}

	public boolean clickButton(String objectXPath) {
		boolean bClick = false;
		try {
			getElement(objectXPath).click();
			bClick = true;
			// -----write into Report-----
			Report.logMessage("Pass", "Clicking on [" + objectXPath + "]");
			return bClick;
		} catch (Exception ex) {
			Report.logMessage("Fail", "Not able to Click on [" + objectXPath + "]");
			Assert.assertTrue(false, ex.getMessage());
		}
		return bClick;
	}

	/**
	 * @author anshulmadan
	 * @Description: Get the screen shot
	 * @description1: It take the screen shot and add the screen shot in folder
	 *                and result link
	 */
	public static void getScreenshot() {
		try {
			resultfolderpath = IniUtilities.ReadIni(iNIFile, "Sceanrio", "ReportFolder", "");
			screenshotsCounter = Integer
					.parseInt(IniUtilities.ReadIni(iNIFile, "resultTestdata", "screenshotsCounter", ""));
			screenshotsCounter = screenshotsCounter + 1;
			String sScreenShotPath = resultfolderpath + File.separator + "ScreenShot_" + screenshotsCounter + ".png";

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// The below method will save the screen shot in the drive with name
			// "screenshot.png"
			Report.addScreenCaptureFromPath(sScreenShotPath);
			FileUtils.copyFile(scrFile, new File(sScreenShotPath));
			IniUtilities.WriteIni(iNIFile, "resultTestdata", "screenshotsCounter", screenshotsCounter);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void setInput(String objectXPath, String inputText) {
		try {
			element = getElement(objectXPath);
			element.click();
			element.clear();
			element.sendKeys(inputText);
			if (objectXPath.contains("password")) {
				inputText = "******";
			}
			Report.logMessage("Pass", "Entering value in [" + objectXPath + "] as [" + inputText + "]");
		} catch (Exception ex) {
			Report.logMessage("Fail", "Entering value in [" + objectXPath + "] is failed");
			Assert.assertTrue(false, ex.getMessage());
		}
	}

	/**
	 * @author anshulmadan
	 * @Description: select the drop down value in drop down list
	 * @param objectXPath
	 *            object tag name in OR.xml
	 * @param inputText
	 *            Value to select from drop down
	 * @return String value of element
	 */
	public void selectDropDown(String objectXPath, String inputText) {
		try {
			element = getElement(objectXPath);
			element.click();
			element = getElement(inputText);
			element.click();
			Report.logMessage("Pass", "Entering value in [" + objectXPath + "] as [" + inputText + "]");
		} catch (Exception ex) {
			Report.logMessage("Fail", "Entering value in [" + objectXPath + "] is failed");
			Assert.assertTrue(false, ex.getMessage());
		}
	}

	/**
	 * @author anshulmadan
	 * @Description: Hids the keyboard
	 */
	public void KeyboardHide() {

		try {
			driver.hideKeyboard();
		} catch (Exception ex) {
			Assert.assertTrue(false, ex.getMessage());
		}
	}

	/**
	 * @author anshulmadan
	 * @Description: Get the Element value on screen using AccessibilityId,
	 *               xpath, index
	 * @param objectXPath
	 *            object tag name in OR.xml
	 * @return String value of element
	 */
	public String GetElementvalue(String objectXPath) {
		String sElement = "";
		try {
			element = getElement(objectXPath);
			sElement = element.getText();
			Report.logMessage("Pass", "Element value: " + sElement);
		} catch (Exception ex) {
			Report.logMessage("Fail", "Not able to find the element " + ex.getMessage());
			Assert.assertTrue(false, ex.getMessage());
		}

		return sElement;
	}

	/**
	 * @author anshulmadan
	 * @Description: Click the button on screen using AccessibilityId, xpath,
	 *               index
	 * @param sAccessibilityId
	 *            AccessibilityId of the element
	 * @return return the boolean value True or false
	 */
	public boolean clickButtonByText(String objectXPath, String text) {
		boolean bClick = false;
		try {
			HashMap<String, String> Elementproperties = getXMLContent(objectXPath);
			if (Elementproperties.containsKey("Xpath")) {
				String xPath = Elementproperties.get("Xpath");
				xPath = xPath.replace("Changetext", text);
				driver.findElementByXPath(xPath).click();
				bClick = true;
				Report.logMessage("Pass", "Clicking on [" + text + "]");
			}
			return bClick;
		} catch (Exception ex) {
			Report.logMessage("Fail", "not able to Click on [" + text + "]");
			Assert.assertTrue(false, ex.getMessage());
		}
		return bClick;
	}

	/**
	 * @author anshulmadan
	 * @Description: Accept or reject the alert
	 * @param sType
	 *            Accept or reject of alert type method
	 */
	public String AlertAccRej(String sType) {
		String sAlerMessage = null;
		try {
			// Thread.sleep(1000);
			// Navigate to 1st item

			Alert sdf = driver.switchTo().alert();
			Report.logMessage("Pass", "Alert message: " + sAlerMessage);
			if (sType.contains("Accept")) {
				driver.switchTo().alert().accept();
			}
			if (sType.contains("Dismiss")) {
				driver.switchTo().alert().dismiss();
			}
			return sAlerMessage;
		} catch (Exception ex) {
			Assert.assertTrue(false, ex.getMessage());
		}
		return sAlerMessage;
	}

	/**
	 * @author anshulmadan
	 * @Description: Scroll Up The Element1 Accessibility to Elment 2 index
	 * @param objectXPath
	 *            First Object from want to scroll
	 * @param sDirection
	 *            Left or right to swipe
	 */
	public void SwipeScreen(String objectXPath, String sDirection) {

		String ScrollValue = "";
		WebElement Element = null;
		try {
			HashMap<String, String> Elementproperties = getXMLContent(objectXPath);
			if (Elementproperties.containsKey("AccessibilityId")) {
				Element = (WebElement) (driver.findElementByAccessibilityId(Elementproperties.get("AccessibilityId")));
			} else if (Elementproperties.containsKey("Xpath")) {
				Element = (WebElement) (driver.findElementByXPath(Elementproperties.get("Xpath")));
			} else if (Elementproperties.containsKey("Id")) {
				Element = (WebElement) (driver.findElementById(Elementproperties.get("Id")));
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> so = new HashMap<String, String>();
			so.put("direction", sDirection);
			// so.put("element", ((RemoteWebElement) Element).getId());
			js.executeScript("mobile: scroll", so);
			Thread.sleep(1000);
		} catch (Exception ex) {
			Assert.assertTrue(false, ex.getMessage());
		}

	}

	/**
	 * @author anshulmadan
	 * @Description: Get the Element using AccessibilityId,xpath, index
	 * @param objectXPath
	 *            object tag name in OR.xml
	 * @return WebElement
	 */
	public WebElement getElement(String objectXPath) {
		WebElement Element = null;
		try {
			HashMap<String, String> Elementproperties = getXMLContent(objectXPath);
			if (Elementproperties.containsKey("AccessibilityId")) {
				Element = (WebElement) (driver.findElementByAccessibilityId(Elementproperties.get("AccessibilityId")));
			} else if (Elementproperties.containsKey("Xpath")) {
				Element = (WebElement) (driver.findElementByXPath(Elementproperties.get("Xpath")));
			} else if (Elementproperties.containsKey("Id")) {
				Element = (WebElement) (driver.findElementById(Elementproperties.get("Id")));
			}
		} catch (Exception ex) {
			Assert.assertTrue(false, ex.getMessage());
		}
		return Element;
	}

	/**
	 * @author anshulmadan
	 * @Description: Scroll UpDown the Page with direct
	 * @param direction
	 *            First Object from want to scroll
	 * @param objectXPath
	 *            First object to want to scroll
	 */
	public void ScrollUPDownPage(String direction, String objectXPath) {
		try {
			Thread.sleep(1000);
			WebElement ElementStart = null;
			HashMap<String, String> Elementproperties = null;
			Elementproperties = getXMLContent(objectXPath);
			if (Elementproperties.containsKey("AccessibilityId")) {
				ElementStart = (WebElement) (driver
						.findElementByAccessibilityId(Elementproperties.get("AccessibilityId")));
			} else if (Elementproperties.containsKey("Xpath")) {
				ElementStart = (WebElement) (driver.findElementByXPath(Elementproperties.get("Xpath")));
			} else if (Elementproperties.containsKey("Id")) {
				ElementStart = (WebElement) (driver.findElementById(Elementproperties.get("Id")));
			}

			TouchAction tAction = new TouchAction(driver);
			int startX = ElementStart.getLocation().getX() + 20;
			int startY = ElementStart.getLocation().getY() + 20;
			int iDirection;
			if (direction.contains("UP")) {
				iDirection = -255;
			} else {
				iDirection = 250;
			}
			int endX = startX + iDirection;
			int endY = startY + iDirection;

			tAction.press(new PointOption().withCoordinates(startX, startY))
					.moveTo(new PointOption().withCoordinates(endX, endY)).release().perform();
			// tAction.tap(TapOptions().)
			// tAction.press(startX, startY).moveTo(endX,
			// endY).release().perform();
		} catch (Exception ex) {
			Assert.assertTrue(false, ex.getMessage());
		}
	}

	/**
	 * @author anshulmadan
	 * @Description: Change context view
	 * @param ViewType
	 *            View type
	 * @param objectXPath
	 *            First object to want to scroll
	 */
	public void changeWebView(String ViewType) {
		try {
			Set<String> allcontext = driver.getContextHandles();
			for (String context : allcontext) {
				if (context.contains(ViewType)) {
					driver.context(context);
				}
			}

		} catch (Exception ex) {
			//Assert.assertTrue(false, ex.getMessage());
		}

	}

	/**
	 * @author anshulmadan
	 * @Description: Quit the driver
	 * @param element
	 *            Element By id/name
	 */
	public void driverQuit() {
		driver.quit();
	}

}
