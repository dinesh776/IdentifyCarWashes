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


/**
 * Page Object class representing the Home Page of the application.
 * Inherits common functionality from BasePage.
 */
public class HomePage extends BasePage {
     static final Logger logger=LogManager.getLogger(HomePage.class);
    // Constructor initializes WebDriver and page elements
    public HomePage(WebDriver driver) {
        super(driver);
        logger.info("HomePage initialized.");
    }

    // WebElement for dismissing the initial popup
    @FindBy(xpath = "//a[text()='Maybe Later']")
    WebElement popUp;


    // WebElement for the search input field
    @FindBy(id = "main-auto")
    WebElement searchField;


    // WebElement for the search button
    @FindBy(xpath = "//div[@class='search_button']")
    WebElement searchButton;


    // WebElement for the location input field
    @FindBy(id = "city-auto-sug")
    WebElement location;


    // WebElement for selecting a custom location from suggestions
    @FindBy(id = "react-autowhatever-city-auto-suggest--item-1")
    WebElement customLocation;


    // WebElement for popular categories section
    @FindBy(id="popular_categories")
    WebElement popularCategories;


    // List of category elements in the side menu
    @FindBy(xpath = "//div[contains(@class,'sidemenu_text')]")
    List<WebElement> categories;


    // WebElement to close the category popup
    @FindBy(id = "pop_close")
    WebElement popClose;


    // WebElement to close the banner popup
    @FindBy(xpath = "//span[@aria-label='Close Banner']")
    WebElement closeBanner;


    // WebElement for filters section
    @FindBy(xpath = "//button[contains(@class,'all_filter_container')]")
    WebElement filters;


    // WebElement for the service title message
    @FindBy(xpath = "//div[contains(@class,'page_title')]/h1")
    WebElement serviceMsg;



    /**
     * Handles popups that appear on the home page.
     */
    public void handlePopups(){
        popUp.click();
        closeBanner.click();
        logger.info("Pop-ups's Handled");
    }


    /**
     * Clicks on the popular categories section.
     */
    public void clickOnCategories(){

        popularCategories.click();
        logger.info("'Categories' button clicked");
    }


    /**
     * Retrieves the current page title.
     * @return Title of the current page
     */
    public String getTitle(){
        logger.info("Title Returned");
        return driver.getTitle();
    }


    /**
     * Gathers all category names from the side menu.
     * @return List of category names
     */
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


    /**
     * Retrieves the service title displayed on the results page.
     * @return Service title text
     */
    public String getServiceTitle(){
        logger.info("Service title return Successfully");
        return wait.until(ExpectedConditions.visibilityOf(serviceMsg)).getText();
    }

// closes the categories popup
    public void closeCategories(){

        popClose.click();
        logger.info("Pop up handled");
    }



    /**
     * Handles location input and selection from suggestions.
     * @param locationVal Location value to be entered
     */
    public void handleLocation(String locationVal){
        if(!locationVal.equalsIgnoreCase("near me")){
            wait.until(ExpectedConditions.elementToBeClickable(location)).click();
            wait.until(ExpectedConditions.visibilityOf(location)).sendKeys(locationVal);
            wait.until(ExpectedConditions.elementToBeClickable(customLocation)).click();
        }
        logger.info("location Handled");
    }


    /**
     * Sets the service name in the search field.
     * @param service Service name to be searched
     */
    public void setSearchField(String service){

        searchField.sendKeys(service);
        logger.info("Searching for the Service");
    }


    /**
     * Clicks the search button and waits for filters to be visible.
     */
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
