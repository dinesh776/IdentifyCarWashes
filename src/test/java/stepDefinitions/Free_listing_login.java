package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.FreeListingPage;

public class Free_listing_login {
	FreeListingPage fp = new FreeListingPage(BaseTest.getDriver());
	
	@Given("the user is on the Free Listing page")
	public void the_user_enters_the_free_listing_page() {
	    fp.freeListingPage();
	}

	@When("the user enters a valid phone number into the field")
	public void the_user_enters_a_valid_phone_number_into_the_field() {
	    fp.mobileNumber("8639284125");
	}

	@When("the user clicks the submit button")
	public void the_user_clicks_on_the_submit_button() {
	   fp.startNow();
	}

	@Then("the OTP popup should appear")
	public void the_otp_popup_will_appear() {
	    fp.handlingPopUp();
	    // method for pop up visibility
	}

	@When("the user enters an invalid phone number into the field")
	public void the_user_enters_an_invalid_phone_number_into_the_field() {
		fp.mobileNumber("1234567890");
		
	}
	
	@Then("the error message {string} should appear on the Free Listing page")
	public void the_error_message_will_appear_in_free_listing_page(String string) {
	    Assert.assertEquals(string, fp.errorDisplay());
	}

	@Then("the error message {string} should appear on the Free Listing page for an empty field")
	public void the_error_message_will_appear_in_free_listing(String string) {
		Assert.assertEquals(string, fp.errorDisplay());
	}





}
