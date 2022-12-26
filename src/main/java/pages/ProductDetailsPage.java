package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProductDetailsPage extends BasePage {

    private final String productDetailsUrl=CommonStrings.PRODUCT_DETAILS_URL;
    private final By backToProductsLocator = By.name("back-to-products");
    private final By addToCartLocator=By.xpath("//button[contains(@class,\"btn_inventory\")]");
    public final By titleLocator=By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]");
    public final By priceLocator=By.className("inventory_details_price");
    public final By detailsLocator=By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]");

    public ProductDetailsPage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(backToProductsLocator));
    }
    public ProductDetailsPage clickAddToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        getWebElement(addToCartLocator).click();
        Assert.assertTrue(getWebElement(addToCartLocator).getText().contains("REMOVE"));
        return this;
    }
    public HomePage clickBackToProducts(){
        wait.until(ExpectedConditions.elementToBeClickable(backToProductsLocator));
        getWebElement(backToProductsLocator).click();
        return new HomePage(driver);
    }
    public boolean verifyDetailsPage() {
        return waitForUrlChange(productDetailsUrl, Time.TIME_SHORTER);
    }

}
