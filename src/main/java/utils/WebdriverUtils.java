package utils;

import data.CommonStrings;
import data.Time;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class WebdriverUtils {
    public static WebDriver setUpWebDriver(){
        WebDriver driver = WebDriverManager.chromiumdriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.IMPLICIT_TIME_OUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.PAGE_LOAD_TIME_OUT));
        driver.manage().window().maximize();
        return driver;
    }
    public static void quitWebDriver(WebDriver driver) {
        driver.quit();
    }
}
