package stepDefinitions;

import java.util.List;

import hooks.BaseTest;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class Category_display {
	HomePage hp = new HomePage(BaseTest.getDriver());
	
	@Given("the user is on the home page")
	public void the_user_enters_the_home_page() {
	   System.out.println("Entered home page");
	}

	@When("the user clicks View All Categories")
	public void the_user_clicks_on_the_button() {
		hp.clickOnCategories();
	}

	@Then("a popup should display all {int} categories")
	public void all_categories_should_appear_in_the_popup(Integer int1) {
		List<String> s=hp.gatherCategories();
		Assert.assertEquals(s.size(),int1);
	}

}
