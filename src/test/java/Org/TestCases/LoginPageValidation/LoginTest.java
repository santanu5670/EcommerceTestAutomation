package Org.TestCases.LoginPageValidation;

import Org.Utilities.DataReader.ExcelDataReader;
import Org.TestComponent.BrowserComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import Org.Utilities.UI.UIOperations;

public class LoginTest extends BrowserComponent {

    UIOperations ui = new UIOperations(driver);
    String reportFileLocation = "C://Users//santa//IdeaProjects//Santanu_SwagLabs//src//test//java//Org//data//RegistrationData.xlsx";
    @Test(dataProvider = "empRegistrationData")
    public void login(HashMap<String,String> input){
        loginPage.Login(input.get("UserName"),input.get("Password"));
    }

    @DataProvider(name="empRegistrationData")
    public Object[][] registrationData() throws IOException {
        List<HashMap<String,String>> dataMapList = ExcelDataReader.getDataFromExcel(reportFileLocation, "Details");
        Object[][] dataArray = new Object[dataMapList.size()][1]; // Assuming each row in the Excel corresponds to an Object array
        for (int i = 0; i < dataMapList.size(); i++) {
            dataArray[i][0] = dataMapList.get(i); // Each row in dataArray will contain a HashMap
        }
        return dataArray;
    }

     /*@Test(dataProvider = "empRegistrationData")
    public void registration(String UserName, String FullName, String Email, String PhoneNumber, String Country) throws InterruptedException {
        registrationPage.Registration(UserName,FullName,Email,PhoneNumber,Country);
    }

    @DataProvider(name="empRegistrationData")
    public Object[][] registrationData() throws IOException {
        //ExcelDataReader excelDataReader = new ExcelDataReader();
        return ExcelDataReader.getDataFromExcel();
    }*/

     /*@DataProvider
    public Object[][] getData() {
        return new Object[][] {{"Santanu_001","Santanu Saha","santanu2539@gmail.com","6290452835","India"},{"Santanu_002","Santanu Saha","santanu2593@gmail.com","6290452845","India"}};
    }*/
}
