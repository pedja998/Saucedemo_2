package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage {
    private final String homePageUrl = CommonStrings.HOME_PAGE_URL;
    public final By titleLocator = By.className("title");
    public HomePage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header_secondary_container")));
    }
    private List<WebElement> getProductNameList(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, \"item_name\")]")));
        List<WebElement> productLinks = driver.findElements(By.xpath("//div[contains(@class, \"item_name\")]"));
        return productLinks;
    }
    private WebElement getWantedProduct(String name){
        List <WebElement> productNameList = getProductNameList();
        for (WebElement element : productNameList){
            if (element.getText().contains(name))
                return element;
        }
        return null;
    }
    public ProductDetailsPage selectWantedElement(String name){
        WebElement element = getWantedProduct(name);
        element.click();
        return new ProductDetailsPage(driver);
    }
    private List<WebElement> getAddToCartButtonList(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,\"btn_inventory\")]")));
        List<WebElement> addToCartList = driver.findElements(By.xpath("//button[contains(@class,\"btn_inventory\")]"));
        return addToCartList;
    }
    private WebElement getSpecificAddBtn(String name){
        List<WebElement> listaDugmica = getAddToCartButtonList();
        for (WebElement e : listaDugmica){
            if(e.getAttribute("id").contains(name))
                return e;
        }
        return null;
    }
    public HomePage clickAddBtnOfSpecificItem(String name){
        WebElement element = getSpecificAddBtn(name);
        element.click();
        return this;
    }
    public boolean verifyHomePage() {
        return waitForUrlChange(homePageUrl, Time.TIME_SHORTER);
    }
}
