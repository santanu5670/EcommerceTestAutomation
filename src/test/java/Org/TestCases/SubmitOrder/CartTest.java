package Org.TestCases.SubmitOrder;

import Org.TestComponent.BrowserComponent;
import Org.Utilities.Assert.Assertion;
import Org.Utilities.DataReader.ExcelDataReader;
import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import Org.Utilities.UI.UIOperations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CartTest extends BrowserComponent {
    private String reportFileLocation = "C://Users//santa//IdeaProjects//Santanu_SwagLabs//src//test//java//Org//data//RegistrationData.xlsx";
    public List<String> productPriceOnHomePage;
    public List<String> productPriceFromCartPage;
    private Assertion hardAssert = new Assertion();

    @Test(dataProvider = "empRegistrationData")
    public void addProductVerification(HashMap<String,String> input){
        loginPage.Login(input.get("UserName"),input.get("Password"));
        productPriceOnHomePage = loginPage.addProductFromHomePage(input.get("ProductName"));
        productPriceFromCartPage = loginPage.VerifyCartPage(input.get("ProductName"));
        for(String productPriceFromHomePage : productPriceOnHomePage){
            System.out.println(productPriceFromHomePage);
        }
        for(String productPriceFromCartPage : productPriceFromCartPage){
            System.out.println(productPriceFromCartPage);
        }
        hardAssert.compareListAndPrintDifferences(productPriceOnHomePage,productPriceFromCartPage);
    }

    @DataProvider(name="empRegistrationData")
    public Object[][] registrationData() throws IOException {
        List<HashMap<String,String>> dataMapList = ExcelDataReader.getDataFromExcel(reportFileLocation, "CartTest");
        Object[][] dataArray = new Object[dataMapList.size()][1]; // Assuming each row in the Excel corresponds to an Object array
        for (int i = 0; i < dataMapList.size(); i++) {
            dataArray[i][0] = dataMapList.get(i); // Each row in dataArray will contain a HashMap
        }
        return dataArray;
    }

}
