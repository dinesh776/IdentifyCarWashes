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


	// Logger instance for logging test execution details
	private static final Logger logger = LogManager.getLogger(Service_page_query.class);

	// Creating an instance of ResultsPage using the WebDriver from BaseTest
	ResultsPage rp = new ResultsPage(BaseTest.getDriver());


	// Step to enter a valid name and valid phone number
	@When("the user enters a valid name and valid phone number into the fields")
	public void the_user_enters_a_valid_name_and_valid_phone_number_into_the_fields() {
		logger.info("Entering valid name and phone number");
		rp.setName("bharath"); //sets the name field
		rp.setMobileNumber("9876345342"); //Sets the mobile number field
	}


	// Step to verify that the OTP window appears
	@Then("the OTP window should appear")
	public void the_otp_window_will_appear() {
		logger.info("Verifying OTP window appearance");
		Assert.assertEquals("Welcome", rp.getWelcomeMsg());
		logger.info("OTP window appeared successfully");
	}


	// Step to enter a valid name and an invalid phone number
	@When("the user enters a valid name and an invalid phone number into the fields")
	public void the_user_enters_a_valid_name_and_an_invalid_phone_number_into_the_fields() {
		logger.info("Entering valid name and invalid phone number");
		rp.setName("bharath");
	}


	// Step to enter both invalid name and valid phone number
	@When("the user enters an invalid name and a valid phone number into the fields")
	public void the_user_enters_an_invalid_name_and_a_valid_phone_number_into_the_fields() {
		logger.info("Entering invalid name and valid phone number");
		rp.setMobileNumber("3333333333"); // Only sets mobile number; name is assumed invalid
	}


	// Step to enter both invalid name and phone number
	@When("the user enters an invalid name and an invalid phone number into the fields")
	public void the_user_enters_an_invalid_name_and_an_invalid_phone_number_into_the_fields() {
		logger.info("Entering invalid name and invalid phone number");
		// No action taken; assumes fields are left empty or invalid
		// No action taken in this step
	}


	// Step to click the submit button on the service page
	@When("the user clicks the service page submit button")
	public void the_user_clicks_on_the_service_page_submit_button() {
		logger.info("Clicking the service page submit button");
		rp.clickOnSendQuery();  //Submits the form
	}


	// Step to verify error message for invalid phone number
	@Then("the error message {string} should appear on the service page for invalid number")
	public void the_error_message_will_appear_in_service_page_for_invalid_number(String expectedMessage) {
		logger.info("Verifying error message for invalid number");
		String actualMessage = rp.getMobileErrorMsg(); //// Retrieves error message for mobile
		logger.debug("Actual error message: {}", actualMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
		logger.info("Error message for invalid number verified successfully");
	}


	// Step to verify error message for invalid name
	@Then("the error message {string} should appear on the service page for invalid name")
	public void the_error_message_will_appear_in_service_page_for_invalid_name(String expectedMessage) {
		logger.info("Verifying error message for invalid name");
		String actualMessage = rp.getNameErrorMsg(); // Retrieves error message for mobile number
		logger.debug("Actual error message: {}", actualMessage);
		Assert.assertEquals(expectedMessage, actualMessage); //Validates error message
		logger.info("Error message for invalid name verified successfully");
	}



	// Step to verify error message for both invalid name and number
	@Then("the error message {string} should appear on the service page for invalid name and number")
	public void the_error_message_will_appear_in_service_page_for_invalid_name_and_number(String expectedMessage) {
		logger.info("Verifying error message for invalid name and number");
		String actualMessage = rp.getNameErrorMsg();   // Assumes name error message is shown for both
		logger.debug("Actual error message: {}", actualMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
		logger.info("Error message for invalid name and number verified successfully");
	}
}
