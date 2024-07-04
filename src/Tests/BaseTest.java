package Tests;

import Flow.GeekFlow;
import PageObjects.HomePage;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    static WebDriver driver;
    static GeekFlow geekFlow;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("detach=true");
        options.addArguments("excludeSwitches=enable-automation");
        options.addArguments("useAutomationExtension=false");
        options.addArguments("disable-blink-features=AutomationControlled");
        options.addArguments("disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("disable-browser-side-navigation");
        options.addArguments("disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        System.out.println("WebDriver initialized successfully.");
        resetPage();
    }

    public static void resetPage() {
        geekFlow = new GeekFlow(driver);
        performAutomationTasks();
    }

    public static void performAutomationTasks() {
        // Cast driver to JavascriptExecutor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Open URL in a new tab
        jsExecutor.executeScript("window.open('https://nowsecure.nl/', '_blank')");

        // Pause for 15 seconds
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Switch to new tab
        String newTabHandle = driver.getWindowHandles().toArray(new String[0])[1];
        driver.switchTo().window(newTabHandle);

        // Switch to frame
        driver.switchTo().frame(0);

        // Find and click the element
        driver.findElement(By.cssSelector("//*[@id='challenge-stage']/div/label/input")).click();
    }

    @AfterClass
    public static void close() {
        driver.close();
        driver.quit();
    }
}
