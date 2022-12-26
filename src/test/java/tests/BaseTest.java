package tests;

import org.openqa.selenium.WebDriver;
import utils.WebdriverUtils;

public class BaseTest {
    protected WebDriver setUpDriver(){
        return WebdriverUtils.setUpWebDriver();
    }
    protected void quitDriver(WebDriver driver) {WebdriverUtils.quitWebDriver(driver);
    }
}
