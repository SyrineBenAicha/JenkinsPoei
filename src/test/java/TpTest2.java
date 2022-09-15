import commun.SetupTeardown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TpTest2 extends SetupTeardown {
    //WebDriver driver;
    //RemoteWebDriver driver;
    final String expectedProductName  = "Sous-total (2 articles):";


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
}
