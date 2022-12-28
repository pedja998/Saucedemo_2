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
    public final By titleLocator=By.xpath("//div[contains(@class,\"details_name\")]");
    public final By priceLocator=By.className("inventory_details_price");
    public final By detailsLocator=By.xpath("//div[contains(@class,\"details_desc large\")]");

    public ProductDetailsPage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(backToProductsLocator));
    }
    public ProductDetailsPage clickAddToCart(){
        clickOnElement(getWebElement(addToCartLocator));
        Assert.assertTrue(getWebElement(addToCartLocator).getText().contains("REMOVE"));
        return this;
    }
    public HomePage clickBackToProducts(){
        clickOnElement(getWebElement(backToProductsLocator));
        return new HomePage(driver);
    }
    public boolean verifyDetailsPage() {
        return waitForUrlChange(productDetailsUrl, Time.TIME_SHORTER);
    }

}
