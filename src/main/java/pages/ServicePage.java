package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * This class represents the Service Page and provides methods to interact with
 * filter and sorting options for services listed on the page.
 */
public class ServicePage extends BasePage {
    private static final Logger logger= LogManager.getLogger(ServicePage.class);
    public ServicePage(WebDriver driver) {
        super(driver);
        logger.info("Service Page initialized");
    }

    // Main filter button
    @FindBy(xpath = "//button[contains(@class,'all_filter_container')]")
    WebElement filters;


 // Sorting options
    @FindBy(xpath = "//span[text()='Relevance']")
    WebElement relevance;

    @FindBy(xpath = "//span[text()='Rating']")
    WebElement rating;

    @FindBy(xpath = "//span[text()='Popular']")
    WebElement popular;


//    Rating filter options
    @FindBy(xpath = "//span[text()='Any']")
    WebElement rating1;

    @FindBy(xpath = "//span[text()='3.5+']")
    WebElement rating2;

    @FindBy(xpath = "//span[text()='4.0+']")
    WebElement rating3;

    @FindBy(xpath = "//span[text()='4.5+']")
    WebElement rating4;

    @FindBy(xpath = "//span[text()='5.0']")
    WebElement rating5;

//    apply & reset buttons

    @FindBy(xpath = "//button[text()='Apply Filters']")
    WebElement applyFilters;

    @FindBy(xpath = "//div[contains(@class,'inside_content_jd')]")
    WebElement filtersMenu;

//      Quick Filters
    @FindBy(xpath = "//span[@aria-label='Top Rated']")
    WebElement topRated;

    @FindBy(xpath = "//span[@aria-label='Quick Response']")
    WebElement quickResponse;

    @FindBy(xpath = "//span[@aria-label='Jd Verified']")
    WebElement jdVerified;

    @FindBy(xpath = "//span[@aria-label='Jd Trust']")
    WebElement jdTrust;


    //Clicks the main filter button to open the filter menu.
    public void clickFilters(){

        filters.click();
        logger.info("Click On 'Apply Filters' button");
    }


    /**
     * Applies a quick filter based on the provided filter name.
     * @param quickFilter The name of the quick filter to apply.
     */

    public void setQuickFilter(String quickFilter){
        if(quickFilter.equalsIgnoreCase("none")){
            logger.info("Return Back to Service page");
            return;
        }
        try{
            switch (quickFilter.toLowerCase()){
                case "top rated":topRated.click();
                logger.info("Toprated clicked");
                break;
                case "quick response":quickResponse.click();
                    logger.info("Quick Response clicked");break;
                case "jd verified":jdVerified.click();
                    logger.info("jd Verified clicked");
                break;
                case "jd trust":jdTrust.click();
                    logger.info("jd trust clicked");break;
                default:
                    logger.error("Invalid Quick Filter");break;
            }
        }catch (Exception e){
            logger.error(quickFilter + " is not available in filters...");
        }

    }


    /**
     * Sorts the services based on the selected sort value.
     * @param sortValue The sorting criteria (e.g., "relevance", "rating", "popular").
     */

    public void sortBy(String sortValue){
        if(sortValue.equalsIgnoreCase("none")){
            logger.info("Return Back to Service page");
            return;
        }
        try{
            switch (sortValue.toLowerCase()){
                case "relevance":
                    if (relevance.isDisplayed()) {
                        relevance.click();
                        logger.info("relevance clicked");
                    }
                    break;
                case "rating":
                    if(rating.isDisplayed()){
                        rating.click();
                        logger.info("Rating clicked");
                    }
                    break;
                case "popular":
                    if(popular.isDisplayed()){
                        popular.click();
                        logger.info("popular clicked");
                    }
                    break;
                default: logger.error("Invalid sort type");break;
            }
        }catch (Exception e){
            logger.error(sortValue+" sort type is not available in filters..");
        }

    }


    /**
     * Sets the rating filter based on the provided rating value.
     * @param rating The rating filter to apply (e.g., "4.0+", "5").
     */
    public void setRating(String rating){
        if(rating.equalsIgnoreCase("none")){
            logger.info("Return back to Service page");
            return;
        }
        try {
            switch (rating.toLowerCase()){
                case "any":
                    if(rating1.isDisplayed()){
                        jsExecutor.executeScript("arguments[0].click();",rating1);
                    }
                    logger.info("'any' is selected in the rating");
                    break;
                case "3.5+":
                    if(rating2.isDisplayed()){
                        jsExecutor.executeScript("arguments[0].click();",rating2);
                    }
                    logger.info("'3.5' is selected in the rating");
                    break;
                case "4.0+":
                    if(rating3.isDisplayed()){
                        jsExecutor.executeScript("arguments[0].click();",rating3);
                    }
                    logger.info("'4.0' is selected in the rating");
                    break;
                case "4.5+":
                    if(rating4.isDisplayed()){
                        jsExecutor.executeScript("arguments[0].click();",rating4);
                    }
                    logger.info("'4.5' is selected in the rating");
                    break;
                case "5":
                    if(rating5.isDisplayed()){
                        jsExecutor.executeScript("arguments[0].click();",rating5);
                    }
                    logger.info("'5.0' is selected in the rating");
                    break;
                default:
                    logger.info("Invalid rating");break;
            }
        }catch (Exception e){
            logger.error(rating+" rating is not available in filters ...");
        }
    }

    //Clicks the "Apply Filters" button to apply selected filters.

    public void applyFilters(){

        applyFilters.click();
        logger.info("'apply filter' button is clicked");
    }





}
