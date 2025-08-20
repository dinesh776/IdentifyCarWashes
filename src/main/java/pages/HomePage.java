package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void handlePopup(){
        popUp.click();
    }

    public void handleLocation(String locationVal){
        if(!locationVal.equalsIgnoreCase("near me")){
            location.click();
            location.sendKeys(locationVal);
            wait.until(ExpectedConditions.visibilityOf(customLocation)).click();
        }
    }

    public void setSearchField(String service){
        searchField.sendKeys(service);
    }

    public void clickOnSearch(){
        searchButton.click();
    }

}
