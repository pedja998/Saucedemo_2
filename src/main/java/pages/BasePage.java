package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public final By burgerButtonLokator =By.id("react-burger-menu-btn");
    public final By cartIconLocator=By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    public final By socialsLocator = By.xpath("//ul[contains(@class,\"social\")]");

    public final By cartIconBadgeLocator=By.className("shopping_cart_badge");
    public BasePage(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(Time.PAGE_LOAD_TIME_OUT));
    }
    public NavPage clickBurgerButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(burgerButtonLokator));
        WebElement burger = driver.findElement(burgerButtonLokator);
        burger.click();
        return new NavPage(driver);
    }
    public CartPage clicCartIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(cartIconLocator));
        WebElement cartIconButton = driver.findElement(cartIconLocator);
        cartIconButton.click();
        return new CartPage(driver);
    }
    public void openUrl(String url){
        driver.get(url);
    }
    public boolean waitForUrlChange(String url, int timeout){
        WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(timeout));
        return wait1.until(ExpectedConditions.urlToBe(url));
    }
    public WebElement getWebElement(By locator){
        WebElement element = driver.findElement(locator);
        return element;
    }
    public int convertToInt(String text){
        int i=Integer.parseInt(text);
        return i;
    }
}
