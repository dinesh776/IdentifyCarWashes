package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import model.Service;
import pages.ResultsPage;
import pages.ServicePage;

import java.util.List;

public class Retrieve_items {
    ServicePage sp = new ServicePage(BaseTest.getDriver());
    ResultsPage rp = new ResultsPage(BaseTest.getDriver());

    @Then("the number of retrieved items should be equal to the expected count")
    public void the_number_of_retrieved_items_should_be_equal_to_the_expected_count() {
        List<Service> l = rp.retrieveServices(5,20);
        Assert.assertEquals(5,l.size());
    }

}
