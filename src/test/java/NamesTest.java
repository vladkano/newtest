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

public class NamesTest {
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

    //Кол-во намименование в базе и на странице, выборочная проверка по наименованию
    @Test
    public void namesOfBracelets() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);

        //sql:
        List<String> sqlList = bracelets.getNames();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        //сравниваем 1,8 и последние элементы, размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(7), siteList.get(7));
        Assert.assertEquals(sqlList.get(sqlSize - 1), siteList.get(siteSize - 1));
    }

    @Test
    public void namesOfEarrings() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);

        //sql:
        List<String> sqlList = earrings.getNames();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));

    }

    @Test
    public void namesOfNecklaces() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);

        //sql:
        List<String> sqlList = necklaces.getNames();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
        System.out.println("from site: " + siteList);

        //сравниваем 1,11 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(10), siteList.get(10));
        Assert.assertEquals(sqlList.get(sqlSize - 1), siteList.get(siteSize - 1));
    }

    @Test
    public void namesOfRings() {
        int siteSize = 0;
        List<String> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);

        //sql:
        List<String> sqlList = rings.getNames();
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
        List<WebElement> elements = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);

        //сравниваем 1,11 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(siteSize, sqlSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(10), siteList.get(10));
        Assert.assertEquals(sqlList.get(sqlSize - 1), siteList.get(siteSize - 1));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
