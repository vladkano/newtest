import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PictureTest {

    private static WebDriver driver;
    private static EarringsPage earrings;
    private static NecklacesPage necklaces;
    private static BraceletsPage bracelets;
    private static RingsPage rings;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //Проверяем отображение картинок и их количество.

    @Test
    public void pictureOfBracelets() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);

        //sql:
        List<Integer> sqlList = bracelets.getPicture();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size() * 2;
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        Assert.assertEquals(siteSize, sqlSize);

    }

    @Test
    public void pictureOfEarrings() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);

        //sql:
        List<Integer> sqlList = earrings.getPicture();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size() * 2;
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);
        Assert.assertEquals(siteSize, sqlSize);
    }

    @Test
    public void pictureOfNecklaces() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);

        //sql:
        List<Integer> sqlList = necklaces.getPicture();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size() * 2;
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);
        Assert.assertEquals(siteSize, sqlSize);
    }


    @Test
    public void pictureOfRings() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);

        //sql:
        List<Integer> sqlList = rings.getPicture();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size() * 2;
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        for (int i = 0; i < 18; i++) {
            rings.clickOnShowMoreButton();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);
        Assert.assertEquals(siteSize, sqlSize);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
