package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.HomePage;
import pages.ServicePage;
import utilities.ExcelUtility;

public class Search_functionality {

	private static final Logger logger = LogManager.getLogger(Search_functionality.class);

	HomePage hp = new HomePage(BaseTest.getDriver());

	@Given("all popups are handled")
	public void handles_any_popups() {
		logger.info("Handling popups on the home page");
		hp.handlePopups();
	}

	@When("the user enters a location {string} and a valid service {string}")
	public void the_user_enters_the_location_and_a_service(String l, String s) {
		logger.info("Entering location: {} and service: {}", l, s);
		hp.handleLocation(l);
		hp.setSearchField(s);
	}

	@When("the user clicks the search button")
	public void the_user_clicks_on_the_search_button() {
		logger.info("Clicking the search button with valid data");
		hp.clickOnSearchWithWait();
	}

	@When("the user clicks the search button with invalid data")
	public void user_clicks_on_the_search_button() {
		logger.info("Clicking the search button with invalid data");
		hp.clickOnSearch();
	}

	@Then("the user should be redirected to the service page")
	public void the_user_is_redirected_to_the_service_page() {
		logger.info("Verifying redirection to the service page");
		if (hp.getTitle().contains("Car Washing Services")) {
			logger.info("Redirection successful");
			Assert.assertTrue(true);
		} else {
			logger.error("Redirection failed");
			Assert.fail();
		}
	}

	@When("the user enters a location and leaves the service field empty")
	public void the_user_enters_the_location_and_leaves_the_service_field_empty() {
		logger.info("Entering location with empty service field");
		hp.handleLocation("coimbatore");
	}

	@Then("the user should remain on the home page")
	public void the_error_message_will_appear_in_home_page() {
		logger.info("Verifying user remains on the home page");
		Assert.assertEquals("Find Businesses Near You on Local Search Engine - Justdial", hp.getTitle());
	}
}
