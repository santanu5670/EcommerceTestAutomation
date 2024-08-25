package Org.PageObject;

import Org.Utilities.Assert.Assertion;
import Org.Utilities.Assert.SoftAssertion;
import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import Org.Utilities.UI.UIOperations;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends UIOperations {
    private WebDriver driver;
    private CaptureScreenshots capture;
    private List<String> productNames;
    private SoftAssertion softAssert = new SoftAssertion();
    private Assertion hardAssert = new Assertion();
    private List<String> productsPriceFromCartPage = new ArrayList<String>();
    public CartPage(WebDriver driver, CaptureScreenshots capture){
        super(driver);
        this.driver = driver;
        this.capture = capture;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    private WebElement Button_Cart;
    @FindBy(xpath = "//div[@class=\"cart_item\"]")
    private List<WebElement> AddedProduct;
    private String Text_productName = "//div[@class=\"cart_item\"]/descendant::a/div[text()=\"#ProductName#\"]";
    private String Text_productPrice = "//div[text()=\"#ProductName#\"]/parent::a/following-sibling::div[@class=\"item_pricebar\"]/div[@class=\"inventory_item_price\"]";
    private String Button_removeProduct = "//div[text()=\"#ProductName#\"]/parent::a/following-sibling::div[@class=\"item_pricebar\"]/button[text()=\"Remove\"]";
    @FindBy(xpath="//button[text()=\"Continue Shopping\"]")
    private WebElement Button_ContinueShopping;
    @FindBy(xpath="//button[text()=\"Checkout\"]")
    private WebElement Button_Checkout;

    public List<String> VerifyCartPage(String productName){
        click(Button_Cart);
        productNames=dataSeparation(productName);
        for (String singleProductName : productNames){
            softAssert.IsTrue(CheckOnlyWebElement(usingXpath(Text_productName.replace("#ProductName#", singleProductName))),"Added Product Not Displaying in Cart Page");
            softAssert.IsTrue(CheckOnlyWebElement(usingXpath(Button_removeProduct.replace("#ProductName#", singleProductName))),"Removed button not displaying in cart page");
            productsPriceFromCartPage.add(GetText(usingXpath(Text_productPrice.replace("#ProductName#", singleProductName))));
            capture.getAllStepFullPageScreenshot("CartPage_");
            softAssert.assertAll();
        }
        click(Button_Checkout);
        capture.getAllStepFullPageScreenshot("CheckoutPage_");
        return productsPriceFromCartPage;
    }
}
