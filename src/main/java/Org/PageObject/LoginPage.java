package Org.PageObject;

import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import Org.Utilities.UI.UIOperations;
import Org.Utilities.DataReader.PropertiesDataExtract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends UIOperations {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@id=\"user-name\"]")
    protected WebElement Username_Input;

    @FindBy(xpath = "//input[@id=\"password\"]")
    protected WebElement Password_Input;

    @FindBy(xpath = "//input[@id=\"login-button\"]")
    protected WebElement Login_Button;

    @FindBy(xpath = "//div[@class=\"error-message-container error\"]/child::h3")
    protected WebElement Login_ErrorMessage;

   public void Login(String userName, String passWord){
       CaptureScreenshots capture = new CaptureScreenshots(driver);
       printURL();
       clearAndSend(Username_Input,userName);
       clearAndSend(Password_Input,passWord);
       capture.getAllStepFullPageScreenshot("LoginPage_");
       click(Login_Button);
       capture.getAllStepFullPageScreenshot("MainPage_");
   }

   public String getLoginErrorMessage(){
       return GetText(Login_ErrorMessage);
   }

   public void launchApplication() throws IOException {
       String url = PropertiesDataExtract.PropDataExtract().getProperty("url");
       navigateToURL(url);
   }


}
