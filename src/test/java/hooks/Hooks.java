package hooks;

import base.BaseTest;
import base.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest{
	@Before
	public void setUp() {
		driver = WebDriverFactory.getDriver();
	}
	
	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			takeScreenshot(scenario.getName().replaceAll("","_"));
			attachScreenshotToAllure();
		}
	      WebDriverFactory.quitDriver();
	}

}
