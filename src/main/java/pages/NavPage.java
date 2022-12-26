package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NavPage extends BasePage {
    private final By logoutLocator=By.id("logout_sidebar_link");
    private final By exitLocator=By.id("react-burger-cross-btn");

    public NavPage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-item-list")));
    }
    public HomePage closeMenu(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitLocator));
        WebElement burgerExit = driver.findElement(exitLocator);
        burgerExit.click();
        return new HomePage(driver);
    }
    private List<WebElement> getBmItemNameList(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,\"bm-item\")]")));
        List<WebElement> bmItemList = driver.findElements(By.xpath("//a[contains(@class,\"bm-item\")]"));
        return bmItemList;
    }
    public WebElement getWantedBmItem(String name){
        List <WebElement> bmItemList = getBmItemNameList();
        for (WebElement element : bmItemList){
            if (element.getText().contains(name))
                return element;
        }
        return null;
    }
    public LoginPage clickLogout(){
        getWebElement(logoutLocator).click();
        return new LoginPage(driver);
    }
}
