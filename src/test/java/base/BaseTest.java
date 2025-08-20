package base;


import java.time.Duration;
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

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
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

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver((ChromeOptions) options);
                break;
            case "edge":
                driver = new EdgeDriver((EdgeOptions) options);
                break;
        }

        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.justdial.com/");
    }

    @AfterClass
    public void tear_down(){
        driver.quit();
    }

}
