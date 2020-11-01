import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class CatalogTest {
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

    //Кол-во единиц в базе и на странице
    @Test
    public void numberOfEarrings() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);
        List<WebElement> numbers = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
        int countOfEarrings = earrings.countEarrings();
        Assert.assertEquals(numbers.size(), countOfEarrings);
    }

    @Test
    public void numberOfNecklaces() {
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);
        List<WebElement> numbers = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
        int countOfNecklaces = necklaces.countNecklaces();
        Assert.assertEquals(numbers.size(), countOfNecklaces);
    }

    @Test
    public void numberOfBracelets() {
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);
//        bracelets.clickOnShowMoreButton();
        int countOfBracelets = bracelets.countBracelets();
        List<WebElement> numbers = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
//        List<WebElement> numbers = new WebDriverWait(driver,10,1000).until(
//                ExpectedConditions.numberOfElementsToBe(By.xpath("//h3[@class='catalog-card__name']"), countOfBracelets));
        Assert.assertEquals(numbers.size(), countOfBracelets);
    }

    @Test
    public void numberOfRings() throws InterruptedException {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);
        for (int i = 0; i < 18; i++) {
            rings.clickOnShowMoreButton();
            Thread.sleep(2000);
        }
        int countOfRings = rings.countRings();
        List<WebElement> numbers = new WebDriverWait(driver, 10, 1000).until(
                ExpectedConditions.numberOfElementsToBe(By.xpath("//h3[@class='catalog-card__name']"), countOfRings));
        Assert.assertEquals(numbers.size(), countOfRings);
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

    //Кол-во намименований в базе и на странице, проверка по цене.
    @Test
    public void priceOfBracelets() {
        int siteSize = 0;
        List<Integer> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);

        //sql:
        List<Integer> sqlList = bracelets.getPrice();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='catalog-card__price price-block']//b[@class='price-block__price']"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            siteList.add(price);
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
    public void priceOfEarrings() {
        int siteSize = 0;
        List<Integer> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);

        //sql:
        List<Integer> sqlList = earrings.getPrice();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='catalog-card__price price-block']//b[@class='price-block__price']"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            siteList.add(price);
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
    public void priceOfNecklaces() {
        int siteSize = 0;
        List<Integer> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);

        //sql:
        List<Integer> sqlList = necklaces.getPrice();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);

        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='catalog-card__price price-block']//b[@class='price-block__price']"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            siteList.add(price);
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
    public void priceOfRings() {
        int siteSize = 0;
        List<Integer> siteList = new ArrayList<>();
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);

        //sql:
        List<Integer> sqlList = rings.getPrice();
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
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='catalog-card__price price-block']//b[@class='price-block__price']"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            siteList.add(price);
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
