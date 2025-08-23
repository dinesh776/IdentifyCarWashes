package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.ResultsPage;

public class Service_page_query {
	ResultsPage rp = new ResultsPage(BaseTest.getDriver());
	
	@When("the user enters a valid name and valid phone number into the fields")
	public void the_user_enters_a_valid_name_and_valid_phone_number_into_the_fields() {
	    rp.setName("bharath");
	    rp.setMobileNumber("9876345342");
	}

	@Then("the OTP window will appear")
	public void the_otp_window_will_appear() {
		Assert.assertEquals("Welcome", rp.getWelcomeMsg());  
	}

	@When("the user enters a valid name and an invalid phone number into the fields")
	public void the_user_enters_a_valid_name_and_an_invalid_phone_number_into_the_fields() {
		rp.setName("bharath");
	}

	@When("the user enters an invalid name and a valid phone number into the fields")
	public void the_user_enters_an_invalid_name_and_a_valid_phone_number_into_the_fields() {
	    rp.setMobileNumber("3333333333");
	}

	@When("the user enters an invalid name and an invalid phone number into the fields")
	public void the_user_enters_an_invalid_name_and_an_invalid_phone_number_into_the_fields() {
		return;
	}
	
	@When("the user clicks on the service page submit button")
	public void the_user_clicks_on_the_service_page_submit_button() {
		rp.clickOnSendQuery();
	}
	
	@Then("the error message {string} will appear in service page for invalid number")
	public void the_error_message_will_appear_in_service_page_for_invalid_number(String string) {
		Assert.assertEquals(string, rp.getMobileErrorMsg());
	}

	@Then("the error message {string} will appear in service page for invalid name")
	public void the_error_message_will_appear_in_service_page_for_invalid_name(String string) {
	    Assert.assertEquals(string, rp.getNameErrorMsg());
	}

	@Then("the error message {string} will appear in service page for invalid name and number")
	public void the_error_message_will_appear_in_service_page_for_invalid_name_and_number(String string) {
	    Assert.assertEquals(string, rp.getNameErrorMsg());
	}






}
