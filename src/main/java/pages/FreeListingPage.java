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


/**
 * Page Object class representing the Free Listing page.
 * Extends BasePage to inherit WebDriver, WebDriverWait, and JavaScriptExecutor.
 */

public class FreeListingPage extends BasePage {

	// Constructor initializes elements using PageFactory
private static final Logger logger=LogManager.getLogger(FreeListingPage.class);

	public FreeListingPage(WebDriver driver) {
		super(driver);
		logger.info("Freelisting Page initialized.");
	}


	// WebElement for the "Free Listing" link in the header
	@FindBy(xpath = "//*[@id=\"header_freelisting\"]/a")
	WebElement freeListing;


	// WebElement for the mobile number input field
	@FindBy(xpath="//*[@id=\"1\"]")
	WebElement mobile;

	//WebElement for the "Start Now" button
	@FindBy(xpath="//*[@id=\"listyourbusiness\"]/div[1]/form/button")
	WebElement start;

	//WebElement for dismissing popups
	@FindBy(xpath="//button[contains(@class,'close')]")
	WebElement dismiss;

	// WebElement for displaying error message
	@FindBy(xpath="//*[@id=\"listyourbusiness\"]/div[1]/span[2]")
	WebElement errorMsg;


	// WebElement for OTP verification popup
	@FindBy(xpath="//*[@id=\"mainContent\"]/div[9]/div")
	WebElement otpverify;


	// WebElement for closing the OTP popup
	@FindBy(xpath="//*[@id=\"mainContent\"]/div[9]/div/div[1]/button")
	WebElement close;

	//Handles and dismisses any popup that appears on the page.
	public void handlingPopUp()
	{

		wait.until(ExpectedConditions.visibilityOf(dismiss));
		dismiss.click();
		logger.info("PopUp Handled");
	}


	/**
	 * Navigates to the Free Listing page by clicking the link.
	 * Handles potential StaleElementReferenceException.
	 */

	public void freeListingPage() {
		try{
			logger.info("Opened Freelisting Page Succesfully" );
			freeListing.click();
		}catch (StaleElementReferenceException e){
			logger.error("Opened Freelisting Page Succesfully" );
			freeListing.click();// Retry clicking if element becomes stale
		}

	}


	/**
	 * Enters the mobile number into the input field.
	 * @param number Mobile number to be entered
	 */

	public void mobileNumber(String number)
	{

		mobile.sendKeys(number);
		logger.info("Phone Number entered Succesfully");
	}


	/**
	 * Clicks the "Start Now" button to proceed.
	 */
	public void startNow()
	{
		start.click();
		logger.info("'Start Now' button Clicked");
	}


	/**
	 * Retrieves the error message displayed on the page.
	 * @return Error message text
	 */
	public String errorDisplay() {
		logger.info("Error message is displayed in the Console");
		return errorMsg.getText();
	}


	/**
	 * Waits for the OTP popup to appear, retrieves its text, and closes it.
	 * @return Text content of the OTP popup
	 */
	public String otpPopUp()
	{

		WebElement element = wait.until(ExpectedConditions.refreshed(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[1]/div[9]/div/div[1]/div"))
		));
		String text = element.getText();

		close.click();// Close the popup after retrieving text
		logger.info("OTP Verificvation Popup is handled");
		return text;

	}
}