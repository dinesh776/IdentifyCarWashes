package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility{

	    public static byte[] screenShotTC(WebDriver driver) {
			return  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    }
}
