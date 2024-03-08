package Org.Utilities.UI;

import Org.Utilities.ReportsConfigaration.ExtentReportConfig.ExtentFactory;
import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class UIOperations{
    private int count = 0;
    private WebDriver driver;

    public UIOperations(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public synchronized void waitForElementToAppear(By findBy, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public synchronized void waitForWebElementToAppear(WebElement ele, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public synchronized void waitForWebElementDisappear(WebElement ele, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public synchronized void waitForWebElementToClick(WebElement ele, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public synchronized void forceWait(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void navigateToURL(String url){
        try {
            driver.navigate().to(url);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public void printURL(){
        ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Navigate To: "+driver.getCurrentUrl());
    }

    public synchronized void scrollToViewWebElement(WebElement element){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
            forceWait(1000);
            ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Scroll To: "+getByValueFromWebElement(element));
        }catch (Exception e){
            ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "Failed to Scroll: "+getByValueFromWebElement(element));
            throw new RuntimeException(e);
        }
    }

    public synchronized Select selectDropdown(WebElement dropdownElement, String dropDownText){
        try {
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByVisibleText(dropDownText);
            ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Select Dropdown By Visible Text: "+getByValueFromWebElement(dropdownElement));
            return dropdown;
        }
        catch (Exception e) {
            ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "Failed to Select Dropdown By Visible Text:"+getByValueFromWebElement(dropdownElement));
            throw new RuntimeException(e);
        }
    }

    public synchronized Boolean CheckOnlyWebElement(WebElement element){
        return (element != null && (element.isDisplayed() || element.isEnabled()));
    }

    public synchronized Boolean CheckWebElementAndMessage(WebElement element, String message){
        return (message!=null && CheckOnlyWebElement(element));
    }

    public synchronized String GetText(WebElement element){
        try {
            String value = "";
            if(CheckOnlyWebElement(element)){
                value = element.getText();
            }
            ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Text Fetched From: "+getByValueFromWebElement(element));
            return value;
        }catch(Exception e){
            ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "Failed To Fetched Text From: "+getByValueFromWebElement(element));
            throw new RuntimeException(e);
        }
    }

    public synchronized void clearAndSend(WebElement element, String message){
        try {
            if(CheckWebElementAndMessage(element, message)){
                scrollToViewWebElement(element);
                element.clear();
                element.sendKeys(message);
                ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Cleared At: "+getByValueFromWebElement(element));
            }
        }catch(Exception e){
            ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "Failed to Clear At: "+getByValueFromWebElement(element));
            throw new RuntimeException(e);
        }
    }

    public synchronized void click(WebElement element){
        try {
            if(CheckOnlyWebElement(element)){
                scrollToViewWebElement(element);
                forceWait(1000);
                element.click();
                ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Clicked at "+getByValueFromWebElement(element));
          }
        }catch (Exception e){
            ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "Failed to Click element at "+getByValueFromWebElement(element));
            throw new RuntimeException(e);
        }
    }

    public String getByValueFromWebElement(WebElement element){
        String byValue = "";
        String elementDescription = "";
        try{
            elementDescription = element.toString().trim();
            int startIndex = elementDescription.indexOf("By.") + 3;
            int endIndex = elementDescription.length()-1;
            byValue = elementDescription.substring(startIndex,endIndex).split(" -> ")[1];
        }catch (Throwable e){
            try {
                byValue= elementDescription.split("By.")[1];
            }catch (Throwable ignored){

            }
        }
        return byValue;
    }

    public WebElement usingXpath(String value){
        WebElement element = null;
        try {
            if(value != null){
                element = driver.findElement(By.xpath(value));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return element;
    }

    public synchronized void dataSeparation(String cellValue, String element, String screenshotname,CaptureScreenshots capture){
        if(cellValue.contains(";")){
            String[] newCellValues = cellValue.split(";");
            for (String newCellValue : newCellValues) {
                if (newCellValue != null) {
                    click(usingXpath(element.replace("#ProductName#", newCellValue)));
                    capture.getAllStepFullPageScreenshot(screenshotname);
                }
            }
        }
        else {
            click(usingXpath(element.replace("#ProductName#",cellValue)));
            capture.getAllStepFullPageScreenshot(screenshotname);
        }
    }

    public synchronized int increment(){
        count++;
        return count;
    }
}
