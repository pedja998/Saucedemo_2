package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage extends BasePage {
    private final String checkOutPageURL= CommonStrings.CHECK_OUT_STEP_ONE;
    private final By checkoutTitleLocator = By.xpath("//span[contains(@class,\"title\")]");
    private final By firstNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By postalCodeLocator = By.id("postal-code");
    private final By continueBtnLocator = By.xpath("//input[contains(@class, \"submit\")]");
    private final By cancelBtnLocator = By.xpath("//button[contains(@name, \"cancel\")]");

    public CheckOutPage(WebDriver driver){
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTitleLocator));
    }
    private CheckOutPage setFirstName(String firstName){
        clearAndType(waitToBeVisible(firstNameLocator,Time.TIME_SHORTER),firstName);
        return this;
    }
    private CheckOutPage setLastName(String lastName){
        clearAndType(waitToBeVisible(lastNameLocator,Time.TIME_SHORTER),lastName);
        return this;
    }
    private CheckOutPage setPostalCode(String code){
        clearAndType(waitToBeVisible(postalCodeLocator,Time.TIME_SHORTER),code);
        return this;
    }
    public CartPage cancelCheckOut(){
        clickOnElement(cancelBtnLocator);
        return new CartPage(driver);
    }
    private CheckoutOverviewPage clickContinue(){
        clickOnElement(continueBtnLocator);
        return new CheckoutOverviewPage(driver);
    }
    public CheckoutOverviewPage proceedCheckOut(String firstName, String lastName, String code){
        setFirstName(firstName);
        setLastName(lastName);
        setPostalCode(code);
        clickContinue();
        return new CheckoutOverviewPage(driver);
    }
    public boolean verifyCheckOutPage() {
        return waitForUrlChange(checkOutPageURL, Time.TIME_SHORTER);
    }
}
