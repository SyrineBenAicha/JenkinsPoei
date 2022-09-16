package main.java.commun;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class SetupTeardown {

    public WebDriver driver;

    String browser ="chrome";

    @BeforeMethod
    public void setup() throws MalformedURLException {
/*
        //System.out.println(System.getProperty("user.dir"));

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.setCapability(CapabilityType.BROWSER_NAME,"chrome");
  */

        // WebDriverManager.firefoxdriver().setup();
        // WebDriverManager.edgedriver().setup();


        switch(browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }

      //  WebDriverManager.chromedriver().setup();
      //  driver = new ChromeDriver();

        //driver = new RemoteWebDriver(new URL("http://admin:admin@192.168.121.21:4444"),options);
        //driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
        driver.get("https://www.amazon.fr/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException, NoSuchAlgorithmException, KeyStoreException, InterruptedException, KeyManagementException {

        ImportResultsToXray res = new ImportResultsToXray();

        if(result.getStatus() == ITestResult.SUCCESS)
        {
            System.out.println("passed **");
            res.generateJsonResults("PASSED");
        }
        else if(result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed **");
            res.generateJsonResults("FAILED");
        }
        else if(result.getStatus() == ITestResult.SKIP ){

            System.out.println("Skiped**");
            res.generateJsonResults("SKIPED");
        }
       res.RemonteResultats();


        driver.quit();

    }


}
