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

	private static final Logger logger = LogManager.getLogger(Show_number_popup.class);
	ServicePage sp = new ServicePage(BaseTest.getDriver());
	ResultsPage rp = new ResultsPage(BaseTest.getDriver());

	@When("all filters are selected accordingly")
	public void all_filters_are_selected_accordingly() {
		logger.info("Selecting all filters");
		sp.clickFilters();
		sp.setQuickFilter("top rated");
		sp.applyFilters();
		logger.info("Filters applied successfully");
	}

	@When("the user clicks the show number button")
	public void the_user_clicks_on_the_show_number_button() {
		logger.info("User clicks on the 'Show Number' button");
		rp.clickOnShow();
	}

	@Then("the phone number should appear in the popup")
	public void the_phone_number_will_appear_in_the_popup() {
		logger.info("Verifying that phone number appears in the popup");
		String contact = rp.getContact();
		logger.debug("Retrieved contact number: {}", contact);
		Assert.assertNotNull(contact, "Phone number should not be null");
		logger.info("Phone number displayed successfully");
	}
}
