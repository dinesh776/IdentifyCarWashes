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
//Step definitions for verifying category display functionality on the home page.

public class Category_display {
	// Logger instance for logging test execution details
	private static final Logger logger = LogManager.getLogger(Category_display.class);
	// Creating an instance of HomePage using the WebDriver from BaseTest

	HomePage hp = new HomePage(BaseTest.getDriver());
//Step to ensure the user is on the home page.
	@Given("the user is on the home page")
	public void the_user_enters_the_home_page() {
		logger.info("User navigated to the Home Page");
		System.out.println("Entered home page");
	}
//Step where the user clicks on the 'View All Categories'
	@When("the user clicks View All Categories")
	public void the_user_clicks_on_the_button() {
		logger.info("User clicked on 'View All Categories' button");
		// Simulates the click action on the categories button
		hp.clickOnCategories();
	}
	//Step to verify that the popup displays the expected, @param expectedCount The expected number of categories
	@Then("a popup should display all {int} categories")
	public void all_categories_should_appear_in_the_popup(Integer expectedCount) {
		logger.info("Verifying that popup displays {} categories", expectedCount);
		// Fetching the list of categories from the popup
		List<String> categories = hp.gatherCategories();

		// Logging the gathered categories for debugging
		logger.debug("Categories gathered: {}", categories);

		// Assertion to check if the actual count matches the expected count
		Assert.assertEquals(categories.size(), expectedCount.intValue(), "Category count mismatch");

		logger.info("Category count matched successfully");
	}
}
