package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreeListingPage extends BasePage {

		public FreeListingPage(WebDriver driver) {
	        super(driver);
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

	    public void handlingPopUp()
		{
			wait.until(ExpectedConditions.visibilityOf(dismiss));
			dismiss.click();
	    }

	    public void freeListingPage() {
			try{
				freeListing.click();
			}catch (StaleElementReferenceException e){
				freeListing.click();
			}

	    }
	     public void mobileNumber(String number)
	    {
			mobile.sendKeys(number);
	    }
	    public void startNow()
	    {
			start.click();
	    }
	    public String errorDisplay() {
	    	return errorMsg.getText();
	    }
}
