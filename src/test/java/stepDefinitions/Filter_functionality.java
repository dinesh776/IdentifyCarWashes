package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.ResultsPage;
import pages.ServicePage;

public class Filter_functionality {
	private final ServicePage sp = new ServicePage(BaseTest.getDriver());
	private final ResultsPage rp = new ResultsPage(BaseTest.getDriver());
	
	@When("the user selects {string} rating in filters")
	public void the_user_selects_a_rating_filter(String rating) {
		sp.clickFilters();
	   sp.setRating(rating);
	   sp.applyFilters();
	}

	@Then("all result items should have a rating of {double} or higher")
	public void the_result_items_rating_should_be_greater_than_or_equal_to_the_selected_rating(double rating) {
	    Assert.assertTrue(Double.parseDouble(rp.getFirstRating())>=rating);
	}
	



}
