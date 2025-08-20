package testcases;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.ServicePage;
import pages.FreeListingPage;


public class HomeTest extends BaseTest {

    @Test(priority = 0)
    public void testHome(){
        HomePage homePage=new HomePage(driver);
        homePage.handlePopup();
        homePage.setSearchField("Car Wash Services");
        homePage.clickOnSearch();
        driver.switchTo().defaultContent();
    }
  
    @Test(priority = 1)
    public void testServicePage(){
        ServicePage servicePage=new ServicePage(driver);
        servicePage.clickFilters();
        servicePage.setQuickFilter("top rated");
        servicePage.sortBy("rating");
        servicePage.setServiceType("authorised");
        servicePage.setRating("4.5+");
        servicePage.applyFilters();
    }
  
    @Test(priority=2)
    public void freeListing()  {
        FreeListingPage flp = new FreeListingPage(driver);
        flp.freeListingPage();
        flp.mobileNumber("987654321");
        flp.startNow();
        System.out.println(flp.errorDisplay());
        flp.handlingPopUp();
    }

}
