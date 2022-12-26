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
    private final By checkoutTitleLocator = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameLocator));
        WebElement firstNameField=driver.findElement(firstNameLocator);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return this;
    }
    private CheckOutPage setLastName(String firstName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameLocator));
        WebElement lastNameField=driver.findElement(lastNameLocator);
        lastNameField.clear();
        lastNameField.sendKeys(firstName);
        return this;
    }
    private CheckOutPage setPostalCode(String code){
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeLocator));
        WebElement postalCodeField = driver.findElement(postalCodeLocator);
        postalCodeField.clear();
        postalCodeField.sendKeys(code);
        return this;
    }
    public CartPage cancelCheckOut(){
        WebElement cancelButton = driver.findElement(cancelBtnLocator);
        cancelButton.click();
        return new CartPage(driver);
    }
    private CheckoutOverviewPage clickContinue(){
        WebElement continueDugme = driver.findElement(continueBtnLocator);
        continueDugme.click();
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
