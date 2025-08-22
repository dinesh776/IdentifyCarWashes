package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.HomePage;
import pages.ServicePage;

public class Search_functionality {
	
	HomePage hp = new HomePage(BaseTest.getDriver());
	ServicePage sp = new ServicePage(BaseTest.getDriver());
	
	@Given("handles any popups")
	public void handles_any_popups() {
	   hp.handlePopups();
	}

	@When("the user enters the location and a valid service")
	public void the_user_enters_the_location_and_a_valid_service() {
	    hp.handleLocation("coimbatore");
	    hp.setSearchField("Car Wash Services");
		
	}

	@When("the user clicks on the search button")
	public void the_user_clicks_on_the_search_button(){
		hp.clickOnSearchWithWait();
	}

	@When("the user clicks on the search button with invalid data")
	public void user_clicks_on_the_search_button(){
		hp.clickOnSearch();
	}

	@Then("the user is redirected to the service page")
	public void the_user_is_redirected_to_the_service_page() {
		if(hp.getTitle().contains("Car Washing Services")){
			Assert.assertTrue(true);
		}else{
            Assert.fail();
		}


	}

	@When("the user enters the location and leaves the service field empty")
	public void the_user_enters_the_location_and_leaves_the_service_field_empty() {
		 hp.handleLocation("coimbatore");
	}

	@Then("the user stays in the home page")
	public void the_error_message_will_appear_in_home_page() {
		Assert.assertEquals("Find Businesses Near You on Local Search Engine - Justdial", hp.getTitle());
	}


}
