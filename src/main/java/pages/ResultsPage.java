package pages;


import base.BasePage;
import model.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class represents the Results Page and contains methods to interact with various elements
 * and retrieve service-related data based on ratings and votes.
 */
public class ResultsPage extends BasePage {
    private static final Logger logger= LogManager.getLogger(ResultsPage.class);
    public ResultsPage(WebDriver driver) {
        super(driver);
        logger.info("ResultsPage initialized.");

    }

    // Form input fields and buttons
    @FindBy(id = "abd_name")
    WebElement formName;

    @FindBy(id = "abd_mobile")
    WebElement formNumber;

    @FindBy(xpath = "//button[contains(@class,'advance_deal_button')]")
    WebElement sendQuery;

    // Error messages for form validation
    @FindBy(id = "abd_error_mobile")
    WebElement msgMobile;

    @FindBy(id = "abd_error_name")
    WebElement msgName;

    // Welcome message after login
    @FindBy(xpath = "//div[contains(@class,'login_text')]//span[1]")
    WebElement welcomeMsg;

    // Close button for modal dialogs
    @FindBy(xpath = "//div[contains(@class,'jd_modal_close')]")
    WebElement closeShow;

    // Button to show contact number
    @FindBy(xpath = "(//span[text()='Show Number']//parent::div)[1]")
    WebElement callBtn;

    // First rating element
    @FindBy(xpath = "(//li[contains(@class,'resultbox_totalrate')])[1]")
    WebElement firstRating;


    // XPath for multiple rating elements
    private final String ratingXpath="(//li[contains(@class,'resultbox_countrate')])";

    // Contact info displayed after clicking "Show Number"
    @FindBy(xpath = "//div[contains(@class,'popbddvn__left')]/div[2]")
    WebElement contactInfo;

    // Close button for OTP dialog
    @FindBy(id = "onCloseOTP")
    WebElement close;

    // Returns the first rating text
    public String getFirstRating(){
        logger.info("Fetching first rating.");
        return wait.until(ExpectedConditions.visibilityOf(firstRating)).getText();
    }

    // Clicks on the "Show Number" button
    public void clickOnShow(){
        logger.info("'Click on Show Number' button");
        callBtn.click();
    }

    // Retrieves contact information after showing the number
    public String getContact(){
        logger.info("Retrieving contact info.");
        return contactInfo.getText();
    }

    // Closes the send query dialog
    public void closeSendQueryDialog(){
        logger.info("Closing send query dialog.");
        close.click();
    }

    public void setName(String name){
        logger.info("Setting name: {}", name);
        formName.sendKeys(name);
    }


    public void setMobileNumber(String number){
        logger.info("Setting mobileNumber: {}", number);
        formNumber.sendKeys(number);
    }

    // Clicks the send query button
    public void clickOnSendQuery(){
        logger.info("Clicking on 'Send Enquiry' Button");
        sendQuery.click();
    }

    public String getMobileErrorMsg(){
        logger.info("Fetching Mobile Number error message.");
        return wait.until(ExpectedConditions.visibilityOf(msgMobile)).getText();
    }

    public String getNameErrorMsg(){
        logger.info("Fetching name error message.");
        return wait.until(ExpectedConditions.visibilityOf(msgName)).getText();
    }

    // Retrieves the welcome message after login
    public String getWelcomeMsg(){
        logger.info("Welecome message");
        return wait.until(ExpectedConditions.visibilityOf(welcomeMsg)).getText();
    }



/**
 * Retrieves a list of services based on the number of services to check and expected votes.
 * Only services with votes greater than expectedVotes are included.
 *

 * @param numberOfServices Number of services to evaluate
 * @param expectedVotes Minimum number of votes required
 * @return List of Service objects with title and mobile number
 */

  public List<Service> retrieveServices(int numberOfServices,int expectedVotes){
      logger.info("Retrieving services with more than {} votes.", expectedVotes);
        List<Service> result=new ArrayList<>();

        for(int i=1;i<=numberOfServices;i++){
            String path=ratingXpath+"["+i+"]";
            WebElement rating;
            try {
                // Try to locate the rating element
                rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
            }catch (Exception e){
                // Scroll down and retry if not visible
                logger.warn("Rating not visible at index {}. Scrolling down.", i);
                jsExecutor.executeScript("window.scrollBy(0, 1000);");
                rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
            }
            // Extract and clean vote count
            String actualVotes=rating.getText().split(" ")[0];
            actualVotes=actualVotes.replaceAll(",","");

            if(!actualVotes.trim().isEmpty()){
                try{
                    // Check if votes exceed expected threshold
                    if(Integer.parseInt(actualVotes)>expectedVotes){
                        // Get service title
                        String title=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3)["+i+"]"))).getText();

                        // Get mobile number or click to reveal it
                        WebElement callButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'callbutton')])["+i+"]")));
                        String mobileNumber=callButton.getText();

                        if(mobileNumber.startsWith("Show")){
                            logger.info("Clicking 'Show Number' for service: {}", title);
                            callButton.click();
                            // Wait until contact info is loaded
                            wait.until(ExpectedConditions.not(ExpectedConditions.textMatches(By.xpath("//div[contains(@class,'popbddvn__left')]/div[2]"), Pattern.compile("^Loading.*"))));

                            mobileNumber=getContact();

                            closeShow.click();
                        }
                        // Add service to result list
                        result.add(new Service(title,mobileNumber));
                        logger.info("Service added: {} - {}", title, mobileNumber);
                    }
                }catch (Exception e){
                    logger.error("Error in proceeded in Servicing",e.getMessage());
                }

            }else{
                // Retry the same index if no votes found
                i--;
            }
        }
        logger.info("Total services retrieved: {}", result.size());
        return result;
    }
}
