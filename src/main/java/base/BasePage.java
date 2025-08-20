package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public JavascriptExecutor jsExecutor;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        jsExecutor=(JavascriptExecutor) driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }

}
