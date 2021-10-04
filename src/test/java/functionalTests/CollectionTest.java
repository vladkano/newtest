package functionalTests;

import baseForTests.TestBase;
import catalog.*;
import collectionAndSet.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        collection = new Collection(driver);
    }

    //Проверяем первый товар, который входит в коллекцию: база sql и на сайте
    @Test
    public void countOfCollectionItems() {
        driver.get(getUrl + "catalog/");
        List<String> namesItems = collection.getNamesItems();
        List<WebElement> site = driver.findElements(By.xpath("//h3[@class='catalog-card__name']"));
        assertEquals(namesItems.get(0), site.get(0).getAttribute("textContent"));
    }

    //Проверка правильности формирования ссылок и их работоспособность
    @Test
    public void firstLinkOfItems() {
        driver.get(getUrl + "catalog/");
        String linkFromSql = collection.getFirstLink();
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(linkFromSql, href);
        assertEquals(url, href);
    }

    @Test
    public void firstLinkOfBracelets() {
        bracelets = new Bracelets(driver);
        driver.get(getUrl + "catalog/braslety");
        String linkFromSql = bracelets.getFirstLinkOfCollection();
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(linkFromSql, href);
        assertEquals(url, href);
    }

    @Test
    public void firstLinkOfBrooches() {
        brooches = new Brooches(driver);
        driver.get(getUrl + "catalog/broshi");
        String linkFromSql = brooches.getFirstLinkOfCollection();
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(linkFromSql, href);
        assertEquals(url, href);
    }

    @Test
    public void firstLinkOfEarrings() {
        earrings = new Earrings(driver);
        driver.get(getUrl + "catalog/sergi");
        String linkFromSql = earrings.getFirstLinkOfCollection();
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(linkFromSql, href);
        assertEquals(url, href);
    }

    @Test
    public void firstLinkOfNecklaces() {
        necklaces = new Necklaces(driver);
        driver.get(getUrl + "catalog/kole");
        String linkFromSql = necklaces.getFirstLinkOfCollection();
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(linkFromSql, href);
        assertEquals(url, href);
    }

    @Test
    public void firstLinkOfRings() {
        rings = new Rings(driver);
        driver.get(getUrl + "catalog/koltsa");
        String linkFromSql = rings.getFirstLinkOfCollection();
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(linkFromSql, href);
        assertEquals(url, href);
    }

    //Проверка, что под товаром ссылки на другие товары коллекции не дублируются
    @Test
    public void checkDoubleMain() {
        driver.get(getUrl + "catalog/");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        HashSet<WebElement> set = new HashSet<>(listOfLinks);
        assertEquals(set.size(), listOfLinks.size());
    }

    @Test
    public void checkDoubleBracelets() {
        driver.get(getUrl + "catalog/braslety");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet<>(listOfLinks);
        assertEquals(set.size(), listOfLinks.size());
    }

    @Test
    public void checkDoubleBrooches() {
        driver.get(getUrl + "catalog/broshi");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet<>(listOfLinks);
        assertEquals(set.size(), listOfLinks.size());
    }

    @Test
    public void checkDoubleEarrings() {
        driver.get(getUrl + "catalog/sergi");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet<>(listOfLinks);
        assertEquals(set.size(), listOfLinks.size());
    }

    @Test
    public void checkDoubleNecklaces() {
        driver.get(getUrl + "catalog/kole");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet<>(listOfLinks);
        assertEquals(set.size(), listOfLinks.size());
    }

    @Test
    public void checkDoubleRings() {
        driver.get(getUrl + "catalog/koltsa");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet<>(listOfLinks);
        assertEquals(set.size(), listOfLinks.size());
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
