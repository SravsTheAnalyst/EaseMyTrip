package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import utils.ConfigReader;

public class HotelBookingPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    
    public HotelBookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    //=========================================================
    //WEB ELEMENTS
    //=========================================================
    
    @FindBy(xpath="//span[text()='Hotels']")
    WebElement openHotelTab;
    
    @FindBy(xpath = "//div[contains(@class,'roomGuests')]//i[contains(@class,'down_arw_htl')]")
    private WebElement guestDropdown;

    @FindBy(id = "Adults_room_1_1")
    private WebElement adultCount;

    @FindBy(id = "Adults_room_1_1_plus")
    private WebElement plusBtn;
    
   

     //==========================================================
    // Common Method
    //==============================================================
    public void openSite() {
    	driver.get(ConfigReader.get("url"));
    }
    
    public void safeClick(WebElement element) {
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    	js.executeScript("arguments[0].click();", element);
    }
    
  
    //==========================================================
    // Action Methods
    //==============================================================
   
   
    public void openHotelTab() {
    	openHotelTab.click();
    }
    public void setAdults(int target) {
    	safeClick(guestDropdown);
    	wait.until(ExpectedConditions.visibilityOf(adultCount));
        //js.executeScript("arguments[0].click();", guestDropdown);
        
        // Loop until the display text matches target
        wait.until(d -> {
        	String text = adultCount.getText().trim().replaceAll("[^0-9]","");
        	if(text.isEmpty())
        		return false;
            int current = Integer.parseInt(text);
            //System.out.println("Current adults: " +current);
            
            if (current < target)
            	js.executeScript("arguments[0].click();", plusBtn);
            return current == target;
            
        });
        // closes dropdown
        js.executeScript("document.body.click();");
    }

    public String getCount() { 
    	wait.until(ExpectedConditions.visibilityOf(adultCount));
    	String count = adultCount.getText().trim().replaceAll("[^0-9]","");
    	//System.out.println("The adult count are: "+count);
    	return count; 
    	}
}