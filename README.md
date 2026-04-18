#EaseMyTrip Automation Framework
 
  
This repository contains a BDD-based automation framework developed to test key functionalities of the EaseMyTrip web application.
 
The framework is built using Selenium WebDriver, Cucumber, and TestNG, and follows the Page Object Model (POM) design pattern to ensure scalability, reusability, and maintainability.
 
---
 
##Highlights
 
- Java and Maven-based framework
- BDD implementation using Cucumber
- Page Object Model (POM) design
- Centralized WebDriver management (WebDriverFactory)
- Screenshot capture on failure
- Logging using Log4j2
- Allure reporting integration
- Excel utility for data-driven testing
 
---
 
##Modules Covered
 
- Cab Booking
- Hotel Booking
- Gift Card Validation
 
---
 
##Tech Stack
 
- Java
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- Maven
- Log4j2
- Allure Reports
 
---
 
##Prerequisites
 
Ensure the following are installed before running the project:
 
- Java 17
- Maven (3.6 or above)
- Eclipse IDE
- Chrome browser
- ChromeDriver (or WebDriverManager)
 
---
 
##Quick Setup
 

1. Clone the repository.
2. Configure test settings in `src/test/resources/config.properties` (see configuration section below).
3. Run tests with Maven:

```powershell
mvn test
```

By default, the suite uses Chrome. To change the browser, update `browser` in `config.properties` (supported: `chrome`, `edge`).

---
 
## Configuration

The test runner reads `src/test/resources/config.properties`. Key properties:

- baseUrl - the target application URL (default: https://www.easemytrip.com/)
- browser - browser to run tests in (`chrome` or `edge`)
- implicitWait - implicit wait timeout (seconds)
- explicitWait - explicit wait timeout (seconds)

Example (`src/test/resources/config.properties`):

```
baseUrl=https://www.easemytrip.com/
browser=chrome
implicitWait=10
explicitWait=10
```

Notes:
- The framework's `WebDriverFactory` reads `browser` and configures browser-specific options.
- If you prefer automatic driver management, consider adding WebDriverManager to the POM and updating `WebDriverFactory` to use it.

---
 
##Framework Design
 
Page Object Model (POM)
 
Separates UI elements and actions from test logic, improving maintainability and reusability.
 
WebDriverFactory
 
Provides centralized driver initialization and management.
 
Cucumber Hooks
 
- @Before: Initializes browser
- @After: Captures screenshot on failure and closes browser
 
##Utilities
 
- Excel utility for test data handling
- ConfigReader for configuration management
 
---
 
##Project Structure
 
- `pom.xml` - Maven configuration and dependencies
- `src/test/resources/features/` - Cucumber feature files
- `src/test/java/stepDefinitions/` - Cucumber step definitions
- `src/test/java//base/pages/` - Page Object classes
- `src/test/java/base/WebDriverFactory.java` - Thread-local WebDriver lifecycle
- `src/test/java/utils/ConfigReader.java` - Simple properties reader
- `src/test/java/runners/TestRunner.java` - Cucumber/TestNG runner

Use the feature files in `src/test/resources/features/` as the canonical place to add new scenarios.

---
 
##Running Tests
 
- Run the full test suite:

```powershell
mvn test
```
Using Maven:
mvn clean test
 
Using Eclipse:
Right-click TestRunner.java → Run As → TestNG Test
 
---
 
##Reporting
 
Allure results are stored in:
```allure-results```
 
Generate report using:
```allure serve allure-results```
 
---
 
##Screenshot Handling
 
Screenshots are captured only when a test fails and stored in the screenshots folder.
 
---
 
##Logging
 
Logging is implemented using Log4j2. Logs are stored in logs/automation.log.
 
---
 
##Test Coverage
 
- Cab Booking flow
- Hotel Booking flow
- Gift Card validation
 
---
 
##Troubleshooting
 
- Ensure browser and driver versions match
- Verify Maven dependencies are installed
- Check config.properties values
- Ensure feature file paths are correct
 
---
 
##Extending the Framework
 
- Add new Page classes in pages
- Add feature files in features
- Add step definitions in stepDefinitions
- Enable parallel execution using TestNG
 
---
 
##CI/CD (Future Scope)
 
The framework is designed to support CI/CD integration. It can be integrated with Jenkins using Maven commands and supports headless execution for faster runs.
 
---
 
##Team Members
 
This project was developed as a group effort:
 
Sravanthi (POC)
Seema
Pratik
Kusuma
Mahalaxmi
 
---

## GitHub Repository
https://github.com/SravsTheAnalyst/EaseMyTrip.git

----
 