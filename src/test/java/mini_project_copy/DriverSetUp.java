package mini_project_copy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.ConfigReader;

public class DriverSetUp {
	public WebDriver driver;
	public void initialize() {
        String browser = ConfigReader.get("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } 
    }
    
    public WebDriver DriverSetup() {
    	initialize();
        driver.manage().window().maximize();
        // Open the URL
        driver.get(ConfigReader.get("baseUrl"));
        return driver;
    }

	
	
	
}
