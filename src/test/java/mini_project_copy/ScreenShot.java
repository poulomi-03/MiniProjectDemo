package mini_project_copy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public void TakeScreenShot(WebDriver driver) throws IOException {
		// Ensure the Screenshots folder exists
		
		
		//create a file or directory
        File screenshotsDir = new File("ScreenShots");
        if (!screenshotsDir.exists()) {
            if (screenshotsDir.mkdirs()) {
                System.out.println("Created Screenshots folder successfully.");
            } else {
                System.err.println("Failed to create Screenshots folder.");
                return;
            }
        }

        // Generate a unique screenshot filename using timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File screenshotFile = new File(screenshotsDir, "screenshot_" + timestamp + ".png");

        // Capture screenshot and save inside the ScreenShots folder
        File capturedFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(capturedFile.toPath(), screenshotFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Screenshot saved successfully at: " + screenshotFile.getAbsolutePath());
	}
}
