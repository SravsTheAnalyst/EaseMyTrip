
package base;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;


public class BaseTest {
	public WebDriver driver;
	
	public WebDriver getDriver() {
		return WebDriverFactory.getDriver();
		
	}
	public void quitDriver() {
	    WebDriverFactory.quitDriver();
		
	}
	//==============================================================
	// Save Screnshot to the folder
	//==============================================================
	
	public void takeScreenshot(String filename) {
		
		try {
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			File src = ts.getScreenshotAs(OutputType.FILE);
			
			String  timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String path = System.getProperty("user.dir")+"/screenshots/"+filename + "_" +timestamp +"_" +System.currentTimeMillis() + ".png";
			File dest = new File(path);
			Files.copy(src.toPath(), dest.toPath());
            System.out.println("Screenshot saved at :" +path);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	//==============================================================
   // Attach Screenshot to Allure
  //==============================================================
		public void attachScreenshotToAllure() {
			try {
				TakesScreenshot ts = (TakesScreenshot) getDriver();
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				
				Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
			}catch (Exception e) {
	            e.printStackTrace();
	        }
		}

}
