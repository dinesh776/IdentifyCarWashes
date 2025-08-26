package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreeListingPage extends BasePage {
private static final Logger logger=LogManager.getLogger(FreeListingPage.class);
	public FreeListingPage(WebDriver driver) {
		super(driver);
		logger.info("Freelisting Page initialized.");
	}

	@FindBy(xpath = "//*[@id=\"header_freelisting\"]/a")
	WebElement freeListing;

	@FindBy(xpath="//*[@id=\"1\"]")
	WebElement mobile;

	@FindBy(xpath="//*[@id=\"listyourbusiness\"]/div[1]/form/button")
	WebElement start;

	@FindBy(xpath="//button[contains(@class,'close')]")
	WebElement dismiss;

	@FindBy(xpath="//*[@id=\"listyourbusiness\"]/div[1]/span[2]")
	WebElement errorMsg;

	@FindBy(xpath="//*[@id=\"mainContent\"]/div[9]/div")
	WebElement otpverify;





	@FindBy(xpath="//*[@id=\"mainContent\"]/div[9]/div/div[1]/button")
	WebElement close;

	public void handlingPopUp()
	{

		wait.until(ExpectedConditions.visibilityOf(dismiss));
		dismiss.click();
		logger.info("PopUp Handled");
	}

	public void freeListingPage() {
		try{
			logger.info("Opened Freelisting Page Succesfully" );
			freeListing.click();
		}catch (StaleElementReferenceException e){
			logger.error("Opened Freelisting Page Succesfully" );
			freeListing.click();
		}

	}
	public void mobileNumber(String number)
	{

		mobile.sendKeys(number);
		logger.info("Phone Number entered Succesfully");
	}

	public void startNow()
	{
		start.click();
		logger.info("'Start Now' button Clicked");
	}

	public String errorDisplay() {
		logger.info("Error message is displayed in the Console");
		return errorMsg.getText();
	}
	public String otpPopUp()
	{

		WebElement element = wait.until(ExpectedConditions.refreshed(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[1]/div[9]/div/div[1]/div"))
		));
		String text = element.getText();
		close.click();
		logger.info("OTP Verificvation Popup is handled");
		return text;

	}
}