package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {
    private final String checkOutOverviewUrl= CommonStrings.CHECK_OUT_STEP_TWO;
    private final By chekoutSummaryLocator = By.className("checkout_summary_container");
    private final By cancelLocator = By.id("cancel");
    private final By finishLocator = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(chekoutSummaryLocator));
    }
    public HomePage cancelCheckOut(){
        getWebElement(cancelLocator);
        return new HomePage(driver);
    }
    public CheckoutCompletePage clickFinis(){
        getWebElement(finishLocator).click();
        return new CheckoutCompletePage(driver);
    }
    public boolean verifyCheckOutOverview() {
        return waitForUrlChange(checkOutOverviewUrl, Time.TIME_SHORTER);
    }
}
