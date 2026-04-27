package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "@target/failed_scenarios.txt",
    glue = {"stepDefinitions","hooks"},
    plugin = {"pretty" , "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
    		},
    monochrome = true
    )
public class FailedTestRunner extends AbstractTestNGCucumberTests{

}
