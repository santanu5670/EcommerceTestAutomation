package Org.TestComponent;

import Org.PageObject.LoginPage;
import Org.Utilities.DataReader.PropertiesDataExtract;
import Org.Utilities.ReportsConfigaration.ScreenshotsConfig.CaptureScreenshots;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BrowserComponent {

    protected WebDriver driver;

    private CaptureScreenshots captureScreenshots;
    protected LoginPage loginPage;

    public WebDriver initializeDriver() throws IOException {
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : PropertiesDataExtract.PropDataExtract().getProperty("browser");
        if(browserName.contains("chrome")){
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless"))
                options.addArguments("headless");
            /*WebDriverManager.chromedriver().setup();*/
            driver = new ChromeDriver(options);
        } else if (browserName.contains("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        /*driver.manage().window().setSize(new Dimension(14410,900));*///full screen
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeSuite
    public synchronized void DeleteDirectoryContents(){
        String directoryPath = System.getProperty("user.dir") + "//Screenshots";

        File directory = new File(directoryPath);

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            // List all files and subdirectories in the directory
            File[] files = directory.listFiles();

            if (files != null) {
                // Delete each file in the directory
                for (File file : files) {
                    file.delete();
                }
                System.out.println("Contents of the directory deleted successfully.");
            } else {
                System.out.println("No files or subdirectories to delete.");
            }
        } else {
            System.out.println("Directory does not exist.");
        }
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage lunchApplicationSite() throws IOException {
        driver = initializeDriver();
        captureScreenshots = new CaptureScreenshots(driver);
        loginPage = new LoginPage(driver,captureScreenshots);
        loginPage.launchApplication();
        return loginPage;
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown(){
        driver.quit();
    }
}
