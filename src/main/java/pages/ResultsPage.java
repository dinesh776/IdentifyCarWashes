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

public class ResultsPage extends BasePage {
    private static final Logger logger= LogManager.getLogger(ResultsPage.class);
    public ResultsPage(WebDriver driver) {
        super(driver);
        logger.info("ResultsPage initialized.");

    }

    @FindBy(id = "abd_name")
    WebElement formName;

    @FindBy(id = "abd_mobile")
    WebElement formNumber;

    @FindBy(xpath = "//button[contains(@class,'advance_deal_button')]")
    WebElement sendQuery;

    @FindBy(id = "abd_error_mobile")
    WebElement msgMobile;

    @FindBy(id = "abd_error_name")
    WebElement msgName;

    @FindBy(xpath = "//div[contains(@class,'login_text')]//span[1]")
    WebElement welcomeMsg;

    @FindBy(xpath = "//div[contains(@class,'jd_modal_close')]")
    WebElement closeShow;

    @FindBy(xpath = "(//span[text()='Show Number']//parent::div)[1]")
    WebElement callBtn;

    @FindBy(xpath = "(//li[contains(@class,'resultbox_totalrate')])[1]")
    WebElement firstRating;


    private final String ratingXpath="(//li[contains(@class,'resultbox_countrate')])";

    @FindBy(xpath = "//div[contains(@class,'popbddvn__left')]/div[2]")
    WebElement contactInfo;

    @FindBy(id = "onCloseOTP")
    WebElement close;

    public String getFirstRating(){
        logger.info("Fetching first rating.");
        return wait.until(ExpectedConditions.visibilityOf(firstRating)).getText();
    }

    public void clickOnShow(){
        logger.info("'Click on Show Number' button");
        callBtn.click();
    }

    public String getContact(){
        logger.info("Retrieving contact info.");
        return contactInfo.getText();
    }

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

    public String getWelcomeMsg(){
        logger.info("Welecome message");
        return wait.until(ExpectedConditions.visibilityOf(welcomeMsg)).getText();
    }

    public List<Service> retrieveServices(int numberOfServices,int expectedVotes){
    logger.info("Retrieving services with more than {} votes.", expectedVotes);
        List<Service> result=new ArrayList<>();

        for(int i=1;i<=numberOfServices;i++){
            String path=ratingXpath+"["+i+"]";
            WebElement rating;
            try {
                rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
            }catch (Exception e){
                logger.warn("Rating not visible at index {}. Scrolling down.", i);
                jsExecutor.executeScript("window.scrollBy(0, 1000);");
                rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
            }
            String actualVotes=rating.getText().split(" ")[0];
            actualVotes=actualVotes.replaceAll(",","");

            if(!actualVotes.trim().isEmpty()){
                try{
                    if(Integer.parseInt(actualVotes)>expectedVotes){
                        String title=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3)["+i+"]"))).getText();

                        WebElement callButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'callbutton')])["+i+"]")));
                        String mobileNumber=callButton.getText();

                        if(mobileNumber.startsWith("Show")){
                            logger.info("Clicking 'Show Number' for service: {}", title);
                            callButton.click();
                            wait.until(ExpectedConditions.not(ExpectedConditions.textMatches(By.xpath("//div[contains(@class,'popbddvn__left')]/div[2]"), Pattern.compile("^Loading.*"))));

                            mobileNumber=getContact();

                            closeShow.click();
                        }
                        result.add(new Service(title,mobileNumber));
                        logger.info("Service added: {} - {}", title, mobileNumber);
                    }
                }catch (Exception e){
                    logger.error("Error in proceeded in Servicing",e.getMessage());
                }

            }else{
                i--;
            }
        }
        logger.info("Total services retrieved: {}", result.size());
        return result;
    }
}
