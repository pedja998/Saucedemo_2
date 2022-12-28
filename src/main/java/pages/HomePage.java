package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {
    private final String homePageUrl = CommonStrings.HOME_PAGE_URL;
    public final By titleLocator = By.className("title");
    public HomePage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header_secondary_container")));
    }

    //Ostavio sam metode koje prave Elemente kroz listu jer da sam ih stavio u select metode onda bi ne bi imao Web element kao return nego ProductDetailsPage
    private WebElement getWantedProduct(String name){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, \"item_name\")]")));
        List<WebElement> productNameList = driver.findElements(By.xpath("//div[contains(@class, \"item_name\")]"));
        for (WebElement element : productNameList){
            if (element.getText().contains(name))
                return element;
        }
        return null;
    }
    public ProductDetailsPage selectWantedElement(String name){
        clickOnElement(getWantedProduct(name));
        return new ProductDetailsPage(driver);
    }
    private WebElement getSpecificAddBtn(String name){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,\"btn_inventory\")]")));
        List<WebElement> listaDugmica = driver.findElements(By.xpath("//button[contains(@class,\"btn_inventory\")]"));
        for (WebElement e : listaDugmica){
            if(e.getAttribute("id").contains(name))
                return e;
        }
        return null;
    }
    public HomePage clickAddBtnOfSpecificItem(String name){
        clickOnElement(getSpecificAddBtn(name));
        return this;
    }
    public boolean verifyHomePage() {
        return waitForUrlChange(homePageUrl, Time.TIME_SHORTER);
    }
    /*public boolean verifyElement(By lokator, int timeout){
        return waitToBeVisible(lokator,timeout).isDisplayed();
    }*/
}
