package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private final String checkOutCompleteUrl= CommonStrings.CHECK_OUT_COMPLETE;
    private final By checkoutContainerLocator = By.className("checkout_complete_container");
    private final By checkoutMessageLocator = By.className("complete-header");
    private final By backHomeBtnLocator = By.xpath("//button[contains(@id,\"back-to-products\")]");
    public CheckoutCompletePage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutContainerLocator));
    }
    public HomePage clickBackHome(){
        wait.until(ExpectedConditions.elementToBeClickable(backHomeBtnLocator));
        WebElement backHomeBtn = driver.findElement(backHomeBtnLocator);
        backHomeBtn.click();
        return new HomePage(driver);
    }
    public boolean verifyCompletePage() {
        return waitForUrlChange(checkOutCompleteUrl, Time.TIME_SHORTER);
    }
    public boolean verifyPlacedOrder(){
        return getWebElement(checkoutMessageLocator).getText().contains("THANK YOU");
    }
}
