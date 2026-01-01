
## Automation Test Framework
This repository contains a robust Java-based automation test framework built with TestNG, designed for scalable, data-driven, cross-browser testing using LambdaTest. It supports headless execution, generates detailed test reports (ExtentReports) and logs (Log4j), and is fully configurable via CLI parameters.


## Author
- [@sangyadav31](https://github.com/sangyadav31)
- EmailAddress: sangeeta.pawar10@gmail.com

## About Me
Hi, My name is Sangeeta Yadav. I have 10 years of experience in Automation Testing using technologies like Selenium WebDriver, RestAssured.
My major expertise is in JAVA programming language.


##  Links
[portfolio](https://github.com/sangyadav31)

[linkedin](https://www.linkedin.com/in/sangeeta-yadav-087baa22)


## Prerequisites
- **Java 11**
- **Maven**
- Download link: https://mvnrepository.com/

## Features
- Headless browser support
- Cloud execution on LambdaTest
- Cross-Browser Testing: Support running tests on different browsers.
- Maven CLI configuration
- Data-driven testing using OpenCSV & Apache POI
- Dynamic test data using JavaFaker
- HTML reports using ExtentReports
- Logging via Log4j2
- Parameterized execution

## Tech Stack
- JAVA 11
- TestNg
- OpenCSV
- Gson
- Apache POI
- JavaFaker
- ExtentReports
- Log4j2
- LambdaTest

## Setup Instructions

**Clone the repository:**

git clone https://github.com/sangyadav31/Test-Automation-Framework.git
cd Test-Automation-framework

**Running test on LambdaTest:**

mvn clean test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false -X


**Running test on Local machine in Headless Mode:**

mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true -X


## Reports and Logs

- Test Reports are generated using ExtentReports and located at:
./report.html
- Execution Logs are created using Log4j2 and stored in: 
./logs/ directory.

## Integrated the project in GitHub Actions
This automation framework is integrated with github actions.
The test will be triggered at 11:30 IST every single day.