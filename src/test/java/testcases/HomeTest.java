package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hooks.BaseTest;
import pages.HomePage;
import pages.ResultsPage;
import pages.ServicePage;
import pages.FreeListingPage;
import utilities.ExcelUtility;

import java.util.HashMap;
import java.util.List;


public class HomeTest extends BaseTest {

    @DataProvider(name = "dp")
    public Object[][] getData(){
        List<HashMap<String,String>> data= ExcelUtility.getTestData();

        Object[][] da=new Object[10][6];

        for(int i=0;i<10;i++){
            da[i][0]=data.get(i).get("S.No");
            da[i][1]=data.get(i).get("Location");
            da[i][2]=data.get(i).get("Service Name");
            da[i][3]=data.get(i).get("Quick Filters");
            da[i][4]=data.get(i).get("Sort By");
            da[i][5]=data.get(i).get("Ratings");
        }
        return da;
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
