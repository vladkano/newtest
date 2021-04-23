import catalog.*;
import filters.Filters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.Man;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogTest extends TestBase{

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        filters = new Filters(driver);
    }

    //Кол-во намименований в базе и на странице, проверка по наименованию дезайнера
    @Test
    public void designersOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
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
        driver.get(getUrl + "catalog/sergi");
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
        driver.get(getUrl + "catalog/kole");
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
        driver.get(getUrl + "catalog/koltsa");
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
        driver.get(getUrl + "catalog/broshi");
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
        assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size()));
    }


    @Test
    public void designersOfРirsing() {
        driver.get(getUrl + "catalog/pirsing");
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
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void designersOfManItems() {
        driver.get(getUrl + "catalog/men");
        man = new Man(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = man.getDesigners();
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

    //Кол-во намименование в базе и на странице, выборочная проверка по наименованию
    @Test
    public void namesOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
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
        driver.get(getUrl + "catalog/sergi");
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
        driver.get(getUrl + "catalog/kole");
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
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void namesOfRings() {
        driver.get(getUrl + "catalog/koltsa");
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
        assertEquals(sqlSize, numberOnly);
//        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void namesOfBrooches() {
        driver.get(getUrl + "catalog/broshi");
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
        driver.get(getUrl + "catalog/pirsing");
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

    @Test
    public void namesOfManItems() {
        navigation = new CatalogNavigation(driver);
        man = new Man(driver);
        driver.get(getUrl + "catalog/men/");
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = man.getNames();
        int sqlSize = sqlList.size();
        //site:
        navigation.clickOnShowMoreButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        navigation.clickOnShowMoreButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        navigation.clickOnShowMoreButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,11 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        assertEquals(sqlSize, numberOnly);
        System.out.println(sqlList);
        System.out.println(siteList);
        assertEquals(sqlList, siteList);
//        assertEquals(sqlList.get(0), siteList.get(0));
//        assertEquals(sqlList.get(10), siteList.get(10));
    }

    //Проверяем отображение картинок и их количество.
    @Test
    public void pictureOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    @Test
    public void pictureOfEarrings() {
        driver.get(getUrl + "catalog/sergi");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    @Test
    public void pictureOfNecklaces() {
        driver.get(getUrl + "catalog/kole");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    @Test
    public void pictureOfRings() {
        driver.get(getUrl + "catalog/koltsa");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    @Test
    public void pictureOfBrooches() {
        brooches = new Brooches(driver);
        driver.get(getUrl + "catalog/broshi");
        List<String> sqlList = brooches.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(sqlSize, siteSize);
    }

    @Test
    public void pictureOfPirsing() {
        pirsing = new Pirsing(driver);
        driver.get(getUrl + "catalog/pirsing");
        List<String> sqlList = pirsing.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    @Test
    public void pictureOfManItems() {
        driver.get(getUrl + "catalog/men");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    //Кол-во наименований в базе и на странице, проверка по цене.
    @Test
    public void priceOfBracelets() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/braslety");
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
        driver.get(getUrl + "catalog/sergi");
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
        driver.get(getUrl + "catalog/kole");
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
        driver.get(getUrl + "catalog/koltsa");
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
    public void priceOfBrooches() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/broshi");
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
        assertEquals(sqlList.subList(0, sqlList.size()), priceList.subList(0, priceList.size()));
    }

    @Test
    public void priceOfPirsing() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/pirsing");
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

    @Test
    public void priceOfManItems() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/men");
        man = new Man(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = man.getPrice();
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
