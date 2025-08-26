package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


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

	public static byte[] screenShotTC(WebDriver driver) {
			return  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    }
}
