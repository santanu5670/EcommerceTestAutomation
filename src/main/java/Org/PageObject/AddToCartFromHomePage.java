package Org.PageObject;

import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import Org.Utilities.UI.UIOperations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class AddToCartFromHomePage extends UIOperations{
    private WebDriver driver;
    private CaptureScreenshots capture;

    public AddToCartFromHomePage(WebDriver driver,CaptureScreenshots capture){
        super(driver);
        this.driver = driver;
        this.capture = capture;
        PageFactory.initElements(driver,this);
    }

    private String AddToCart_Button = "//div[text()=\"#ProductName#\"]/parent::a/parent::div/following-sibling::div/button[text()=\"Add to cart\"]";

    public void ProductAddToCart(String productName){
        printURL();
        dataSeparation(productName,AddToCart_Button,"AddToCart_",capture);
    }
}
