package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.ResultsPage;
import pages.ServicePage;

public class Show_number_popup {
	ServicePage sp = new ServicePage(BaseTest.getDriver());
	ResultsPage rp = new ResultsPage(BaseTest.getDriver());
	
	@When("all filters are selected accordingly")
	public void all_filters_are_selected_accordingly() {
		sp.clickFilters();
	   sp.setQuickFilter("top rated");
	   sp.applyFilters();
	}

	@When("the user clicks the show number button")
	public void the_user_clicks_on_the_show_number_button() {
	    rp.clickOnShow();
	}

	@Then("the phone number should appear in the popup")
	public void the_phone_number_will_appear_in_the_popup() {
	 Assert.assertNotNull(rp.getContact());
	}

}
