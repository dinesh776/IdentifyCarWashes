package testRunner;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.AllureReportOpener;

/**
 * TestRunner class to configure and execute Cucumber tests using TestNG.
 */
@CucumberOptions(
        features = {"src/test/resources/features/category_display.feature"},  // Path to feature files
        glue = {"stepDefinitions", "hooks"},  // Step definitions and hooks
        //tags = "@Sanity or @Feildlevel or @LoginFeature or @DataExport or @GlossySprays or @Regression",  // Tags to filter scenarios
//        plugin = {
//                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",  // Allure report plugin
//                "pretty",  // Console output formatting
//                "html:target/cucumber-report.html"  // HTML report generation
//        }
        plugin = {
                 // Allure report plugin

                "pretty",// Console output formatting
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber=reports/cucumber-reports2.json",
                "html:target/cucumber-report.html"  // HTML report generation
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        // Clean previous Allure results before test execution
        AllureReportOpener.cleanAllureResults();
    }
    @AfterSuite
    public void afterSuite() {
        // Open Allure report after test execution
        AllureReportOpener.openAllureReport();
    }
}
