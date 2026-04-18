package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "src/test/resources/features",
    glue = {"stepDefinitions","hooks"},
    //tags = //"@CabTest",
    //"@GiftCardTest", 
    //"@HotelTest",
    plugin = {"pretty" , "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    monochrome = true
    )
public class TestRunner extends AbstractTestNGCucumberTests{

}
