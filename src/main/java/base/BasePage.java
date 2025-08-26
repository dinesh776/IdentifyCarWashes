package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.time.Duration;


/**
 * BasePage serves as the parent class for all page objects.
 * It initializes common elements like WebDriver, JavaScriptExecutor, and WebDriverWait.
 */

public class BasePage {
private static final Logger logger= LogManager.getLogger(BasePage.class);
    public WebDriver driver; // WebDriver instance for interacting with the browser
    public JavascriptExecutor jsExecutor;   // Used to execute JavaScript commands in the browser
    public WebDriverWait wait;  // Explicit wait for synchronizing with dynamic elements


    /**
     * Constructor initializes the page elements and utilities.
     * @param driver WebDriver instance passed from test or page class
     */

    public BasePage(WebDriver driver) {
        this.driver = driver;
logger.info("Driver Intilized");
        // Initializes all WebElements annotated with @FindBy in child classes
        PageFactory.initElements(driver,this);

        // Casts WebDriver to JavascriptExecutor for executing JS commands
        jsExecutor=(JavascriptExecutor) driver;
    logger.info("JavaScript Executor is Executed");
        // Initializes WebDriverWait with timeout value from config
        wait=new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        logger.info("WebDriverWait is Started");
    }

}
