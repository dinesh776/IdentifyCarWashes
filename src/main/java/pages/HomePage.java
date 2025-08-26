package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    static final Logger logger=LogManager.getLogger(HomePage.class);
    @FindBy(xpath = "//a[text()='Maybe Later']")
    WebElement popUp;

    @FindBy(id = "main-auto")
    WebElement searchField;

    @FindBy(xpath = "//div[@class='search_button']")
    WebElement searchButton;

    @FindBy(id = "city-auto-sug")
    WebElement location;

    @FindBy(id = "react-autowhatever-city-auto-suggest--item-1")
    WebElement customLocation;

    @FindBy(id="popular_categories")
    WebElement popularCategories;

    @FindBy(xpath = "//div[contains(@class,'sidemenu_text')]")
    List<WebElement> categories;

    @FindBy(id = "pop_close")
    WebElement popClose;

    @FindBy(xpath = "//span[@aria-label='Close Banner']")
    WebElement closeBanner;

    @FindBy(xpath = "//button[contains(@class,'all_filter_container')]")
    WebElement filters;

    @FindBy(xpath = "//div[contains(@class,'page_title')]/h1")
    WebElement serviceMsg;

    public HomePage(WebDriver driver) {
        super(driver);
        logger.info("HomePage initialized.");
    }

    public void handlePopups(){
        popUp.click();
        closeBanner.click();
        logger.info("Pop-ups's Handled");
    }

    public void clickOnCategories(){

        popularCategories.click();
        logger.info("'Categories' button clicked");
    }

    public String getTitle(){
        logger.info("Title Returned");
        return driver.getTitle();
    }

    public List<String> gatherCategories(){
        logger.info("Started collecting category Names");
        List<String> categoriesList=new ArrayList<>();
        for(WebElement e:categories){
            String category=e.getText();
            categoriesList.add(category);
        }
        logger.info("Completed collecting the categories");
        return categoriesList;
    }

    public String getServiceTitle(){
        logger.info("Service title return Successfully");
        return wait.until(ExpectedConditions.visibilityOf(serviceMsg)).getText();
    }


    public void closeCategories(){

        popClose.click();
        logger.info("Pop up handled");
    }


    public void handleLocation(String locationVal){
        if(!locationVal.equalsIgnoreCase("near me")){
            wait.until(ExpectedConditions.elementToBeClickable(location)).click();
            wait.until(ExpectedConditions.visibilityOf(location)).sendKeys(locationVal);
            wait.until(ExpectedConditions.elementToBeClickable(customLocation)).click();
        }
        logger.info("location Handled");
    }

    public void setSearchField(String service){

        searchField.sendKeys(service);
        logger.info("Searching for the Service");
    }

    public void clickOnSearchWithWait(){
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(filters));
        logger.info("successfully filtered the service");
    }

    public void clickOnSearch(){

        searchButton.click();
        logger.info("Click on search Button");
    }





}
