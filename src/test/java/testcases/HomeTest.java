package testcases;

import org.testng.annotations.Test;

import hooks.BaseTest;
import pages.HomePage;
import pages.ResultsPage;
import pages.ServicePage;
import pages.FreeListingPage;

import java.util.List;


public class HomeTest extends BaseTest {

    @Test()
    public void testHome(){
        HomePage homePage=new HomePage(driver);
        homePage.handlePopups();

        homePage.clickOnCategories();
        List<String>s=homePage.gatherCategories();
        System.out.println(s);
        homePage.closeCategories();

        homePage.handleLocation("Old Gajuwaka,Visakhapatnam");
        homePage.setSearchField("Car Wash Services");
        homePage.clickOnSearch();
    }
  
    @Test(priority = 1)
    public void testServicePage(){
        ServicePage servicePage=new ServicePage(driver);
        servicePage.clickFilters();
        servicePage.setQuickFilter("top rated");
        servicePage.sortBy("rating");
        servicePage.setRating("4.5+");
        servicePage.applyFilters();
    }

    @Test(priority = 2)
    public void getResults(){
        ResultsPage resultsPage=new ResultsPage(driver);
//        resultsPage.setName("dinesh");
//        resultsPage.clickOnSendQuery();
//        System.out.println("Step 1 :"+resultsPage.getMobileErrorMsg());
//
//        resultsPage.setName("");
//        driver.navigate().refresh();
//        resultsPage.setMobileNumber("9876543210");
//        resultsPage.clickOnSendQuery();
//        System.out.println("Step 2 : "+resultsPage.getNameErrorMsg());
//
//        resultsPage.setName("d");
//        resultsPage.setMobileNumber("9876543212");
//        resultsPage.clickOnSendQuery();
//        System.out.println("Step 3 : "+resultsPage.getWelcomeMsg());
//
//        resultsPage.closeSendQueryDialog();

        System.out.println(resultsPage.retrieveServices(5,20));

    }
  
//    @Test(priority=2)
    public void freeListing()  {
        FreeListingPage flp = new FreeListingPage(driver);
        flp.freeListingPage();
        flp.mobileNumber("987654321");
        flp.startNow();
        System.out.println(flp.errorDisplay());
    }

}
