package app.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final String PATH = "D:\\work\\projects\\src\\main\\resources\\runConfig.properties";

    public static Properties props = new Properties();
    public static WebDriver driver = null;

    public static void initConfig(){
        InputStream input;
        {
            try {
                input = new FileInputStream(PATH);
                props.load(input);
                String driverType = props.getProperty("browser");
                if ("chrome".equals(driverType)) {
                    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                    driver =  new ChromeDriver();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Check the path to properties");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't read from properties file");
            }
        }
    }
}
