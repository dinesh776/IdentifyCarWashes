package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ServicePage;

public class HomeTest extends BaseTest {

    @Test(priority = 1)
    public void testHome(){
        HomePage homePage=new HomePage(driver);
        homePage.handlePopup();
        homePage.setSearchField("Car Wash Services");
        homePage.clickOnSearch();
    }

    @Test(priority = 2)
    public void testServicePage(){
        ServicePage servicePage=new ServicePage(driver);
        servicePage.clickFilters();
        servicePage.setQuickFilter("top rated");
        servicePage.sortBy("rating");
        servicePage.setServiceType("authorised");
        servicePage.setRating("4.5+");
        servicePage.applyFilters();
    }

}
