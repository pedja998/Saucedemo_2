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
        clickOnElement(exitLocator);
        return new HomePage(driver);
    }
    public WebElement getWantedBmItem(String name){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,\"bm-item\")]")));
        List<WebElement> bmItemList = driver.findElements(By.xpath("//a[contains(@class,\"bm-item\")]"));
        for (WebElement element : bmItemList){
            if (element.getText().contains(name))
                return element;
        }
        return null;
    }
    public LoginPage clickLogout(){
        clickOnElement(logoutLocator);
        return new LoginPage(driver);
    }
}
