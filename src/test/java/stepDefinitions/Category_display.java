package stepDefinitions;

import java.util.List;

import hooks.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class Category_display {

	private static final Logger logger = LogManager.getLogger(Category_display.class);
	HomePage hp = new HomePage(BaseTest.getDriver());

	@Given("the user is on the home page")
	public void the_user_enters_the_home_page() {
		logger.info("User navigated to the Home Page");
		System.out.println("Entered home page");
	}

	@When("the user clicks View All Categories")
	public void the_user_clicks_on_the_button() {
		logger.info("User clicked on 'View All Categories' button");
		hp.clickOnCategories();
	}

	@Then("a popup should display all {int} categories")
	public void all_categories_should_appear_in_the_popup(Integer expectedCount) {
		logger.info("Verifying that popup displays {} categories", expectedCount);
		List<String> categories = hp.gatherCategories();
		logger.debug("Categories gathered: {}", categories);
		Assert.assertEquals(categories.size(), expectedCount.intValue(), "Category count mismatch");
		logger.info("Category count matched successfully");
	}
}
