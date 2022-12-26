package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePage {
    private final String cartPageURL= CommonStrings.CART_PAGE_URL;
    private final By titleLocator = By.className("title");
    private final By continueShoppingLocator = By.id("continue-shopping");
    private final By checkOutLocator = By.id("checkout");

    public CartPage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
    }
    public List<WebElement> getItemsInCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@id, \"title_link\")]")));
        List<WebElement> itemsInCart = driver.findElements(By.xpath("//a[contains(@id, \"title_link\")]"));
        return itemsInCart;
    }
    public HomePage clickContinueShoppingButton(){
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingLocator));
        WebElement continueDugme = driver.findElement(continueShoppingLocator);
        continueDugme.click();
        return new HomePage(driver);
    }
    public CheckOutPage clickCheckOutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutLocator));
        WebElement checkOutDugme = driver.findElement(checkOutLocator);
        checkOutDugme.click();
        return new CheckOutPage(driver);
    }
    public boolean verifyCartPage() {
        return waitForUrlChange(cartPageURL, Time.TIME_SHORTER);
    }
}
