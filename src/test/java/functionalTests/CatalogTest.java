package functionalTests;

import baseForTests.TestBase;
import catalog.*;
import filters.Filters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import sections.Man;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        filters = new Filters(driver);
    }

    public void getDesignerNamesFromSite() {
        List<WebElement> elements = driver.findElements(designerName);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
    }

    public void getPicturesFromSite() {
        driver.get(getUrl + "catalog/braslety");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    public void getPricesFromSite() {
        List<WebElement> elements = driver.findElements(price);
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
    }


    //Кол-во наименований в базе и на странице, проверка по наименованию дизайнера
    @Test
    public void braceletDesigners() {
        driver.get(getUrl + "catalog/braslety");
        bracelets = new Bracelets(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = bracelets.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        //сравниваем размеры и содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    @Test
    public void earringDesigners() {
        driver.get(getUrl + "catalog/sergi");
        earrings = new Earrings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = earrings.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));

    }

    @Test
    public void necklacesDesigners() {
        driver.get(getUrl + "catalog/kole");
        necklaces = new Necklaces(driver);
        String countHeader = filters.getCountHeader();
        int numberOnly = Integer.parseInt(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = necklaces.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        //сравниваем размеры и содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    @Test
    public void ringDesigners() {
        driver.get(getUrl + "catalog/koltsa");
        rings = new Rings(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = rings.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        //сравниваем размеры и содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    @Test
    public void broochDesigners() {
        driver.get(getUrl + "catalog/broshi");
        brooches = new Brooches(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = brooches.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        //сравниваем размеры и содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }


    @Test
    public void piercingDesigners() {
        driver.get(getUrl + "catalog/pirsing");
        pirsing = new Pirsing(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = pirsing.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        //сравниваем размеры и содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size())));
    }

    @Test
    public void menItemDesigners() {
        driver.get(getUrl + "catalog/men");
        man = new Man(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = man.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        //сравниваем содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    //Кол-во наименование в базе и на странице, проверка по наименованию
    @Test
    public void braceletNames() {
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
        //Сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void earringNames() {
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
        //Сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }


    @Test
    public void necklaceNames() {
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
        //Сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void ringNames() {
        driver.get(getUrl + "catalog/koltsa");
        rings = new Rings(driver);
        //sql:
        List<String> sqlList = rings.getNames();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
//            siteList.add(s);
            siteList.add(s.substring(0, 12));
        }
        //Сравниваем первые несколько символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    @Test
    public void broochNames() {
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
        //Сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, siteList.size()), siteList.subList(0, siteList.size()));
    }

    @Test
    public void piercingNames() {
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
        //Сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, siteList.size()), siteList.subList(0, siteList.size()));
    }

    @Test
    public void menItemsNames() {
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
        //Сравниваем первые 9 символов в названии. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.subList(0, siteList.size()), siteList.subList(0, siteList.size()));
    }

    //Проверяем отображение картинок и их количество.
    @Test
    public void picturesOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
        getPicturesFromSite();
    }

    @Test
    public void picturesOfEarrings() {
        driver.get(getUrl + "catalog/sergi");
        getPicturesFromSite();
    }

    @Test
    public void picturesOfNecklaces() {
        driver.get(getUrl + "catalog/kole");
        getPicturesFromSite();
    }

    @Test
    public void picturesOfRings() {
        driver.get(getUrl + "catalog/koltsa");
        getPicturesFromSite();
    }

    @Test
    public void picturesOfBrooches() {
        driver.get(getUrl + "catalog/broshi");
        getPicturesFromSite();
    }

    @Test
    public void picturesOfPiercing() {
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
    public void picturesMenItems() {
        driver.get(getUrl + "catalog/men");
        getPicturesFromSite();
    }

    //Кол-во наименований в базе и на странице, проверка по цене.
    @Test
    public void braceletsPrice() {
        driver.get(getUrl + "catalog/braslety");
        bracelets = new Bracelets(driver);
        //sql:
        List<Integer> sqlList = bracelets.getPrice();
        getPricesFromSite();
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void earringsPrice() {
        driver.get(getUrl + "catalog/sergi");
        earrings = new Earrings(driver);
        //sql:
        List<Integer> sqlList = earrings.getPrice();
        getPricesFromSite();
        //сравниваем размеры и содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void necklacesPrice() {
        driver.get(getUrl + "catalog/kole");
        necklaces = new Necklaces(driver);
        //sql:
        List<Integer> sqlList = necklaces.getPrice();
        getPricesFromSite();
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void ringsPrice() {
        driver.get(getUrl + "catalog/koltsa");
        rings = new Rings(driver);
        //sql:
        List<Integer> sqlList = rings.getPrice();
        getPricesFromSite();
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void broochesPrice() {
        driver.get(getUrl + "catalog/broshi");
        brooches = new Brooches(driver);
        //sql:
        List<Integer> sqlList = brooches.getPrice();
        getPricesFromSite();
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @Test
    public void piercingPrice() {
        driver.get(getUrl + "catalog/pirsing");
        pirsing = new Pirsing(driver);
        //sql:
        List<Integer> sqlList = pirsing.getPrice();
        getPricesFromSite();
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, sqlList.size()), priceList.subList(0, priceList.size()));
    }

    @Test
    public void menItemsPrice() {
        driver.get(getUrl + "catalog/men");
        man = new Man(driver);
        //sql:
        List<Integer> sqlList = man.getPrice();
        getPricesFromSite();
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
