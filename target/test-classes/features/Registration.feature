Feature: Register a new User

	
	Scenario: Registration
		Given User Open registration page
		When User enter user details
		And User Click on "RegistrationPage/RegisterUser"
		Then Verify the User details