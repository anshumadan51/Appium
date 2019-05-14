package StepDefinations;

import cucumber.api.java.en.*;

import java.net.MalformedURLException;

import com.assignment.appiumTest.Drivers.Drivers;
public class Base extends Drivers{

	
	
	
	@Given("User Open Application")
	public void application() throws MalformedURLException{
		System.out.println("in features file");
		openApplication();
	}
	
	@When("User Click on \"(.*)\"")
	public void ClickOnButton(String element){
		clickButton(element);
		System.out.println("in features file" + element);
		
	}
	
	@Then("VerifyPop and \"(.*)\"")
	public void AlertAcceptDismiss(String sType){
		AlertAccRej(sType);
	}
	
	
	@And("User take screenshots")
	public void UsergetScreenShots(){
		getScreenshot();
	}
	
	@And("User wait for \"(.*)\"")
	public void Userwait(String userWait) throws InterruptedException{
		
		Thread.sleep(Integer.parseInt(userWait));
	}
	
	@And("User Select Context to \"(.*)\"")
	public void SelectContext(String contextType){
		changeWebView(contextType);
	}
}
