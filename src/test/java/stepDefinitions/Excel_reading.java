package stepDefinitions;

import hooks.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import model.Service;
import pages.HomePage;
import pages.ResultsPage;
import pages.ServicePage;
import utilities.ConfigReader;
import utilities.ExcelUtility;
import utilities.JsonUtility;

import java.util.HashMap;
import java.util.List;

public class Excel_reading {
    HashMap<String,String> data;
    HomePage hp=new HomePage(BaseTest.getDriver());
    ServicePage sp=new ServicePage(BaseTest.getDriver());
    ResultsPage rp=new ResultsPage(BaseTest.getDriver());
    List<Service> res;

    @When("the user enters a location and a valid service with required filters with index {int}")
    public void the_user_enters_the_location_and_a_valid_service(int i) {
        data= ExcelUtility.getData(i-1);
        hp.handleLocation(data.get("Location"));
        hp.setSearchField(data.get("Service Name"));
        hp.clickOnSearchWithWait();

        sp.clickFilters();
        sp.setQuickFilter(data.get("Quick Filters"));
        sp.sortBy(data.get("Sort By"));
        sp.setRating(data.get("Ratings"));
        sp.applyFilters();
    }
    @Then("user redirected to the specified service page")
    public void validate_service_page(){
        Assert.assertTrue(hp.getServiceTitle().contains(data.get("Service Name")));
    }

    @Then("Retrieve services")
    public void retrieve_services(){
        res=rp.retrieveServices(Integer.parseInt(ConfigReader.getNumberOfServices()),Integer.parseInt(ConfigReader.getExpectedVotes()));
    }

    @Then("Save services to json file")
    public void save_data(){
        JsonUtility.writeJson(res,data.get("Service Name"));
    }


    @After("@SaveJson")
    public void save_json(){
        JsonUtility.saveJson();
    }
}
