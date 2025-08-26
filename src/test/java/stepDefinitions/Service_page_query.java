package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.ResultsPage;

public class Service_page_query {

	private static final Logger logger = LogManager.getLogger(Service_page_query.class);
	ResultsPage rp = new ResultsPage(BaseTest.getDriver());

	@When("the user enters a valid name and valid phone number into the fields")
	public void the_user_enters_a_valid_name_and_valid_phone_number_into_the_fields() {
		logger.info("Entering valid name and phone number");
		rp.setName("bharath");
		rp.setMobileNumber("9876345342");
	}

	@Then("the OTP window should appear")
	public void the_otp_window_will_appear() {
		logger.info("Verifying OTP window appearance");
		Assert.assertEquals("Welcome", rp.getWelcomeMsg());
		logger.info("OTP window appeared successfully");
	}

	@When("the user enters a valid name and an invalid phone number into the fields")
	public void the_user_enters_a_valid_name_and_an_invalid_phone_number_into_the_fields() {
		logger.info("Entering valid name and invalid phone number");
		rp.setName("bharath");
	}

	@When("the user enters an invalid name and a valid phone number into the fields")
	public void the_user_enters_an_invalid_name_and_a_valid_phone_number_into_the_fields() {
		logger.info("Entering invalid name and valid phone number");
		rp.setMobileNumber("3333333333");
	}

	@When("the user enters an invalid name and an invalid phone number into the fields")
	public void the_user_enters_an_invalid_name_and_an_invalid_phone_number_into_the_fields() {
		logger.info("Entering invalid name and invalid phone number");
		// No action taken in this step
	}

	@When("the user clicks the service page submit button")
	public void the_user_clicks_on_the_service_page_submit_button() {
		logger.info("Clicking the service page submit button");
		rp.clickOnSendQuery();
	}

	@Then("the error message {string} should appear on the service page for invalid number")
	public void the_error_message_will_appear_in_service_page_for_invalid_number(String expectedMessage) {
		logger.info("Verifying error message for invalid number");
		String actualMessage = rp.getMobileErrorMsg();
		logger.debug("Actual error message: {}", actualMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
		logger.info("Error message for invalid number verified successfully");
	}

	@Then("the error message {string} should appear on the service page for invalid name")
	public void the_error_message_will_appear_in_service_page_for_invalid_name(String expectedMessage) {
		logger.info("Verifying error message for invalid name");
		String actualMessage = rp.getNameErrorMsg();
		logger.debug("Actual error message: {}", actualMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
		logger.info("Error message for invalid name verified successfully");
	}

	@Then("the error message {string} should appear on the service page for invalid name and number")
	public void the_error_message_will_appear_in_service_page_for_invalid_name_and_number(String expectedMessage) {
		logger.info("Verifying error message for invalid name and number");
		String actualMessage = rp.getNameErrorMsg();
		logger.debug("Actual error message: {}", actualMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
		logger.info("Error message for invalid name and number verified successfully");
	}
}
