package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;


public class CabBookingPage {
	
    WebDriver driver;
    WebDriverWait wait;
    
    //======================================================
    //CONSTRUCTOR
    //======================================================
    public CabBookingPage(WebDriver driver) {
    	this.driver = driver;
    	this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	PageFactory.initElements(driver, this);
    }
    
    //=========================================================
    //WEB ELEMENTS
    //=========================================================
    
    @FindBy(xpath = "//span[@class='meuicowidth cabmenuico']")
    WebElement cabsMenu;
    
    @FindBy(xpath = "//label[@for='rdbTravelTypeOther']")
    WebElement outStation;
    
    @FindBy(xpath = "//div[@id='sourceName']")
    WebElement fromCityBoxOpen;
    
    @FindBy(id="a_FromSector_show")
    WebElement fromCityTextBox;
    
    @FindBy(xpath = "//div[@id='destinationName']")
    WebElement toCityBoxOpen;
    
    @FindBy(xpath="//input[@id='a_ToSector_show']")
    WebElement toCityTextBox;
     
    @FindBy(xpath = "//div[@id='pickCalender']//i[@class='Cal-ico']")
    WebElement calendarOpen;
  
    
    @FindBy(xpath = "//div[@id='showtime']")
    WebElement pickupTime;
    
    @FindBy(xpath = "//div[@onclick='GetList()']")
    WebElement searchBtn;
    
   @FindBy(xpath = "//input[@id='suv']")
    WebElement suvFilter;
    
    //==========================================================
    // COMMON METHODS
    //===========================================================
    public void safeClick(WebElement element) {
    	wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element))).click();
    }
    public void safeSendKeys(WebElement element ,String value) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	element.clear();
    	element.sendKeys(value);
    	
    }
    
    //==========================================================
    // Action Methods
    //==============================================================
    
    
    public void openSite() {
    	driver.get(ConfigReader.get("url"));
    }
    
    public void selectCabs() {
    	safeClick(cabsMenu);
    	safeClick(outStation);
    	
    }
    
    public void enterFromCity(String city) {
    	
    	safeClick(fromCityBoxOpen);
    	safeSendKeys(fromCityTextBox,city);
    	
    	By optionLocator = By.xpath("//div[@id='StartCity']//li[1]");
    	wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(optionLocator)));
    	WebElement option = driver.findElement(optionLocator);
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
    	//Actions act = new Actions(driver);
    	//act.moveToElement(option).pause(Duration.ofMillis(500)).click().perform();
    	
    }
    
    public void enterToCity(String city) {
    	safeClick(toCityBoxOpen);
    	safeSendKeys(toCityTextBox,city);
    	
    	By optionLocator = By.xpath("//div[@id='EndCity']//li[1]");
    	wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(optionLocator)));
    	WebElement option = driver.findElement(optionLocator);
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
    	//Actions act = new Actions(driver);
    	//act.moveToElement(option).pause(Duration.ofMillis(500)).click().perform();
   
    }
    
    //==============================================================
    // Date Picker (Reusable)
    //==============================================================
    
    public void selectDate(String month,String year,String date) {
    	safeClick(calendarOpen);
    	
    	while(true) {
    		String currentMonth=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-datepicker-month']"))).getText();
    		String currentYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
    		
    		if(currentMonth.equalsIgnoreCase(month)&&currentYear.equals(year)) {
    			break;
    		}
    		
    		//next button
    		driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
    	}
    		// Select Date after month matched
    		List<WebElement> alldates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//a"));
    		for(WebElement dt : alldates) {
    			if(dt.getText().equals(date)) {
    				dt.click();
    				break;
    			}
    		}
    		driver.findElement(By.tagName("body")).click();
    		}
     
    
    //===========================================================
    // Time Picker (Split version)
    //============================================================
    
    public void selectTime(String hour , String min, String period) {
    	
        safeClick(pickupTime);
        
        // Wait until hour options are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@onclick,'Hour')]")));
        
        // Select hour(Dynamic)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@onclick='Hour(" + hour + ")']"))).click();
        // Convert minutes to index 
        int minuteValue = Integer.parseInt(min);
        int index = (minuteValue / 5) +1;
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@onclick='min(" + index + ")']"))).click();
        
       // Select AM/PM
        if(period.equalsIgnoreCase("PM")) {
        	
        driver.findElement(By.xpath("//label[@for='pm']")).click();
        } else {
        	
        	driver.findElement(By.xpath("//label[@for='am']")).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@onclick='Done(event)']"))).click();
        
    }
    
    public void clickSearch() {
    	safeClick(searchBtn);
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cabFare _f25']")));
    }
    
    public void applySuvFilter() {
    	try {
    		suvFilter.click();;
    		}catch(Exception e) {
    		System.out.println("SUV filter not applicable");
    	}
    }
    public int getLowestPrice(){ 
    	//div[@class='cabFare _f25 ']
            List<WebElement> prices = driver.findElements(By.xpath("//div[contains(@class,'cabFare') and contains(text(),'₹')]"));
            List<Integer> list = new ArrayList<>();
            	for(WebElement p:prices) {
            		String text = p.getText().replaceAll("[^0-9]","");
            		if(!text.isEmpty())
            			list.add(Integer.parseInt(text));
            	}
            	if(list.isEmpty()) {
            		throw new RuntimeException("No prices found");
            	}
           return Collections.min(list);
    }
    	//===========================================================
       // Business flow method
       //===========================================================
       
           public int bookCab(String from, String to, String month, String year, String date,
        		   String hour, String minute, String period) {
        	   selectCabs();
        	   enterFromCity(from);
        	   enterToCity(to);
        	   selectDate(month, year, date);
        	   selectTime(hour, minute, period);
        	   clickSearch();
        	   applySuvFilter();
        	   
        	   return getLowestPrice();
           }
}
