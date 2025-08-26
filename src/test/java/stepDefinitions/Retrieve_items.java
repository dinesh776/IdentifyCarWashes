package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.*;
import model.Service;
import pages.ResultsPage;

import java.util.List;

public class Retrieve_items {
    ResultsPage rp = new ResultsPage(BaseTest.getDriver());

    @Then("the number of items retrieved are")
    public void the_number_of_retrieved_items_should_be_equal_to_the_expected_count() {
        List<Service> l = rp.retrieveServices(5,20);
        System.out.println("There are "+l.size()+" number of items with votes greater than 20");
    }

}
