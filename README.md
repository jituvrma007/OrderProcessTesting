# OrderProcessTesting : about the project
This is a sample backend/Api automation project for testing restful APIs of a restaurant order processing. Starting from simple functionality, it consists of a '/processOrder' API that allows you to return response with order object with updated "orderStatus" and "lastUpdatedTimestamp".

### A bit more about the project
This project is covering some basic Api automation test framework and some sample test cases. Although, we can surely enhance it more with lots of robust features.
Also, this project is developed/tested on Windows, however it is intended to work on any operating system, in case of issues, please fix the path inside "utility/Constants.java"

### Project structure
```

├── OrderProcessTesting
│   ├── src\main\java
│   │   ├── base 
│   │   └── utility
│   │   
│   ├── src\main\resources
│   │   
│   ├── src\test\java
│   │   ├── models
│   │   ├── helpers
│   │   └── scenarios
│   │   
│   ├── src\test\resources
│   │   └── orderProcessTesting
│   │ 		├── apiDetails
│   │   	├── environments
│   │   	├── schema
│   │   	└── testSuites

```


### Dependencies
Here are the dependencies used in the project for development & testing perspective. 
<br/> Note - All are open source project and widely available over the web. Set up the matching dependencies to test the code further.

* [Java 17](https://www.java.com/en/) - Programming Language
* [Gradle 7.5](https://gradle.org/install/) - Dependency Management
* [RestAssured](http://rest-assured.io/) - Accessing the APIs Response
* [TestNG](https://testng.org/doc/) - Unit Testing framework for Java 
* [ExtentReports](http://extentreports.com/) - Reporting framework for our tests
* [WireMock](https://wiremock.org/#open-source-get-started) - Api request/response mocking framework for "/processOrder" Api
* [MockLab](https://orderprocesstesting.mocklab.io/processOrder) - On cloud stubbing/mocking framework for "/processOrder" Api


## Solution include
1. Abstraction Layers - Layers like, BaseSetup, Utility, Request API details, Environment, schema and testSuites are done. Since it is just a sample project, we can design and extend this framework to scale further


2. Data driven testing - We are using the testNG, which has the capability of doing the data driven testing (Using data provider). So, we can test the same test of test cases with different multiple set of data.


3. Generating html human-readable report -> Yes, html reports are being generated with using utility ExtentReports/testNG/gradle reports. It is covering the detailed steps, pass/fail/skipped results and with the respective timeline. It is super easy to understand, and it supports detailed charts as well.


4. Logging -> On console screen and under "executionReports\generatedLogs\Logs.log" directory.


5. JSON Schema Validation -> We validate the JSON response with the respective schema stored inside "schema" directory in the project. 


6. Mocking -> On local using wiremock server, and using MockLab which gives us the flexibility to implement the response without backend implementation.


## Getting Started
The below steps will get you a copy of the project up and running, on your local machine for development and testing purposes. 

1. Open your terminal and do a clone of this project.
```
=> git clone https://github.com/jituvrma007/OrderProcessTesting.git
```
2) Navigate to the respective cloned directory and run below command.
```
=> cd OrderProcessTesting\wiremocksample
```
3) Run the "wireMock" Server (It will act as standalone mocked response server which will serve the mocked api response)
```
=> java -jar wiremock-jre8-standalone-2.33.2.jar --global-response-templating --verbose
```
4) If you something like below text on terminal, "wireMock" Server ran successfully locally.
![image](https://user-images.githubusercontent.com/66662969/184700201-b9dd8bf0-9a38-41f3-b59d-d712f935b9d4.png)

5) Don't close this terminal, and open a new terminal and then in the new terminal run below command -
```
=> gradlew test
````
6) Above command will build the project along with test cases.

## Running the test cases on MockLab environment.
Note - For stopping the wireMock server, navigate to terminal where server is running and then do "ctrl+c". This will stop the server,however this step is purely optional. So, irrespective of our wiremock server is running, we will target "Mocklab" server.

1. Navigate to the respective cloned directory.
2. Run below commands to configure "HOST" variable as "dev" in command prompt or in edit configuration (if running via IDE). 
```
=> set HOST = dev 
```
![image](https://user-images.githubusercontent.com/66662969/184699912-dd75588f-a3f1-4580-8c8d-c4fe4f27ceed.png)

3. Please use same terminal to execute the test scenarios. 
4. Our tests will target on "Mocklab" environment. 


## More ways of running the tests
1) Run after importing into IDE(Intelij). Followed by running respective test case "ProcessOrderAPITestScenarios.java" file from "scenarios" folder or "scenarios" folder itself.
2) Run the testNG suite ".xml" files, inside "testSuites" folder.
3) Run "gradlew test -Psuite=ProcessOrderApiScenarios" from command line. 
4) Run the specific classes from command line as "gradlew :clean :test --tests "com.orderProcessTesting.tests.scenarios.ProcessOrderAPITestScenarios"

## Reports
Here are a below steps to get a human-readable html report.
```
1) Navigate to respective directory where the project is stored locally.
2) Navigate to /executionReports directory.
3) Find the logs inside "/generatedLogs" directory.
4) Find the html based gradleReport inside "/gradleReports" directory.
5) Find the html based extentReport inside "/extentReport" directory.
6) Find the html based extentReport inside "/testNGReport" directory.
```
![image](https://user-images.githubusercontent.com/66662969/184699701-5201c7b1-c3c0-4653-853b-43e27a240277.png)


