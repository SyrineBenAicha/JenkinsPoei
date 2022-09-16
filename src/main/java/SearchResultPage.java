package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {

    private WebDriver driver;
    private By ElementSelector = By.cssSelector("img.s-image");
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }
    public ProductPage openSearchResult(int index) {
        List<WebElement> resultatList = driver.findElements(ElementSelector);
        resultatList.get(index).click();
        return new ProductPage(driver);
    }
}
