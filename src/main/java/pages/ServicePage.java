package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServicePage extends BasePage {

    public ServicePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@class,'all_filter_container')]")
    WebElement filters;


//    sort
    @FindBy(xpath = "//span[text()='Relevance']")
    WebElement relevance;

    @FindBy(xpath = "//span[text()='Rating']")
    WebElement rating;

    @FindBy(xpath = "//span[text()='Popular']")
    WebElement popular;

//    Service types
    @FindBy(xpath = "//span[text()='Authorised']")
    WebElement auth;

    @FindBy(xpath = "//span[text()='Door Step']")
    WebElement door;

//    rating
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


    public void clickFilters(){
        filters.click();
    }

    public void setQuickFilter(String quickFilter){
        switch (quickFilter.toLowerCase()){
            case "top rated":topRated.click();break;
            case "quick response":quickResponse.click();break;
            case "jd verified":jdVerified.click();break;
            case "jd trust":jdTrust.click();break;
            default:
                System.out.println("Invalid Quick Filter");break;
        }
    }

    public void sortBy(String sortValue){
        try{
            switch (sortValue.toLowerCase()){
                case "relevance":
                    if (relevance.isDisplayed()) {
                        relevance.click();
                    }
                    break;
                case "rating":
                    if(rating.isDisplayed()){
                        rating.click();
                    }
                    break;
                case "popular":
                    if(popular.isDisplayed()){
                        popular.click();
                    }
                    break;
                default: System.out.println("Invalid sort type");break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setServiceType(String type){
        try {
            switch (type.toLowerCase()){
                case "authorised":
                    if(auth.isDisplayed()){
                        auth.click();
                    }
                    break;
                case "door step":
                    if(door.isDisplayed()){
                        door.click();
                    }
                    break;
                default:
                    System.out.println("Invalid service type");break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setRating(String rating){
        try {
            jsExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;",filtersMenu);
            switch (rating.toLowerCase()){
                case "any":
                    if(rating1.isDisplayed()){
                        rating1.click();
                    }break;
                case "3.5+":
                    if(rating2.isDisplayed()){
                        rating2.click();
                    }break;
                case "4.0+":
                    if(rating3.isDisplayed()){
                        rating3.click();
                    }break;
                case "4.5+":
                    if(rating4.isDisplayed()){
                        rating4.click();
                    }break;
                case "5.0":
                    if(rating5.isDisplayed()){
                        rating5.click();
                    }break;
                default:
                    System.out.println("Invalid rating");break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void applyFilters(){
        applyFilters.click();
    }





}
