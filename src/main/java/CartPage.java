import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private By quantitySelector = By.cssSelector("#quantity");
    private By CaracteristiqueSelector = By.cssSelector(".a-text-bold + span");
    private By subtotalSelector = By.cssSelector("#sc-subtotal-label-activecart");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     *
     * cette fonction permet de selectionner une quantité
     * @param quantity quantité à selectionner
     * @return
     */
    public CartPage selectQuantity(int quantity) {
        WebElement dropdown = driver.findElement(quantitySelector);
        Select quantiteDropdown = new Select(dropdown);
        quantiteDropdown.selectByIndex(quantity);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CartPage(driver);
    }
    public CartPage getFirstProductCapacity() {
        List<WebElement> informations = driver.findElements(CaracteristiqueSelector);
        WebElement taille = informations.get(0);
        return new CartPage(driver);
    }
    public CartPage getFirstProductColor() {
        List<WebElement> informations = driver.findElements(CaracteristiqueSelector);
        WebElement couleur = informations.get(1);
        return new CartPage(driver);
    }
    public CartPage  getFirstProductConfiguration() {
        List<WebElement> informations = driver.findElements(CaracteristiqueSelector);
        WebElement configuration = informations.get(2);
        return new CartPage(driver);
    }
    public String getSubtotalCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement soustotale = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalSelector));
        return soustotale.getText();
        //return new CartPage(driver);
    }


}
