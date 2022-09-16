package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private By acceptCookieSelector = By.cssSelector("[data-cel-widget=sp-cc-accept]");
    private By ResearchSelector = By.cssSelector("[55aria-label=Rechercher]"); //By.cssSelector("[55aria-label=Rechercher]");
    private By ButtonSelector = By.cssSelector("[type=submit]");

    private final int TIMEOUT_COOKIE = 6;

    private static final Logger Log = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
/*
    public void acceptCookie() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_COOKIE));
        WebElement buttonCookie = wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookieSelector));
        buttonCookie.click();
    }*/
    public HomePage acceptCookie() {
        Log.info("J'accepte les cookies");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_COOKIE));
        WebElement buttonCookie = wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookieSelector));
        buttonCookie.click();
        return this;
    }

    /*public void SearchWithButton(String keyword) {
        WebElement searchBar = driver.findElement(ResearchSelector);
        searchBar.sendKeys(keyword);

        WebElement loupeButton = driver.findElement(ButtonSelector);
        loupeButton.click();*/
    public SearchResultPage SearchWithButton(String keyword) {
        WebElement searchBar = driver.findElement(ResearchSelector);
        searchBar.sendKeys(keyword);

        WebElement loupeButton = driver.findElement(ButtonSelector);
        loupeButton.click();
        return new SearchResultPage(driver);

    }

}