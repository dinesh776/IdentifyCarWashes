package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Do;
import junit.framework.Assert;
import pages.HomePage;
import pages.ResultsPage;
import pages.ServicePage;

public class Filter_functionality {
	private ServicePage sp = new ServicePage(BaseTest.getDriver());
	private ResultsPage rp = new ResultsPage(BaseTest.getDriver());
	
	@When("the user selects {string} rating in filters")
	public void the_user_selects_a_rating_filter(String rating) {
		sp.clickFilters();
	   sp.setRating(rating);
	   sp.applyFilters();
	}

	@Then("the result items rating should be greater than or equal to the {double} rating")
	public void the_result_items_rating_should_be_greater_than_or_equal_to_the_selected_rating(double rating) {
	    Assert.assertTrue(Double.parseDouble(rp.getFirstRating())>rating);
	}
	



}
