package StepDefinations;

import org.testng.Assert;

import com.assignment.appiumTest.Drivers.Drivers;
import com.assignment.appiumTest.Drivers.Report;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Gesture extends Drivers {

	@When("^User Swipe the screen \"(.*)\"$")
	public void swipe(String swipeType) {
		ScrollUPDownPage(swipeType, "TouchActions/ScaleFactor");
	}

	@Then("^Verify Gesture \"(.*)\"$")
	public void verifyGesture(String expectedGesture) {
		String actualGesture = GetElementvalue("TouchActions/GestureType");
		if (actualGesture.contains(expectedGesture)) {
			Report.logMessage("Pass", "Gesture: " + expectedGesture + " Tested sucessfully");
		} else {
			Report.logMessage("Fail", "Gesture: " + expectedGesture + " is not tested sucessfully");
			Assert.assertTrue(false, "Gesture is not tested sucessfully");
		}

	}
}
