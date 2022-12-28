package tests.login;

import data.CommonStrings;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tests.BaseTest;

import java.io.FileReader;
import java.io.IOException;

public class GoodLoginAndCheckOut extends BaseTest {
    private WebDriver driver;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String postalCode;
    private int numberOfItems;
    @BeforeMethod
    public void setUpTest() throws IOException {
        fileReader=new FileReader("C:\\Users\\User\\Desktop\\Comtrade\\ProbaNeka\\Saucedemo_2\\src\\main\\java\\data\\Page.properties");
        properties.load(fileReader);
        driver = setUpDriver();
        username = properties.getProperty("korisnicko");
        password = properties.getProperty("sifra");
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
        Assert.assertTrue(homePage.verifyElement(homePage.titleLocator, Time.TIME_SHORTER),"PRODUCTS NIJE PRIKAZAN");
        Assert.assertTrue(homePage.verifyElement(homePage.socialsLocator, Time.TIME_SHORTER),"NEMA SOCIAL LINKOVA");
        Assert.assertTrue(homePage.verifyElement(homePage.cartIconLocator,Time.TIME_SHORTER),"NEMA CART IKONE");


        //Ulazim u burger meni i proveravam da li postoji LOGOUT
        NavPage burgerMenu = homePage.clickBurgerButton();
        Assert.assertTrue(burgerMenu.verifyElement(burgerMenu.getWantedBmItem("LOGOUT")),"NEMA LOGOUT-a");

        //Vraćam se na HomePage pritiskom na x u burger meniju, proveravam da li je dobar URL
        homePage = burgerMenu.closeMenu();
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

        Assert.assertTrue(productDetailsPage.verifyElement(productDetailsPage.titleLocator,3),"NIJE PRIKAZAN TITLE");
        Assert.assertTrue(productDetailsPage.verifyElement(productDetailsPage.detailsLocator,3),"NIJE PRIKAZAN DETAILS");
        Assert.assertTrue(productDetailsPage.verifyElement(productDetailsPage.priceLocator,3),"NIJE PRIKAZANA CENA");

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
