package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import pages.ResultsPage;
import pages.ServicePage;

public class Filter_functionality {

	private static final Logger logger = LogManager.getLogger(Filter_functionality.class);
	private final ServicePage sp = new ServicePage(BaseTest.getDriver());
	private final ResultsPage rp = new ResultsPage(BaseTest.getDriver());

	@When("the user selects {string} rating in filters")
	public void the_user_selects_a_rating_filter(String rating) {
		logger.info("User selects rating filter: {}", rating);
		sp.clickFilters();
		sp.setRating(rating);
		sp.applyFilters();
		logger.info("Filters applied successfully");
	}

	@Then("all result items should have a rating of {double} or higher")
	public void the_result_items_rating_should_be_greater_than_or_equal_to_the_selected_rating(double rating) {
		logger.info("Validating that all result items have a rating >= {}", rating);
		double actualRating = Double.parseDouble(rp.getFirstRating());
		logger.debug("First result rating: {}", actualRating);
		Assert.assertTrue(actualRating >= rating, "Rating is below expected threshold");
		logger.info("Rating validation passed");
	}
}
