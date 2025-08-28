package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.*;
import model.Service;
import pages.ResultsPage;
import pages.ServicePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Retrieve_items {


    // Logger instance for logging information and debugging
    private static final Logger logger = LogManager.getLogger(Retrieve_items.class);

    // Creating an instance of ResultsPage using the WebDriver from BaseTest
    ResultsPage rp = new ResultsPage(BaseTest.getDriver());


    // Step definition for verifying the number of items retrieved
    @Then("the number of items retrieved are")
    public void the_number_of_retrieved_items_should_be_equal_to_the_expected_count() {

        // Logging the start of the retrieval process
        logger.info("Retrieving services with minimum vote threshold");

        // Retrieving services with votes between 5 and 20
        List<Service> services = rp.retrieveServices(5, 20);
        logger.info("Number of services retrieved: {}", services.size());

        // Logging the actual list of services for debugging purposes
        logger.debug("Retrieved services: {}", services);
        System.out.println("There are " + services.size() + " number of items with votes greater than 20"); // result to the console
    }
}
