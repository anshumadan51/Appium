package StepDefinations;

import com.assignment.appiumTest.Drivers.Drivers;
import com.assignment.appiumTest.Drivers.IniUtilities;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PopUpWindow extends Drivers {
	
	@Then("^Verify PopUp and \"(.*)\"$")
	public void VerifyPopUp(String popUpAction){
		
		AlertAccRej(popUpAction);
	}
}
