package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.FreeListingPage;

public class Free_listing_login {
	FreeListingPage fp = new FreeListingPage(BaseTest.getDriver());
	private  static final Logger logger= LogManager.getLogger(Free_listing_login.class);
	@Given("the user is on the Free Listing page")
	public void the_user_enters_the_free_listing_page() {

		logger.info("User Naviagted to Free Listing Page");
		fp.freeListingPage();
	}

	@When("the user enters a valid phone number into the field")
	public void the_user_enters_a_valid_phone_number_into_the_field() {

		logger .info("User entered a valid phone Number");
		fp.mobileNumber("8639284125");
	}

	@When("the user clicks the submit button")
	public void the_user_clicks_on_the_submit_button() {
	   logger.info("User clicked on button 'Start Now'");
		fp.startNow();
	}

	@Then("the OTP popup should appear")
	public void the_otp_popup_will_appear() {
		logger.info("OTP Verification pop-up is handled");
	    fp.handlingPopUp();
	    // method for pop up visibility
	}

	@When("the user enters an invalid phone number into the field")
	public void the_user_enters_an_invalid_phone_number_into_the_field() {
		logger.info("User Entered an invalid Phone Number");
		fp.mobileNumber("1234567890");
		
	}
	
	@Then("the error message {string} should appear on the Free Listing page")
	public void the_error_message_will_appear_in_free_listing_page(String string) {
		logger.info("Error message is displayed like 'Enter a valid Phone Number'");
	    Assert.assertEquals(string, fp.errorDisplay());
	}

	@Then("the error message {string} should appear on the Free Listing page for an empty field")
	public void the_error_message_will_appear_in_free_listing(String string) {
		logger.info("Error message is displayed like 'Enter a Phone Number'");
		Assert.assertEquals(string, fp.errorDisplay());
	}





}
