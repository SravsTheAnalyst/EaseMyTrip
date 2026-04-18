package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.CabBookingPage;
import utils.ExcelUtils;

public class CabBookingSteps extends BaseTest{
	private static final Logger log = LogManager.getLogger(CabBookingSteps.class);
	  
    CabBookingPage cab;
    int price;
    int row;
    
	@Given("user opens the cab booking website")
	public void openWebsite() {
		driver = getDriver();
		cab = new CabBookingPage(driver);
		log.info("opening cab booking page");
		cab.openSite();
		cab.selectCabs();
	}
	
	@When("user executes cab booking from excel")
	public void executeCabFromExcel() {
		
		// read data from excel
		row = ExcelUtils.getRowByTestCase("EaseMyTrip", "TC_01");
		
		// From reading from excel data
		String from = ExcelUtils.getCellData("EaseMyTrip",row, 2);
		
		// To reading from excel data
		String to = ExcelUtils.getCellData("EaseMyTrip",row, 3);
		System.out.println("Running Cab Test " + from + " to " +to);
		
		// Entering values into cab booking page 
		log.info("From city: " +from);
		cab.enterFromCity(from);
		
		log.info("To city: " +to);
		cab.enterToCity(to);
		
		cab.selectDate("May", "2026", "19");
		cab.selectTime("12", "20", "PM");
		
		log.info("Clicking search");
		cab.clickSearch();
		
		cab.applySuvFilter();
		
		// Getting lowest price
		log.info("Fetching lowest price");
		int price = cab.getLowestPrice();
		
		log.info("Lowest price : " +price);
		
		ExcelUtils.setCellData("EaseMyTrip", row, 5,String.valueOf(price));
		
		boolean status = (price > 0);
		
		// Entering testcase as Pass or Fail
		ExcelUtils.setCellData("EaseMyTrip", row, 6, status ? "PASS" : "FAIL");
		
		// Validation
		Assert.assertTrue(price > 0);
		}
}

