package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
public class GiftCardPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public GiftCardPage(WebDriver driver) {
    	this.driver=driver;
    	this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	PageFactory.initElements(driver, this);
    }
        
  //=========================================================
    //WEB ELEMENTS
    //=========================================================
    
    @FindBy(xpath="//span[@class='meuicowidth moremenuico']")
    WebElement moreMenu;
    
    @FindBy(xpath="//span[normalize-space()='Gift Card']")
    WebElement giftCardElement;
    
    @FindBy(xpath="//img[@alt='NewYear']")
    WebElement cardType;
    
    @FindBy(xpath="//input[@maxlength='5']")
    WebElement moneyBox;
  //select[@selected='selected']
    @FindBy(xpath="(//select)[1]")
    WebElement quantityDropDown;
 
    @FindBy(xpath="//input[@ng-model='User.SenderName']")
    WebElement senderName;
    
    @FindBy(id="txtEmailId")
    WebElement senderEmail;
    
    @FindBy(xpath="//input[@ng-model='User.SenderMobile']")
    WebElement senderNumber;
    
    @FindBy(xpath="//input[@ng-change='SameAsSender()']")
    WebElement recieverCheckBox;
    
    @FindBy(xpath="//input[@ng-change='IsValidate()']")
    WebElement termsCheckBox;
    
   // @FindBy(xpath="//p[@class='err_msg ng-binding']")
    //WebElement errorMsg;
  
    
  //==========================================================
    // Common Method
    //==============================================================
    public void safeClick(WebElement element) {
    	wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element))).click();
    }
    public void safeSendKeys(WebElement element ,String value) {
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    	element.click();
    	element.clear();
    	element.sendKeys(value);
    	
    }
    public void scrollToElement(WebElement element) {
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void openSite() {
    	driver.get(ConfigReader.get("url"));
    }
    
    //==========================================================
    // Action Methods
    //==============================================================
    public void navigateToGiftCards() {
    	safeClick(moreMenu);
    	safeClick(giftCardElement);
    	
    	scrollToElement(cardType);
    	safeClick(cardType);
    	
    }
    public void fillGiftCardsDetails(String money,String quantityValue,String name,String email,String phoneNumber) {
    	scrollToElement(moneyBox);
    	safeSendKeys(moneyBox,money);
    	
    	Select select = new Select(quantityDropDown);
    	select.selectByValue(quantityValue);
    	
    	scrollToElement(senderName);
    	
    	safeSendKeys(senderName,name);
    	safeSendKeys(senderEmail,email);
    	safeSendKeys(senderNumber,phoneNumber);
    	safeClick(recieverCheckBox);
    	safeClick(termsCheckBox);
    	}
    public String getCapturedErrorMessage() {
        // We use a more generic locator that looks for any 'red' or 'error' text near the email
        By flexibleErrorLocator = By.xpath("//p[contains(@class,'err_msg') and contains(text(),'valid.')]");
      //input[@id='txtEmailId']/following::p[contains(@class,'err_msg')][1]
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(flexibleErrorLocator));
        return error.getText();
    
    }
  
  
}
