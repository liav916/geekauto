package Tests;

import Flow.GeekFlow;
import PageObjects.HomePage;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    //static HomePage homePage;
    static WebDriver driver;

    static GeekFlow geekFlow;


    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome-stable");
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        System.out.println("WebDriver initialized successfully."); // Add this line for logging
        resetPage();
    }


    public static void resetPage() {
        geekFlow = new GeekFlow(driver);
        //homePage = new HomePage(driver);

    }


    @AfterClass
    public static void close() {
        driver.close();
        driver.quit();
    }
}
