package Org.TestCases.LoginPageValidation;

import Org.TestComponent.BrowserComponent;
import Org.Utilities.Assert.Assertion;
import Org.Utilities.DataReader.ExcelDataReader;
import Org.Utilities.UI.UIOperations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class InvalidLoginTest extends BrowserComponent {
    private String msg = "Epic sadface: Username and password do not match any user in this service";
    private String reportFileLocation = "C://Users//santa//IdeaProjects//Santanu_SwagLabs//src//test//java//Org//data//RegistrationData.xlsx";
    private Assertion assertion = new Assertion();
    @Test(dataProvider = "empRegistrationData")
    public void inValidCredentialsValidation(HashMap<String,String> input){
        loginPage.Login(input.get("UserName"),input.get("Password"));
        assertion.IsEquals(msg,loginPage.getLoginErrorMessage(),"Error Message not displaying");
    }

    @DataProvider(name="empRegistrationData")
    public Object[][] registrationData() throws IOException {
        List<HashMap<String,String>> dataMapList = ExcelDataReader.getDataFromExcel(reportFileLocation, "InValidLoginDetails");
        Object[][] dataArray = new Object[dataMapList.size()][1]; // Assuming each row in the Excel corresponds to an Object array
        for (int i = 0; i < dataMapList.size(); i++) {
            dataArray[i][0] = dataMapList.get(i); // Each row in dataArray will contain a HashMap
        }
        return dataArray;
    }
}
