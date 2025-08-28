package pages;

import base.BasePage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='Maybe Later']")
    WebElement popUp;

    @FindBy(id = "main-auto")
    WebElement searchField;

    @FindBy(xpath = "//div[@class='search_button']")
    WebElement searchButton;

    @FindBy(id = "city-auto-sug")
    WebElement location;

    @FindBy(xpath = "//div[starts-with(text(),'Trending')]/ancestor::li/following::li[1]")
    WebElement customLocation1;

    @FindBy(id = "react-autowhatever-city-auto-suggest--item-1")
    WebElement customLocation2;

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
    }

    public void handlePopups(){
        popUp.click();
        closeBanner.click();
    }

    public void clickOnCategories(){
        popularCategories.click();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public List<String> gatherCategories(){
        List<String> categoriesList=new ArrayList<>();
        for(WebElement e:categories){
            String category=e.getText();
            categoriesList.add(category);
        }
        return categoriesList;
    }

    public String getServiceTitle(){
        return wait.until(ExpectedConditions.visibilityOf(serviceMsg)).getText();
    }


    public void closeCategories(){
        popClose.click();
    }


    public void handleLocation(String locationVal) {
        if(!locationVal.equalsIgnoreCase("near me")){
            wait.until(ExpectedConditions.elementToBeClickable(location)).click();
            location.sendKeys(locationVal);
            try {
                if(customLocation1.isDisplayed()){
                    customLocation1.click();
                }else{
                    customLocation2.click();
                }
            }catch (StaleElementReferenceException e){
                if(customLocation1.isDisplayed()){
                    customLocation1.click();
                }else{
                    customLocation2.click();
                }
            }
        }
    }

    public void setSearchField(String service){
        searchField.sendKeys(service);
    }

    public void clickOnSearchWithWait(){
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(filters));
    }

    public void clickOnSearch(){
        searchButton.click();
    }





}
