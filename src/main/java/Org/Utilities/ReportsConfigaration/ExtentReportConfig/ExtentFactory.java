package Org.Utilities.ReportsConfigaration.ExtentReportConfig;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {

    //To make a Private constructor so that no one can create a object for this class
    private ExtentFactory(){

    }
    private static ExtentFactory instance = new ExtentFactory();

    public static ExtentFactory getInstance(){
        return instance;
    }

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    public ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public void setExtentTest(ExtentTest extentTestObject) {
        extentTest.set(extentTestObject);
    }
}
