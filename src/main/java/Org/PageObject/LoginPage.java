package Org.PageObject;

import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import Org.Utilities.UI.UIOperations;
import Org.Utilities.DataReader.PropertiesDataExtract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class LoginPage extends UIOperations {
    private WebDriver driver;
    private CaptureScreenshots capture;
    private AddToCartFromHomePage addToCartFromHomePage;
    private CartPage cartPage;

    private List<String> ProductPriceFromHomePage;
    private List<String> ProductPriceFromCartPage;

    public LoginPage(WebDriver driver,CaptureScreenshots capture){
        super(driver);
        this.driver = driver;
        this.capture = capture;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@id=\"user-name\"]")
    private WebElement Username_Input;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement Password_Input;

    @FindBy(xpath = "//input[@id=\"login-button\"]")
    private WebElement Login_Button;

    @FindBy(xpath = "//div[@class=\"error-message-container error\"]/child::h3")
    private WebElement Login_ErrorMessage;

   public void Login(String userName, String passWord){
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

    public List<String> addProductFromHomePage(String productName){
        addToCartFromHomePage = new AddToCartFromHomePage(driver,capture);
        ProductPriceFromHomePage = addToCartFromHomePage.ProductAddToCart(productName);
        return ProductPriceFromHomePage;
    }

    public List<String> VerifyCartPage(String productName){
        cartPage = new CartPage(driver,capture);
        ProductPriceFromCartPage = cartPage.VerifyCartPage(productName);
        return ProductPriceFromCartPage;
    }
}
