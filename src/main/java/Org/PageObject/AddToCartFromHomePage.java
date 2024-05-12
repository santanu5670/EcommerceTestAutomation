package Org.PageObject;

import Org.Utilities.Assert.Assertion;
import Org.Utilities.Assert.SoftAssertion;
import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import Org.Utilities.UI.UIOperations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AddToCartFromHomePage extends UIOperations{
    private WebDriver driver;
    private CaptureScreenshots capture;
    private List<String> productNames;
    private boolean removerCartChecker=false;
    private SoftAssertion softAssert = new SoftAssertion();
    private Assertion hardAssert = new Assertion();
    private List<String> productsPrice = new ArrayList<String>();

    public AddToCartFromHomePage(WebDriver driver,CaptureScreenshots capture){
        super(driver);
        this.driver = driver;
        this.capture = capture;
        PageFactory.initElements(driver,this);
    }

    private String AddToCart_Button = "//div[text()=\"#ProductName#\"]/parent::a/parent::div/following-sibling::div/button[text()=\"Add to cart\"]";
    private String RemoveCart_Button = "//div[text()=\"#ProductName#\"]/parent::a/parent::div/following-sibling::div/button[text()=\"Remove\"]";
    private String Text_ProductPrice = "//div[text()=\"#ProductName#\"]/../../following-sibling::div/div[@class=\"inventory_item_price\"]";

    public List<String> ProductAddToCart(String productName){
        printURL();
        productNames=dataSeparation(productName);
        for (String singleProductName : productNames) {
            click(usingXpath(AddToCart_Button.replace("#ProductName#", singleProductName)));
            removerCartChecker=CheckOnlyWebElement(usingXpath(RemoveCart_Button.replace("#ProductName#", singleProductName)));
            softAssert.IsTrue(removerCartChecker,"Remove button not displaying for: "+singleProductName);
            productsPrice.add(GetText(usingXpath(Text_ProductPrice.replace("#ProductName#", singleProductName))));
            capture.getAllStepFullPageScreenshot("AddToCart_");
            softAssert.assertAll();
        }

        /*for(String productPrice:productsPrice){
            System.out.println(productPrice);
        }*/
        return productsPrice;
    }
}
