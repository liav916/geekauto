package timeToRun;

import PageObjects.HomePage;
import Tests.ArticlePrint;
import org.springframework.stereotype.Component;

import java.sql.Driver;
import java.util.Date;
import java.util.TimerTask;
import PageObjects.HomePage;
import Tests.*;

@Component
public class ExpiredSchedular extends TimerTask {

    private final ArticlePrint articlePrint;

    public ExpiredSchedular(ArticlePrint articlePrint) {
        this.articlePrint=articlePrint;
    }

    @Override
    public void run() {

        System.out.println(new Date());
        BaseTest.setUp();
        articlePrint.Geektime();
        BaseTest.close();

    }
}