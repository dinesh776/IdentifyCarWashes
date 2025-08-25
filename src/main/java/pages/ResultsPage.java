package pages;


import base.BasePage;
import model.Service;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ResultsPage extends BasePage {

    public ResultsPage(WebDriver driver) {
        super(driver);
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
        return wait.until(ExpectedConditions.visibilityOf(firstRating)).getText();
    }

    public void clickOnShow(){
        callBtn.click();
    }

    public String getContact(){
        return contactInfo.getText();
    }

    public void closeSendQueryDialog(){
        close.click();
    }

    public void setName(String name){
        formName.sendKeys(name);
    }


    public void setMobileNumber(String number){
        formNumber.sendKeys(number);
    }

    public void clickOnSendQuery(){
        sendQuery.click();
    }

    public String getMobileErrorMsg(){
        return wait.until(ExpectedConditions.visibilityOf(msgMobile)).getText();
    }

    public String getNameErrorMsg(){
        return wait.until(ExpectedConditions.visibilityOf(msgName)).getText();
    }

    public String getWelcomeMsg(){
        return wait.until(ExpectedConditions.visibilityOf(welcomeMsg)).getText();
    }

    public List<Service> retrieveServices(int numberOfServices,int expectedVotes){

        List<Service> result=new ArrayList<>();

        for(int i=1;i<=numberOfServices;i++){
            String path=ratingXpath+"["+i+"]";
            WebElement rating;
            try {
                rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
            }catch (Exception e){
                jsExecutor.executeScript("window.scrollBy(0, 500);");
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
                            callButton.click();
                            wait.until(ExpectedConditions.not(ExpectedConditions.textMatches(By.xpath("//div[contains(@class,'popbddvn__left')]/div[2]"), Pattern.compile("^Loading.*"))));

                            mobileNumber=getContact();

                            closeShow.click();
                        }
                        result.add(new Service(title,mobileNumber));
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        }
        return result;
    }
}
