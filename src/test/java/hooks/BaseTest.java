package hooks;


import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ConfigReader;
import utilities.ScreenshotUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BaseTest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BaseTest.class);
    public static WebDriver driver=null;
private  static final Logger logger= (Logger) LogManager.getLogger(BaseTest.class);
    public static WebDriver getDriver() {
        return driver;
    } //Getter for webDriver

    // Setup method executed before each scenario and before the test class
    @Before
    @BeforeClass
    public static void setUp() throws MalformedURLException {


        // Setup method executed before each scenario and before the test class
        String Execution= ConfigReader.getEnvironment();


        // Read browser and platform parameters from TestNG XML
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platform");


        // Set default values if parameters are not provided
        browser=browser==null?"chrome":browser;
        platform=platform==null?"windows":platform;


        // ChromiumOptions is a generic type for Chrome/Edge options
        ChromiumOptions<?> options;


        // Initialize browser-specific options
        switch (browser.toLowerCase()) {
            case "chrome":
                options = new ChromeOptions();
                break;
            case "edge":
                options = new EdgeOptions();
                break;
            default:
                System.out.println("Invalid browser");
                return;
        }


        // Configure browser to avoid automation detection
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");



        if(Execution.equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //Set platform capability
            switch (platform){
                case "windows":capabilities.setPlatform(Platform.WIN11);break;
                case "linux":capabilities.setPlatform(Platform.LINUX);break;
                case "mac":capabilities.setPlatform(Platform.MAC);break;
                default:
                    logger.info("Invalid Paltform selected");
                    return;
            }

            //set browser capability
            switch (browser){
                case "chrome":capabilities.setBrowserName("chrome");break;
                case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox":capabilities.setBrowserName("firefox");break;
                case "safari":capabilities.setBrowserName("safari");break;
                default:
                    logger.info("Invalid browser");
                    return;
            }

            //Merge capabilities with options
            options.merge(capabilities);


            // Initialize RemoteWebDriver with remote URL
            driver=new RemoteWebDriver(new URL(ConfigReader.getRemoteUrl()),options);

        }else if(Execution.equalsIgnoreCase("local")){
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver((ChromeOptions) options);
                    break;
                case "edge":
                    driver = new EdgeDriver((EdgeOptions) options);
                    break;
                default:
                    logger.info("The Browser is not available in this device");
                    return;
            }
        }


        // Disable WebDriver detection by JavaScript
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );


        // Set implicit wait and maximize window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().window().maximize();
        //NAvigate to application URL
        driver.get(ConfigReader.getAppUrl());
    }


    // Capture screenshot after each step if scenario fails
    @AfterStep
    public static void captureScreenshotOnFailure(Scenario scenario){
        if (scenario.isFailed()) {

            //Take screenshot
            byte[] screenshot=ScreenshotUtility.screenShotTC(BaseTest.getDriver());

            // Attach screenshot to Cucumber report
            scenario.attach(screenshot, "image/png",scenario.getName());

            // Attach screenshot to Allure report
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));

        }
    }


    // Teardown method executed after each scenario and after the test class
    @After
    @AfterClass
    public static void tear_down(){
        driver.quit();
      logger.info("Driver closed Successfully");
    } // close browser and end session

}
