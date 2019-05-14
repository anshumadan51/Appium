Feature: WebView Functionality

	
	Scenario: Verify WebView Search
	And User Click on "HomePage/WebView"
	And User Select Context to "WEBVIEW"
	And User wait for "10000"
	And User Verify the Value for "Hello" element "WebView/WelcomeMessage"
	And User take screenshots