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

public class DesignerTest {
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

    //Кол-во намименований в базе и на странице, проверка по наименованию дезайнера

    @Test
    public void designersOfBracelets() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);

        //sql:
        List<String> sqlList = bracelets.getDesigners();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        //сравниваем размеры и содержание списков
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(siteList, sqlList);
    }

    @Test
    public void designersOfEarrings() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);

        //sql:
        List<String> sqlList = earrings.getDesigners();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        //сравниваем размеры и содержание списков
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(siteList, sqlList);

    }

    @Test
    public void designersOfNecklaces() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);

        //sql:
        List<String> sqlList = necklaces.getDesigners();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        //сравниваем размеры и содержание списков
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(siteList, sqlList);
    }

    @Test
    public void designersOfRings() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);

        //sql:
        List<String> sqlList = rings.getDesigners();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
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
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        //сравниваем размеры и содержание списков
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(siteList, sqlList);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
