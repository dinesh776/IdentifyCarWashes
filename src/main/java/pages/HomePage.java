package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='Maybe Later']")
    WebElement popUp;

    @FindBy(id = "main-auto")
    WebElement searchField;

    @FindBy(xpath = "//div[@class='search_button']")
    WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void handlePopup(){
        popUp.click();
    }

    public void setSearchField(String service){
        searchField.sendKeys(service);
    }

    public void clickOnSearch(){
        searchButton.click();
    }

}
