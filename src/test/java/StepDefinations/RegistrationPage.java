package StepDefinations;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.assignment.appiumTest.Drivers.Drivers;
import com.assignment.appiumTest.Drivers.Excel;
import com.assignment.appiumTest.Drivers.IniUtilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegistrationPage extends Drivers {

	public static String iNIFile = "src/main/resources/ResultConfig.ini";
	public String username, email, password, name, langauge;

	@Given("^User Open registration page$")
	public void openRegistration() {
		clickButton("HomePage/StartRegistration");
	}

	@When("^User enter user details$")
	public void enterUserDetails() throws IOException {
		Excel ObjExcel = new Excel(getExcelProperties("ExcelDataPath"));
		String TestCaseName = IniUtilities.ReadIni(iNIFile, "Scenario", "CurrentScenarioValue", "");
		username = ObjExcel.getColData(TestCaseName, "UserName");
		email = ObjExcel.getColData(TestCaseName, "Email");
		password = ObjExcel.getColData(TestCaseName, "Password");
		name = ObjExcel.getColData(TestCaseName, "name");
		langauge = ObjExcel.getColData(TestCaseName, "Langauge");
		setInput("RegistrationPage/UserName", username);
		setInput("RegistrationPage/Email", email);
		setInput("RegistrationPage/Password", password);
		setInput("RegistrationPage/Name", name);
		KeyboardHide();
		clickButton("RegistrationPage/ProgrammingLanuguge");
		clickButtonByText("RegistrationPage/Lanugauge", "PHP");
		clickButton("RegistrationPage/Accept");
		getScreenshot();
		// clickButton("RegistrationPage/RegisterUser");
	}

	@Then("^Verify the User details$")
	public void VerifyRegisteredUser() {
		getScreenshot();
		assertEquals(name, GetElementvalue("VerifyUser/Name"));
		assertEquals(email, GetElementvalue("VerifyUser/Email"));
		assertEquals(langauge, GetElementvalue("VerifyUser/ProgrammingLanuguge"));
		assertEquals("true", GetElementvalue("VerifyUser/Accept"));
		clickButton("VerifyUser/RegisterUser");
	}

}
