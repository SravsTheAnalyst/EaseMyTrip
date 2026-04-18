package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HotelBookingPage;
import utils.ExcelUtils;

public class HotelBookingSteps extends BaseTest{
	private static final Logger log = LogManager.getLogger(HotelBookingSteps.class);
	
	HotelBookingPage hotelPage;
	int expectedCount;
	int row;
	
	@Given("user opens hotel booking page")
	public void openHotelPage() {
		
		hotelPage = new HotelBookingPage(getDriver());
		log.info("Opening Hotel Page");
		hotelPage.openSite();
		
		log.info("Opening hotel tab");
		hotelPage.openHotelTab();
		}
	
	@When("user selects adult count")
	public void selectAdults() {
		
		// Run only Hotel Booking rows
		row = ExcelUtils.getRowByTestCase("EaseMyTrip", "TC_03");
		
		// read data from excel
		String value = ExcelUtils.getCellData("EaseMyTrip", row, 2);
		System.out.println("Excel value :" +value);
		
		// statement to see the value 
		if(value == null || value.trim().isEmpty()) {
			throw new RuntimeException("Excel value missing!");
		}
		
		// selecting adults
		int adults = Integer.parseInt(value.trim());
		log.info("Setting adult count :" +adults);
		hotelPage.setAdults(adults);
	}
	
	@Then("selected adult count should displayed correctly")
	public void validateAdultCount() {
		String actual = hotelPage.getCount();
		log.info("Expected count: " +actual);
		ExcelUtils.setCellData("EaseMyTrip", row, 5, actual);
	
		// Expected value taken form excel
		String expected = ExcelUtils.getCellData("EaseMyTrip", row, 4);
		boolean status = actual.equals(expected); 
		
		// printing result pass or fail
		ExcelUtils.setCellData("EaseMyTrip", row, 6, status ? "PASS" : "FAIL");
		
		// Validation
		Assert.assertEquals(actual,expected);
	}
	

}
