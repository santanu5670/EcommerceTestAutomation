package Org.Utilities.DataReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDataExtract {
    public static synchronized Properties PropDataExtract() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Org//Resources//GlobalData.properties");
        prop.load(fis);
        return prop;
    }
}
