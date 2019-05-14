Feature: Gesture Functionality

	
	Scenario: Verify Scroll
	And User Click on "HomePage/TouchActionbutton"
	And User take screenshots
	When User Swipe the screen "down"
	Then Verify Gesture "FLICK"