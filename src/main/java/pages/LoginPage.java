package pages;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    private final String loginPageUrl= CommonStrings.LOGIN_PAGE_URL;
    private final By usernameLocator = By.id("user-name");
    private final By passwordLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    public LoginPage(WebDriver driver){
        super(driver);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
    }
    public LoginPage open(){
        openUrl(loginPageUrl);
        return this;
    }
    public boolean verifyLoginPage(){
        return waitForUrlChange(loginPageUrl, Time.TIME_SHORTER);
    }
    public HomePage login(String username, String password){
        setUsername(username);
        setPassword(password);
        clicLogin();
        return new HomePage(driver);
    }
    //Privatna metoda, neće biti dostupna u testu, samo je jedna public
    private LoginPage setUsername(String username){
        clearAndType(getWebElement(usernameLocator),username);
        return this;
    }
    private LoginPage setPassword(String password){
        clearAndType(getWebElement(passwordLocator),password);
        return this;
    }
    private HomePage clicLogin(){
        clickOnElement(loginButtonLocator);
        return new HomePage(driver);
    }
}
