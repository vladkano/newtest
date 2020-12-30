import catalog.*;
import filters.Filters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogTest {
    private static WebDriver driver;
    private static Earrings earrings;
    private static Necklaces necklaces;
    private static Bracelets bracelets;
    private static Rings rings;
    private static Brooches brooches;
    private static Pirsing pirsing;
    private By numberOfItem = By.xpath("//h3[@class='catalog-card__name']");
    private Filters filters;
    private List<String> siteList = new ArrayList<>();
    private int siteSize = 0;
    //private String getUrl = "http://176.53.182.129:8088/catalog/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/";
    private String getUrl = "https://poisondrop.ru/catalog/";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        filters = new Filters(driver);
    }

    //Кол-во намименований в базе и на странице, проверка по наименованию дезайнера
    @Test
    public void designersOfBracelets() {
        driver.get(getUrl + "braslety");
        bracelets = new Bracelets(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = bracelets.getDesigners();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void designersOfEarrings() {
        driver.get(getUrl + "sergi");
        earrings = new Earrings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = earrings.getDesigners();
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);
        //сравниваем размеры и содержание списков
        SoftAssertions s = new SoftAssertions();
        s.assertThat(numberOnly).isEqualTo(sqlSize);
        s.assertThat(siteList.subList(0, 47)).isEqualTo(sqlList.subList(0, 47));
        s.assertAll();
//        assertEquals(sqlSize + " товаров", countHeader);
//        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));

    }

    @Test
    public void designersOfNecklaces() {
        driver.get(getUrl + "kole");
        necklaces = new Necklaces(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = necklaces.getDesigners();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void designersOfRings() {
        driver.get(getUrl + "koltsa");
        rings = new Rings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = rings.getDesigners();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void designersOfBrooches() {
        driver.get(getUrl + "broshi");
        brooches = new Brooches(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = brooches.getDesigners();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void designersOfРirsing() {
        driver.get(getUrl + "pirsing");
        pirsing = new Pirsing(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = pirsing.getDesigners();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, sqlSize), siteList.subList(0, siteList.size()));
    }

    //Кол-во намименование в базе и на странице, выборочная проверка по наименованию
    @Test
    public void namesOfBracelets() {
        driver.get(getUrl + "braslety");
        bracelets = new Bracelets(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = bracelets.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,8 и последние элементы, размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(7), siteList.get(7));
    }

    @Test
    public void namesOfEarrings() {
        driver.get(getUrl + "sergi");
        earrings = new Earrings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = earrings.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void namesOfNecklaces() {
        driver.get(getUrl + "kole");
        necklaces = new Necklaces(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = necklaces.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,11 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        System.out.println(sqlSize);
        System.out.println(numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void namesOfRings() {
        driver.get(getUrl + "koltsa");
        rings = new Rings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = rings.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,11 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
//        assertEquals(sqlList.get(0), siteList.get(0));
//        assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void namesOfBrooches() {
        driver.get(getUrl + "broshi");
        brooches = new Brooches(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = brooches.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,11 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void namesOfPirsing() {
        driver.get(getUrl + "pirsing");
        pirsing = new Pirsing(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = pirsing.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,11 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(10), siteList.get(10));
    }

    //Проверяем отображение картинок и их количество.
    @Test
    public void pictureOfBracelets() {
        driver.get(getUrl + "braslety");
//        bracelets = new catalog.Bracelets(driver);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(96, siteSize);
    }

    @Test
    public void pictureOfEarrings() {
        driver.get(getUrl + "sergi");
//        earrings = new catalog.Earrings(driver);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(96, siteSize);
    }

    @Test
    public void pictureOfNecklaces() {
        driver.get(getUrl + "kole");
//        necklaces = new catalog.Necklaces(driver);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(96, siteSize);
    }

    @Test
    public void pictureOfRings() {
        driver.get(getUrl + "koltsa");
//        rings = new catalog.Rings(driver);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(96, siteSize);
    }

    @Test
    public void pictureOfBrooches()  {
        driver.get(getUrl + "broshi");
//        brooches = new catalog.Brooches(driver);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(96, siteSize);
    }

    @Test
    public void pictureOfPirsing()  {
        pirsing = new Pirsing(driver);
        driver.get(getUrl + "pirsing");
        List<String> sqlList = pirsing.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='page__content']//img"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(sqlSize*2, siteSize);
    }

    //Кол-во наименований в базе и на странице, проверка по цене.
    @Test
    public void priceOfBracelets() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "braslety");
        bracelets = new Bracelets(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = bracelets.getPrice();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOfEarrings() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "sergi");
        earrings = new Earrings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = earrings.getPrice();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOfNecklaces() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "kole");
        necklaces = new Necklaces(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = necklaces.getPrice();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOfRings() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "koltsa");
        rings = new Rings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = rings.getPrice();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOBrooches() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "broshi");
        brooches = new Brooches(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = brooches.getPrice();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOPirsing() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "pirsing");
        pirsing = new Pirsing(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = pirsing.getPrice();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
