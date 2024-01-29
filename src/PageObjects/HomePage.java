package PageObjects;

import com.mongodb.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.openqa.selenium.*;
import com.mongodb.client.result.*;

import java.sql.Driver;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomePage extends PageObjects.BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    //    String password = "a72Y53vXKjhNDAJn";
//    String userName = "shilo";
//    MongoClient mongoClient;
    MongoDatabase db;
    String Skey = "geektimenews";

    By allArticle = By.id("most_popular");
    By backButton = By.cssSelector("[href=\"https://www.geektime.co.il\"]");


    public void createDb(String dbTableName) {
        String connectionString = "mongodb+srv://yaal-2122:wsmJQ3ggbFxFtHX@cluster0.qnlfmxm.mongodb.net/GQ-Dashboard?retryWrites=true&w=majority";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        System.out.println("Created Mongo Connection successfully");
        MongoClient mongoClient = MongoClients.create(connectionString);
        this.db = mongoClient.getDatabase(dbTableName);
        System.out.println("Get database is successful");

    }
    public void mongoInsertData(String title,String subtitle,String summary,String image, String category,String date,int i) {
        MongoCollection<Document> collection = db.getCollection(Skey);
        InsertOneResult result = collection.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("Title", title)
                .append("subtitle", subtitle)
                .append("summary", summary)
                .append("image", image)
                .append("category", category)
                .append("date", date)
                .append("index", i));
    }
    //done

    public void mongoUpdateData(String title,String subtitle,String summary,String image, String category,String date,int i) {

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("index", i);


        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
                new BasicDBObject()
                        .append("Title", title)
                        .append("subtitle", subtitle)
                        .append("summary", summary)
                        .append("image", image)
                        .append("category", category)
                        .append("date", date)
                        .append("index", i));


        db.getCollection(Skey).updateOne(searchQuery, updateQuery);
    }


    public boolean dropTheTable(int i, String site)
    {
        MongoCollection<Document> collection = db.getCollection(site);
        if (collection.countDocuments() < i) {
            return true;
        }
        return false;
    }
    public void GetAllInfo(int count) throws InterruptedException {
        String image ="";


        WebElement mainArticles = driver.findElement(By.cssSelector("article[id]"));
        String summary = "";
        int i=count+1;
        Thread.sleep(6000);

        String title = mainArticles.findElement(By.className("head-title")).getText();
        String subTitle = mainArticles.findElement(By.className("head-sub-title")).getText();
        String category = mainArticles.findElement(By.tagName("a")).getText();
        String date = mainArticles.findElement(By.cssSelector(".date.sp")).getText();

        try {
            image = mainArticles.findElement(By.cssSelector(".size-large")).getAttribute("src");

        } catch (NoSuchElementException e) {  image = mainArticles.findElement(By.cssSelector(".size-full")).getAttribute("src");
        }
        List<WebElement> paragraphs = mainArticles.findElements(By.cssSelector("#content p"));
        for (WebElement paragraph : paragraphs)
        {
            summary += paragraph.getText() + "\n";
        }
        mongoUpdateData(title, subTitle, summary,image,category,date, count);
        System.out.println("Title: " + title);
        System.out.println("subTitle: " + subTitle);
        System.out.println("summary: " + summary);
        System.out.println("image: " + image);
        System.out.println("category: " + category);
        System.out.println("date: " + date);
        System.out.println("post number: " + i);
    }


    public void openAllArticles() throws InterruptedException {
        driver.get("https://www.geektime.co.il/");
        createDb("GQ-Dashboard");
        Waitviseblity(allArticle);
        //  List<WebElement> article = driver.findElements(By.cssSelector("article img"));
        List<WebElement> article = driver.findElements(By.cssSelector("article [class=\"attachment-card_cat size-card_cat wp-post-image ev-image\"]"));

        for (int i = 0; i < 9;i++)
        {
            Thread.sleep(3000);
            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
            jse2.executeScript("arguments[0].scrollIntoView(true);", article.get(i));
            Thread.sleep(3000);
            jse2.executeScript("arguments[0].click()", article.get(i));
            verifyurl(article,i);
            GetAllInfo(i);
            driver.navigate().back();
            Thread.sleep(3000);
            article = driver.findElements(By.cssSelector("article [class=\"attachment-card_cat size-card_cat wp-post-image ev-image\"]"));
        }

    }


}