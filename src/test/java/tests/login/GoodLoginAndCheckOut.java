package tests.login;

import data.CommonStrings;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tests.BaseTest;

public class GoodLoginAndCheckOut extends BaseTest {
    private WebDriver driver;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String postalCode;
    private int numberOfItems;
    @BeforeMethod
    public void setUpTest(){
        driver = setUpDriver();
        username = CommonStrings.STANDARD_USERNAME;
        password = CommonStrings.PASSWORD;
        firstName=CommonStrings.FIRST_NAME;
        lastName=CommonStrings.LAST_NAME;
        postalCode=CommonStrings.POSTAL_CODE;
        numberOfItems=0;
    }

    @Test(testName = "TC-1")
    public void testSuccessLoginAndVeriffy(){
        LoginPage loginPage = new LoginPage(driver).open();

        HomePage homePage = loginPage.login(username,password);
        Assert.assertTrue(homePage.verifyHomePage(),"Nije dobar url");

        //Provera title, social, cartIcon
        Assert.assertTrue(homePage.getWebElement(homePage.titleLocator).isDisplayed(),"PRODUCTS NIJE PRIKAZAN");
        Assert.assertTrue(homePage.getWebElement(homePage.socialsLocator).isDisplayed(),"NEMA SOCIAL LINKOVA");
        Assert.assertTrue(homePage.getWebElement(homePage.cartIconLocator).isDisplayed(),"NEMA CART IKONE");

        //Ulazim u burger meni i proveravam da li postoji LOGOUT
        NavPage burgerMenu = homePage.clickBurgerButton();
        Assert.assertTrue(burgerMenu.getWantedBmItem("LOGOUT").isDisplayed(),"NEMA LOGOUT ITEM-a");

        //Vraćam se na HomePage pritiskom na x u burger meniju, proveravam da li je dobar URL
        HomePage homePage1 = burgerMenu.closeMenu();
        Assert.assertTrue(homePage.verifyHomePage(),"Nije dobar url");
    }

    @Test(testName = "TC-2")
    public void testSuccessLoginAndCheckOut(){
        LoginPage loginPage = new LoginPage(driver).open();

        //Ulazim na home page i proveravam URL
        HomePage homePage = loginPage.login(username,password);
        Assert.assertTrue(homePage.verifyHomePage(),"Nije dobar url!");

        //Klikom na željeni item ulazim na product details
        ProductDetailsPage productDetailsPage = homePage.selectWantedElement("Sauce Labs Backpack");
        Assert.assertTrue(productDetailsPage.verifyDetailsPage(),"Nije dobar url!");
        productDetailsPage.clickAddToCart(); //U metodi sam proverio da li je promenjen tekst dugmeta nakon klika
        numberOfItems++;
        Assert.assertTrue(productDetailsPage.getWebElement(productDetailsPage.titleLocator).isDisplayed(),"NIJE PRIKAZAN TITLE");
        Assert.assertTrue(productDetailsPage.getWebElement(productDetailsPage.detailsLocator).isDisplayed(),"NIJE PRIKAZAN DETAILS");
        Assert.assertTrue(productDetailsPage.getWebElement(productDetailsPage.priceLocator).isDisplayed(),"NIJE PRIKAZANA CENA");

        //Klikom idem na home page i klik addToCart Fleece Jacket
        homePage = productDetailsPage.clickBackToProducts();
        Assert.assertTrue(homePage.verifyHomePage(),"Nije dobar url");
        homePage.clickAddBtnOfSpecificItem("fleece-jacket"); //dodaj Asser da proveris promeni teksta
        numberOfItems++;
        
        //Provera da li se pojavio broj izbranih elemenata u shopCartIconi i otvaranje ShopCart strane
        Assert.assertEquals(homePage.convertToInt(homePage.getWebElement(homePage.cartIconBadgeLocator).getText()),numberOfItems);
        CartPage cartPage = homePage.clicCartIcon();
        Assert.assertTrue(cartPage.verifyCartPage());

        //Ulazak u CheckOut stranu
        CheckOutPage checkOutPage = cartPage.clickCheckOutButton();
        Assert.assertTrue(checkOutPage.verifyCheckOutPage(),"Nije dobar url");

        //Popunjavanje imena, prezimena i poštanskog broja
        CheckoutOverviewPage overviewPage= checkOutPage.proceedCheckOut(firstName,lastName,postalCode);
        Assert.assertTrue(overviewPage.verifyCheckOutOverview(),"URL nije dobar");

        //Klik na finish i ulazak u Complete page, provera Url-a i provera prikazane poruke
        CheckoutCompletePage completePage = overviewPage.clickFinis();
        Assert.assertTrue(completePage.verifyCompletePage());
        Assert.assertTrue(completePage.verifyPlacedOrder());

        //Klik na burger meni, klik na logout, provera da li se vratio na login stranu
        NavPage burgerMenu = completePage.clickBurgerButton();
        loginPage = burgerMenu.clickLogout();
        Assert.assertTrue(loginPage.verifyLoginPage(),"Nije dobar Url");
    }
    @AfterMethod(alwaysRun = true)
    public void tearTest(){
        quitDriver(driver);
    }
}
