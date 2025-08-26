package testRunner;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import utilities.ReportOpener;

/**
 * TestRunner class to configure and execute Cucumber tests using TestNG.
 */
@CucumberOptions(
        features = {"src/test/resources/features/Excel_reading.feature"},  // Path to feature files
        //features= {"@target/rerun.txt"},
        glue = {"stepDefinitions", "hooks"},  // Step definitions and hooks
        //tags = "@Sanity or @Regression",  // Tags to filter scenarios
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", // Allure report plugin
                "pretty",  // Console output formatting
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"// HTML report generation
        },
        monochrome = true,  //Removes unnecessary characters from console output
        dryRun = false,     // If true, checks for missing step definitions without executing tests
        publish = false     // If true, publishes results to Cucumber Report (optional)
)
public class TestRunner extends AbstractTestNGCucumberTests {


    // Executed once before the entire test suite starts
    @BeforeSuite
    public void beforeSuite() {
        // Clean previous Allure results before test execution
        ReportOpener.cleanAllureResults();
    }

    // Executed once after the entire test suite finishes
    @AfterSuite
    public void afterSuite() {


        // Opens Allure, Extent, and Cucumber reports automatically after test execution
        ReportOpener.openAllureReport();
        ReportOpener.openLatestExtentReport();
        ReportOpener.openCucumberReport();
    }


    // Provides scenarios to TestNG for parallel execution or data-driven testing
    @Override
    @DataProvider()
    public Object[][] scenarios(){
        return super.scenarios();
    } // Uses default scenario provider from AbstractTestNGCucumberTests
}
