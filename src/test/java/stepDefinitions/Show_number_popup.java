package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.ResultsPage;
import pages.ServicePage;

public class Show_number_popup {


	// Logger instance for logging test execution details
	private static final Logger logger = LogManager.getLogger(Show_number_popup.class);


	// Creating instances of page objects using the WebDriver from BaseTest
	ServicePage sp = new ServicePage(BaseTest.getDriver());
	ResultsPage rp = new ResultsPage(BaseTest.getDriver());


	// Step to select all filters on the service page
	@When("all filters are selected accordingly")
	public void all_filters_are_selected_accordingly() {
		logger.info("Selecting all filters");
		sp.clickFilters();       //Opens the filter section
		sp.setQuickFilter("top rated");   //Applies a specific quick filter
		sp.applyFilters();  //Applies the selected filters
		logger.info("Filters applied successfully");
	}


	// Step to simulate clicking the "Show Number" button
	@When("the user clicks the show number button")
	public void the_user_clicks_on_the_show_number_button() {
		logger.info("User clicks on the 'Show Number' button");
		rp.clickOnShow();   //Triggers the action to show contact number
	}


	// Step to verify that the phone number appears in the popup
	@Then("the phone number should appear in the popup")
	public void the_phone_number_will_appear_in_the_popup() {
		logger.info("Verifying that phone number appears in the popup");
		String contact = rp.getContact();  //Retrieves the contact number from the popup
		logger.debug("Retrieved contact number: {}", contact);
		Assert.assertNotNull(contact, "Phone number should not be null");  //Validates that the contact number is present
		logger.info("Phone number displayed successfully");
	}
}
