package PageObjects;

import Tests.ArticlePrint;
import Tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage
{ WebDriver driver;
    BaseTest BaseTest;
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        BaseTest=new BaseTest();

    }
    // click function
    public  void click(By elementLocation) {
        waitForElement(elementLocation);
        driver.findElement(elementLocation).click();
    }

    public  void waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public  void verifyurl(List<WebElement> s, int index) {
        String url = driver.getCurrentUrl();
        if (url == "https://www.geektime.co.il"){
            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
            jse2.executeScript("arguments[0].scrollIntoView(true);", s.get(index));
            jse2.executeScript("arguments[0].click()", s.get(index));}

    }

    public void Waitviseblity(By by) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException o) {
            o.printStackTrace();
        }
    }




}
