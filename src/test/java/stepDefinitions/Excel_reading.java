package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import model.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ResultsPage;
import pages.ServicePage;
import utilities.ConfigReader;
import utilities.ExcelUtility;
import utilities.JsonUtility;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
//Step definitions for reading test data from Excel, performing service search,
        //applying filters, retrieving results, and saving them to a JSON file.


        public class Excel_reading {

            // Logger instance for logging test execution details
            private static final Logger logger = LogManager.getLogger(Excel_reading.class);

            // HashMap to store test data fetched from Excel
    HashMap<String, String> data;

            // Page object instances for interacting with different pages
            HomePage hp = new HomePage(BaseTest.getDriver());
            ServicePage sp = new ServicePage(BaseTest.getDriver());
            ResultsPage rp = new ResultsPage(BaseTest.getDriver());
            // List to store retrieved services
            List<Service> res;

            //Step to read data from Excel and perform search
            //@param i Index of the test data row in Excel
    @When("the user enters a location and a valid service with required filters with index {int}")
    public void the_user_enters_the_location_and_a_valid_service(int i) {
        logger.info("Fetching test data from Excel for index: {}", i);
        data = ExcelUtility.getData(i - 1); // Convert to 0-based index

        //Enter Location
        logger.info("Entering location: {}", data.get("Location"));
        hp.handleLocation(data.get("Location"));

        //Enter service name
        logger.info("Entering service name: {}", data.get("Service Name"));
        hp.setSearchField(data.get("Service Name"));

        logger.info("Clicking search button");
        hp.clickOnSearchWithWait();

        logger.info("Applying filters: Quick Filter = {}, Sort By = {}, Ratings = {}",
                data.get("Quick Filters"), data.get("Sort By"), data.get("Ratings"));

        sp.clickFilters();
        sp.setQuickFilter(data.get("Quick Filters"));
        sp.sortBy(data.get("Sort By"));
        sp.setRating(data.get("Ratings"));
        sp.applyFilters();
    }

    //Step to validate that the user is redirected to the correct service page.

            @Then("user redirected to the specified service page")
    public void validate_service_page() {
        logger.info("Validating redirection to service page for: {}", data.get("Service Name"));
        Assert.assertTrue(hp.getServiceTitle().contains(data.get("Service Name")),
                "Service page title does not contain expected service name");
        logger.info("Redirection successful");
    }

    //Step to retrieve a list of services based on configuration settings.

            @Then("Retrieve services")
    public void retrieve_services() {
        int count = Integer.parseInt(ConfigReader.getNumberOfServices());
        int votes = Integer.parseInt(ConfigReader.getExpectedVotes());
        logger.info("Retrieving top {} services with minimum {} votes", count, votes);
                // Fetch services based on criteria
        res = rp.retrieveServices(count, votes);
        logger.debug("Retrieved services: {}", res);
    }
            //Step to save the retrieved services to a JSON file.

            @Then("Save services to json file")
    public void save_data() {
        logger.info("Saving services to JSON file for service: {}", data.get("Service Name"));
        JsonUtility.writeJson(res, data.get("Service Name"));  // Write data to JSON
    }

        //Hook to finalize and save the JSON file after scenarios tagged with @SaveJson.

            @After("@SaveJson")
    public void save_json() {
        logger.info("Finalizing and saving JSON file");
        JsonUtility.saveJson(); //// Final save operation
    }
}
