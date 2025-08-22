package hooks;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

    public static WebDriver driver=null;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public static void setUp() throws MalformedURLException {

        String Execution="local";

        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platform");

        ChromiumOptions<?> options;

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

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");



        if(Execution.equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();

            switch (platform){
                case "windows":capabilities.setPlatform(Platform.WIN11);break;
                case "linux":capabilities.setPlatform(Platform.LINUX);break;
                case "mac":capabilities.setPlatform(Platform.MAC);break;
                default:
                    System.out.println("Invalid Platform");return;
            }

            switch (browser){
                case "chrome":capabilities.setBrowserName("chrome");break;
                case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox":capabilities.setBrowserName("firefox");break;
                case "safari":capabilities.setBrowserName("safari");break;
                default:
                    System.out.println("Invalid browser");return;
            }

            options.merge(capabilities);

            driver=new RemoteWebDriver(new URL("http://10.109.179.27:4444"),options);

        }else if(Execution.equalsIgnoreCase("local")){
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver((ChromeOptions) options);
                    break;
                case "edge":
                    driver = new EdgeDriver((EdgeOptions) options);
                    break;
                default:
                    System.out.println("The Browser is not available in this device");return;
            }
        }

        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.justdial.com/");
    }

    @After
    public static void tear_down(){
        driver.quit();
    }

}
