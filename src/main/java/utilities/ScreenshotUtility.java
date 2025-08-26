package utilities;

import lombok.extern.java.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScreenshotUtility{
	private  static final Logger logger=LogManager.getLogger(ScreenshotUtility.class);
	    public static byte[] screenShotTC(WebDriver driver) {
			logger.info("ScreenShot printed Successfully");
			return  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    }
}
