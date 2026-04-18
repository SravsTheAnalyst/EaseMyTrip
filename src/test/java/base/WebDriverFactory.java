package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class WebDriverFactory {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		if(driver == null) {
			String browser = ConfigReader.get("browser","chrome").toLowerCase();
			
			switch(browser) {
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				break;
			default:
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
				break;
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		return driver;
	}
	public static void quitDriver() {
		if(driver!=null) {
		driver.quit();
		driver=null;
	}
}
}

