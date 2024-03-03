package Org.TestComponent;

import Org.Utilities.ReportsConfigaration.ExtentReportConfig.ExtentFactory;
import Org.Utilities.ReportsConfigaration.ExtentReportConfig.ExtentReporterNG;
import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.List;

public class Listeners extends BrowserComponent implements ITestListener {
    CaptureScreenshots capture = new CaptureScreenshots(driver);
    ExtentTest test;
    public ExtentReports extent = ExtentReporterNG.getReportObject();

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        ExtentFactory.getInstance().setExtentTest(test);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        List<String> screenshots = capture.getScreenshotsPath();
//        screenshots.forEach(screenshotPath -> extentTest.get().pass("Step Description", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()));
        screenshots.forEach(screenshotPath -> ExtentFactory.getInstance().getExtentTest().info("Step Description", MediaEntityBuilder.createScreenCaptureFromBase64String(capture.convertImageToBase64(screenshotPath)).build()));
        ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Test Passed");
        capture.updatescreenshotsMap();
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        List<String> screenshots = capture.getScreenshotsPath();
//        screenshots.forEach(screenshotPath -> extentTest.get().fail("Step Description", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()));
        screenshots.forEach(screenshotPath -> ExtentFactory.getInstance().getExtentTest().info("Step Description", MediaEntityBuilder.createScreenCaptureFromBase64String(capture.convertImageToBase64(screenshotPath)).build()));
        ExtentFactory.getInstance().getExtentTest().fail(result.getThrowable());
        capture.updatescreenshotsMap();
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public synchronized void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }
}
