package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import base.BaseTest;
import io.cucumber.java.en.*;
import pages.GiftCardPage;
import utils.ExcelUtils;
 

public class GiftCardSteps extends BaseTest {
	private static final Logger log = LogManager.getLogger(GiftCardSteps.class);
    GiftCardPage gift;
    String errorMsg;
    int row;
 
    @Given("user opens the gift card page")
    public void openGiftCardPage() {
       driver = getDriver();
       gift = new GiftCardPage(driver);
       log.info("Opening gift card page");
       gift.openSite();
    }
 
    @When("user executes gift cards from excel")
    public void executesGiftFromExcel() {
    	// read data from excel
    	row = ExcelUtils.getRowByTestCase("EaseMyTrip", "TC_02");
    	
    	// Navigate to gift card
    	log.info("Navigating to gift card section");
    	gift.navigateToGiftCards();
    	
    	//Read Email from Excel
    	String email = ExcelUtils.getCellData("EaseMyTrip", row, 2);
    	log.info("Email from excel" +email);
    	
    	// Enter the data
    	gift.fillGiftCardsDetails("5000", "2", "Ravi", email, "9653792047");
    	
    	//Capture error
    	errorMsg = gift.getCapturedErrorMessage();
    	log.error("Captured validation message: " +errorMsg);
    	
    	System.out.println("Captured Error: " + errorMsg);
    	
    	//Write Actual Output
    	ExcelUtils.setCellData("EaseMyTrip", row, 5, errorMsg);
    	
    	// Validate
    	boolean status = errorMsg != null &&
    			!errorMsg.trim().isEmpty() &&
    			errorMsg.toLowerCase().contains("email");
    	
    	// Write Status
    	ExcelUtils.setCellData("EaseMyTrip", row, 6, status ? "PASS" : "FAIL");
    	
    	// ASSERT
    	Assert.assertTrue(status,
    			"Expected email validation error but got: " + errorMsg);
    	}
    }
 