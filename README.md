# Vonage Conversation Apitests

This is a Java-based test automation framework for the [Vonage Conversation API's](https://developer.vonage.com/en/api/conversation). 
It uses TestNG as the test framework and rest assured to make api requests.
Uses extent report testng listener for test reports.<br>
[TestCases can be found here](TestCases.md)

## Project Structure
The project is organized into the following maven modules:
* `client`: The clients directory contains source code for interacting with various components of the Vonage API, such as conversations, events, legs, members, and users.
* `core`: The core directory contains source code for core functionality of the Vonage API, such as default object mapping, RestAssured utilities, and TestNG listeners with retry functionality.
* `models`: The models directory contains source code for the request and response models used by various components of the Vonage API, such as conversations, events, legs, members, and users
* `tests`: The tests directory contains source code for testing various components of the Vonage API. 
  * It includes a configs directory for configuration files, a src/main/java/com/vonage/api directory for the main test code, and a src/test/java/com/vonage/api directory for the test resources.
      * `configs`: contains Service base URL, api path.
      * `suites`: testng suite xml file, with extent and retry listeners. 
      * `test.java`: contains the test class, test utlity class.
      * `resources`: contains log4j2 config file.
      * `suites`: contains TestNG XML suite files that group test classes together.
      * `target`: contains compiled classes and reports generated.

## How to Run the Tests
1. Clone the repository to your local machine.
2. Navigate to the project root directory.
3. Run `mvn test` to execute all tests.

## How to Generate Test Reports
Run [testng suite xml](tests%2Fsuites%2Ftestng_suite.xml). After running the tests, the test reports can be found in the `tests/target/testNgReport` directory. Open the `AutomationReportResultsToModify.html` file in a web browser to view the report.