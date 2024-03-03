package Org.Utilities.ReportsConfigaration.ScreenshotsConfig;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.file.FileUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CaptureScreenshots {
    //    private static List<String> screenshots = new ArrayList<>();
    private static ThreadLocal<List<String>> screenshots = ThreadLocal.withInitial(ArrayList::new);
    WebDriver driver;


    public CaptureScreenshots(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public synchronized String getScreenshot(String screenShotName, WebDriver driver){
        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "//Screenshots//"+screenShotName+System.currentTimeMillis()+".png";
            File destination = new File(path);
//            String filePath = destination.getAbsolutePath();
            FileUtils.copyFile(source, destination);
            return path;
//            return System.getProperty("user.dir") + "//Screenshots//" +screenShotName+System.currentTimeMillis()+ ".png";
            /*return filePath;
            return destination.getName();*/
        } catch (WebDriverException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void getAllStepScreenshot(String screenShotName, WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "//Screenshots//" + screenShotName + System.currentTimeMillis() + ".png";
            File destination = new File(path);
            FileUtils.copyFile(source, destination);
            screenshots.get().add(path);
        } catch (WebDriverException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void getAllStepFullPageScreenshot(String screenShotName) {
        try {
            String path = System.getProperty("user.dir") + "//Screenshots//" + screenShotName + System.currentTimeMillis() + ".png";
            File directory = new File(path);
            if(!directory.exists()){
                directory.mkdirs();
            }
            FileUtil.writeImage(Shutterbug.shootPage(driver, Capture.FULL,false).getImage(),"PNG",directory);
            screenshots.get().add(path);
        } catch (WebDriverException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized List<String> getScreenshotsPath() {
        return new ArrayList<>(screenshots.get());
    }

    public synchronized void updatescreenshotsMap() {
        screenshots.get().clear();
    }

    /*public synchronized String convertImageToBase64(String path){
        try {
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            fis.read(bytes);
            String base64Img = new String(Base64.getEncoder().encodeToString(bytes));
            return base64Img;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }*/

    public String convertImageToBase64(String path){
        String Base = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
            Base = Base64.getEncoder().encodeToString(fileContent);
            Base = "data:image/png;base64," + Base;
        }catch (Exception e){
            System.out.println("Couldnt able to convert image");
        }
        return Base;
    }
}
