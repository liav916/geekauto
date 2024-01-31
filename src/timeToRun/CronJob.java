package timeToRun;
import Tests.ArticlePrint;
import java.util.Timer;
import java.util.TimerTask;


public class CronJob {
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        ArticlePrint articleprint = new ArticlePrint(); // Create an instance of SportFiveTests
        TimerTask task = new ExpiredSchedular(articleprint); // Pass the instance to the constructor
        timer.schedule(task, 1, 7200000);

    }
}