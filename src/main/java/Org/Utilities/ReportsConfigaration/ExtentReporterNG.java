package Org.Utilities.ReportsConfigaration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.IOException;

public class ExtentReporterNG {
    private static ExtentReports extent;
    public static synchronized ExtentReports getReportObject() {
        try {
            String path = System.getProperty("user.dir")+"\\Reports\\index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.loadXMLConfig(System.getProperty("user.dir")+"\\Config\\ExtentReportConfig.xml");
//            reporter.loadJSONConfig(new File(System.getProperty("user.dir")+"\\Config\\ExtentReportConfig.json"));
            reporter.config().setReportName("Web Automations Results");
            reporter.config().setDocumentTitle("Test Results");
            extent = new ExtentReports();
            extent.setSystemInfo("Tester", "Santanu");
            extent.attachReporter(reporter);
             /*To open an ExtenReport Automatically use below line"*/
            /*Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"\\Reports\\index.html").toURI());*/
        }catch (IOException e) {
            e.printStackTrace();
        }
        return extent;
    }
}
