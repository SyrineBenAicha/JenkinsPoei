
import commun.SetupTeardown;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TpPageObject extends SetupTeardown {

    //WebDriver driver;
    //RemoteWebDriver driver;
    final String expectedProductName  = "Sous-total (2 articles):";


/*
    @AfterMethod
    public void teardown() {
        driver.quit();

    }
 */
    @Test
    public void testPO() {

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookie();
        homePage.SearchWithButton("Apple iPhone 13 Pro Max (256 Go) - Vert Alpin");



        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.openSearchResult(0);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.refuseAppleCare();
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.selectQuantity(2);
        cartPage.getFirstProductCapacity();
        cartPage.getFirstProductColor();
        cartPage.getFirstProductConfiguration();
        cartPage.getSubtotalCart();


        //Assert.assertEquals(Collections.singleton(cartPage.getSubtotalCart()),expectedProductName);
        Assert.assertEquals(cartPage.getSubtotalCart(), expectedProductName);

    }
    @Test
    public void testPOO() {
        final String keyword = "iPhone 13";

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.acceptCookie()
                .SearchWithButton(keyword)
                .openSearchResult(0)
                .addToCart()
                .refuseAppleCare()
                .openCart()
                .selectQuantity(1);

        Assert.assertEquals(cartPage.getSubtotalCart(), "Sous-total (1 article) :");
    }
/*
    @Test
    public void test2(){ // passer le curseur de souris sur le bouton sans appuie
        HomePage homePage = new HomePage(driver);

        WebElement loginButton = driver.findElement(By.cssSelector("#nav-link-accountList"));

        Actions actions = new Actions(driver);    
        actions.moveToElement(loginButton);
        actions.perform();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    */
}