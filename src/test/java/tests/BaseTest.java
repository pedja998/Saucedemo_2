package tests;

import org.openqa.selenium.WebDriver;
import utils.WebdriverUtils;

import java.io.FileReader;
import java.util.Properties;

public class BaseTest {
    public static Properties properties = new Properties();
    public static FileReader fileReader;
    protected WebDriver setUpDriver(){
        return WebdriverUtils.setUpWebDriver();
    }
    protected void quitDriver(WebDriver driver) {WebdriverUtils.quitWebDriver(driver);
    }
}
