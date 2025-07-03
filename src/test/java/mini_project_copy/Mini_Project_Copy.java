package mini_project_copy;

import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;


public class Mini_Project_Copy {
		public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		
		   //calling DriverSetUp class and initializing driver
		   DriverSetUp d = new DriverSetUp();
		   driver = d.DriverSetup();
		   
		   //adding implicit wait for web elements globally
		   int duration=Integer.parseInt(ConfigReader.get("implicitWait"));
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
        
		   
		//handling alerts and popups if they are present
        try {
            WebElement popupClose = driver.findElement(By.id("livchat_close")); // Adjust selector
            popupClose.click();
        } catch (Exception e) {
            System.out.println("No popup detected.");
        }
        
        //navigate to  the "completed projects" link
        driver.findElement(By.partialLinkText("COMPLETED PROJECTS")).click();
        
        driver.findElements(By.className("boosted-tabs-nav-title")).get(0).click();
        
        //List<WebElement> projects = driver.findElements(By.className("item-title"));
        List<WebElement> projects = driver.findElements(By.xpath("//h2[@class='item-title']//a[contains(text(),'Isha')]"));
        
        //added the projects in a set to avoid duplicate.
        Set<String> set = new HashSet<>();
        for(WebElement ele : projects) {
            set.add(ele.getAttribute("textContent"));
        }
        
        //adding explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
        wait.until(ExpectedConditions.elementToBeClickable(By.className("item-title")));
        
        
        //initialize javascript executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement footer = projects.get(set.size()-1);
		int footerPosition = footer.getLocation().getY()-250;
		
		// Scroll to a position slightly above the footer
		js.executeScript("window.scrollTo(0, arguments[0]);", footerPosition);
		Thread.sleep(1000);
        
        System.out.println("Total Number of Completed Projects: "+set.size());
 
        //Display the names of first five completed projects to the console
        System.out.println("First Five Completed Projects");
 
        for(int projectCount=0;projectCount<5;projectCount++)
            System.out.println(projects.get(projectCount).getAttribute("textContent"));
 
        
        
        //Scroll down and click on “Enquire Now” button
        driver.findElement(By.xpath("//div[@class='mobile-content-isha']//div[@class='mci-ph enq-tooltip-new']//a[@href='#']")).click();

 
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[contains(text(),'Other Enquiry')]")));
        element1.click();
   
        //Read and display the email address for contact to console
        System.out.println(driver.findElement(By.xpath("//a[contains(text(),'marketing@ishahomes.com')]")).getAttribute("textContent"));
        
        //System.out.println(driver.findElement(By.xpath("//a[contains(text(),'marketing@ishahomes.com')]")).getText());
        
        
        //calling Screenshot class and store it in a folder
        ScreenShot shot = new ScreenShot();
        shot.TakeScreenShot(driver);
        
        //closes the driver
        driver.quit();

	}

}
