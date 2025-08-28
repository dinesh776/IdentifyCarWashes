package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.ResultsPage;
import pages.ServicePage;

public class Filter_functionality {

	private static final Logger logger = LogManager.getLogger(Filter_functionality.class);
	// Page object for interacting with the service page
	private final ServicePage sp = new ServicePage(BaseTest.getDriver());

	// Page object for interacting with the results page
	private final ResultsPage rp = new ResultsPage(BaseTest.getDriver());

	//Step definition for selecting a rating filter.
	// @param rating The rating value to be selected in the filter.


	@When("the user selects {string} rating in filters")
	public void the_user_selects_a_rating_filter(String rating) {
		logger.info("User selects rating filter: {}", rating);
		sp.clickFilters();   // Open the filter section
		sp.setRating(rating); // Set the desired rating filter
		sp.applyFilters();  //Apply the selected filters
		logger.info("Filters applied successfully");
	}

	//Step definition for validating that all result items meet the rating criteria.
	//@param rating The minimum rating expected for the result items.


	@Then("all result items should have a rating of {double} or higher")
	public void the_result_items_rating_should_be_greater_than_or_equal_to_the_selected_rating(double rating) {
		logger.info("Validating that all result items have a rating >= {}", rating);  // Log validation start

		// Get the rating of the first result item and convert it to double
		double actualRating = Double.parseDouble(rp.getFirstRating());
		logger.debug("First result rating: {}", actualRating);

		// Assert that the actual rating is greater than or equal to the expected rating
		Assert.assertTrue(actualRating >= rating, "Rating is below expected threshold");
		logger.info("Rating validation passed");
	}
}
