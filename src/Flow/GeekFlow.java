package Flow;

import PageObjects.HomePage;
import org.openqa.selenium.WebDriver;


public class GeekFlow {

    HomePage homePage;


    public GeekFlow(WebDriver driver) {
        homePage = new HomePage(driver);

    }

    public void Flowyflow() throws InterruptedException {
        homePage.openAllArticles();

    }

}