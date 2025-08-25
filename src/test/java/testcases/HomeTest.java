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

//  @Test(priority=0)
//    public void homeTest(){
//      HomePage homePage=new HomePage(driver);
//       homePage.handlePopups();
//        homePage.handleLocation("Old Gajuwaka,Visakhapatnam");
//        homePage.setSearchField("Car Wash Services");
//        homePage.clickOnSearchWithWait();
//
//      }
//    }

//  public static void main(String[] args) {
//    List<HashMap<String ,String>> res=ExcelUtility.getTestData();
//    System.out.println("| S.No  | Location  | Service Name  | Quick Filters | Sort By | Ratings |");
//    for(HashMap<String,String> m : res){
//      System.out.println("| "+m.get("S.No")+" | "+m.get("Location")+" | "+m.get("Service Name")+" | "+m.get("Quick Filters")+" | "+m.get("Sort By")+" | "+m.get("Ratings")+" |");
//      System.out.println();
//  }}


  
//    @Test(priority = 1)
//    public void testServicePage(){
//        ServicePage servicePage=new ServicePage(driver);
//        servicePage.clickFilters();
//        servicePage.setQuickFilter("top rated");
//        servicePage.sortBy("rating");
//        servicePage.setRating("4.5+");
//        servicePage.applyFilters();
//    }
//
//    @Test(priority = 2)
//    public void getResults(){
//        ResultsPage resultsPage=new ResultsPage(driver);
////        resultsPage.setName("dinesh");
////        resultsPage.clickOnSendQuery();
////        System.out.println("Step 1 :"+resultsPage.getMobileErrorMsg());
////
////        resultsPage.setName("");
////        driver.navigate().refresh();
////        resultsPage.setMobileNumber("9876543210");
////        resultsPage.clickOnSendQuery();
////        System.out.println("Step 2 : "+resultsPage.getNameErrorMsg());
////
////        resultsPage.setName("d");
////        resultsPage.setMobileNumber("9876543212");
////        resultsPage.clickOnSendQuery();
////        System.out.println("Step 3 : "+resultsPage.getWelcomeMsg());
////
////        resultsPage.closeSendQueryDialog();
//
//        System.out.println(resultsPage.retrieveServices(5,20));
//
//    }
//
////    @Test(priority=2)
//    public void freeListing()  {
//        FreeListingPage flp = new FreeListingPage(driver);
//        flp.freeListingPage();
//        flp.mobileNumber("987654321");
//        flp.startNow();
//        System.out.println(flp.errorDisplay());
//    }

}
