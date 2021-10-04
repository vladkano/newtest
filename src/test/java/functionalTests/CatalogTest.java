package functionalTests;

import baseForTests.TestBase;
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

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        filters = new Filters(driver);
    }


    //Кол-во наименований в базе и на странице, проверка по наименованию дизайнера
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
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем размеры и содержание списков
        SoftAssertions s = new SoftAssertions();
        s.assertThat(sqlSize).isEqualTo(numberOnly);
        s.assertThat(siteList.subList(0, 47)).isEqualTo(sqlList.subList(0, 47));
        s.assertAll();

    }

    @Test
    public void designersOfNecklaces() {
        driver.get(getUrl + "catalog/kole");
        necklaces = new Necklaces(driver);
        String countHeader = filters.getCountHeader();
        int numberOnly = Integer.parseInt(countHeader.replaceAll("[^0-9]", ""));
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
    public void designersOfPirsing() {
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
        assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size()));
    }

    @Test
    public void designersOfMenItems() {
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
        //сравниваем содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    //Кол-во наименование в базе и на странице, проверка по наименованию
    @Test
    public void namesOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
        bracelets = new Bracelets(driver);
        //sql:
        List<String> sqlList = bracelets.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 6));
        }
        //сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void namesOfEarrings() {
        driver.get(getUrl + "catalog/sergi");
        earrings = new Earrings(driver);
        //sql:
        List<String> sqlList = earrings.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 5));
        }
        //сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void namesOfNecklaces() {
        driver.get(getUrl + "catalog/kole");
        necklaces = new Necklaces(driver);
        //sql:
        List<String> sqlList = necklaces.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 4));
        }
        //сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void namesOfRings() {
        driver.get(getUrl + "catalog/koltsa");
        rings = new Rings(driver);
        //sql:
        List<String> sqlList = rings.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 6));
        }
        //сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void namesOfBrooches() {
        driver.get(getUrl + "catalog/broshi");
        brooches = new Brooches(driver);
        //sql:
        List<String> sqlList = brooches.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 9));
        }
        //сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, siteList.size()), siteList.subList(0, siteList.size()));
    }

    @Test
    public void namesOfPirsing() {
        driver.get(getUrl + "catalog/pirsing");
        pirsing = new Pirsing(driver);
        //sql:
        List<String> sqlList = pirsing.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, siteList.size()), siteList.subList(0, siteList.size()));
    }

    @Test
    public void namesOfMenItems() {
        navigation = new CatalogNavigation(driver);
        man = new Man(driver);
        driver.get(getUrl + "catalog/men/");
        //sql:
        List<String> sqlList = man.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 9));
        }
        //сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, siteList.size()), siteList.subList(0, siteList.size()));
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
        List<WebElement> elements = driver.findElements(numberOfPictures);
        List<Integer> sqlList = pirsing.getPrice();
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(sqlList.size(), siteSize);
    }

    @Test
    public void pictureMenItems() {
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
        //sql:
        List<Integer> sqlList = bracelets.getPrice();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOfEarrings() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/sergi");
        earrings = new Earrings(driver);
        //sql:
        List<Integer> sqlList = earrings.getPrice();
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
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOfNecklaces() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/kole");
        necklaces = new Necklaces(driver);
        //sql:
        List<Integer> sqlList = necklaces.getPrice();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOfRings() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/koltsa");
        rings = new Rings(driver);
        //sql:
        List<Integer> sqlList = rings.getPrice();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void priceOfBrooches() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/broshi");
        brooches = new Brooches(driver);
        //sql:
        List<Integer> sqlList = brooches.getPrice();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, sqlList.size()), priceList.subList(0, priceList.size()));
    }

    @Test
    public void priceOfPirsing() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/pirsing");
        pirsing = new Pirsing(driver);
        //sql:
        List<Integer> sqlList = pirsing.getPrice();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, sqlList.size()), priceList.subList(0, priceList.size()));
    }

    @Test
    public void priceOfMenItems() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "catalog/men");
        man = new Man(driver);
        //sql:
        List<Integer> sqlList = man.getPrice();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='price-block__main']/b"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
