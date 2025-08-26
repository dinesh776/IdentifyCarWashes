package utilities;

import lombok.extern.java.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Utility class for capturing screenshots during test execution.
 * Primarily used for embedding screenshots in reports or debugging failed test cases.
 */
public class ScreenshotUtility{


	/**
	 * Captures a screenshot of the current browser window and returns it as a byte array.
	 * This is useful for attaching screenshots to test reports (e.g., Allure).
	 *
	 * @param driver The WebDriver instance used to take the screenshot.
	 * @return A byte array representing the screenshot image.
	 */

	private  static final Logger logger=LogManager.getLogger(ScreenshotUtility.class);
  
	    public static byte[] screenShotTC(WebDriver driver) {
			  logger.info("ScreenShot printed Successfully");
			return  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    }
}
