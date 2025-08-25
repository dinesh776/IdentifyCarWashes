package pages;

import base.BasePage;
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


    public void closeCategories(){
        popClose.click();
    }


    public void handleLocation(String locationVal){
        if(!locationVal.equalsIgnoreCase("near me")){
            wait.until(ExpectedConditions.elementToBeClickable(location)).click();
            wait.until(ExpectedConditions.visibilityOf(location)).sendKeys(locationVal);
            wait.until(ExpectedConditions.elementToBeClickable(customLocation)).click();
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
