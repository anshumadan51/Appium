package StepDefinations;

import com.assignment.appiumTest.Drivers.Drivers;
import com.assignment.appiumTest.Drivers.Report;

import cucumber.api.java.en.And;

public class WebView extends Drivers {

	@And("User Verify the Value for \"(.*)\" element \"(.*)\"")
	public void getElementValue(String expectedValue, String element){
		String ActualValue = GetElementvalue(element);
		if(ActualValue.contains(expectedValue)){
			Report.logMessage("Pass", "Expected value is : " + expectedValue + "same as " + ActualValue);
		}else{
			Report.logMessage("Fail", "Expected value is : " + expectedValue + "same as "+ ActualValue);
		}
	}
}
