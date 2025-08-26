package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.HomePage;

public class Search_functionality {


	// Logger instance for logging test execution details
	private static final Logger logger = LogManager.getLogger(Search_functionality.class);

	// Creating an instance of HomePage using the WebDriver from BaseTest
	HomePage hp = new HomePage(BaseTest.getDriver());


	// Step to handle any popups that may appear on the home page
	@Given("all popups are handled")
	public void handles_any_popups() {
		logger.info("Handling popups on the home page");
		hp.handlePopups(); // Calls method to close or bypass popups
	}


	// Step to enter location and service into the search fields
	@When("the user enters a location {string} and a valid service {string}")
	public void the_user_enters_the_location_and_a_service(String l, String s) {
		logger.info("Entering location: {} and service: {}", l, s);
		hp.handleLocation(l); //Sets the Location field
		hp.setSearchField(s); // Sets the service field
	}


	// Step to click the search button with valid data
	@When("the user clicks the search button")
	public void the_user_clicks_on_the_search_button() {
		logger.info("Clicking the search button with valid data");
		hp.clickOnSearchWithWait(); // Clicks the search button and waits for
	}


	// Step to click the search button with invalid data
	@When("the user clicks the search button with invalid data")
	public void user_clicks_on_the_search_button() {
		logger.info("Clicking the search button with invalid data");
		hp.clickOnSearch();   // Clicks the search button without waiting
	}


	// Step to verify redirection to the service page
	@Then("the user should be redirected to the service page")
	public void the_user_is_redirected_to_the_service_page() {
		logger.info("Verifying redirection to the service page");
		if (hp.getTitle().contains("Car Washing Services")) {
			logger.info("Redirection successful");
			Assert.assertTrue(true);    // Test passes if title contains expected text
		} else {
			logger.error("Redirection failed");
			Assert.fail();   // Test fails if title does not match
		}
	}


	// Step to enter location while leaving the service field empty
	@When("the user enters a location and leaves the service field empty")
	public void the_user_enters_the_location_and_leaves_the_service_field_empty() {
		logger.info("Entering location with empty service field");
		hp.handleLocation("coimbatore");  //Sets Location only
	}


	// Step to verify that the user remains on the home page
	@Then("the user should remain on the home page")
	public void the_error_message_will_appear_in_home_page() {
		logger.info("Verifying user remains on the home page");
		Assert.assertEquals("Find Businesses Near You on Local Search Engine - Justdial", hp.getTitle());   // Validates page title
	}
}
