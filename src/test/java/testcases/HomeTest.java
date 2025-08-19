package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTest {

    @Test
    public void testHome(){
        HomePage homePage=new HomePage(driver);
        homePage.handlePopup();
        homePage.setSearchField("Car Wash Services");
        homePage.clickOnSearch();
    }

}
