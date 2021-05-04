
import catalog.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest extends TestBase{

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
        driver.manage().window().maximize();
        collection = new Collection(driver);
    }

    //Проверяем первый товар который входит в коллекцию: база sql и на сайте
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
        Set<WebElement> set = new HashSet(listOfLinks);
        boolean check = set.size() == listOfLinks.size();
//        System.out.println(set.size());
//        System.out.println(listOfLinks.size());
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleBracelets() {
        bracelets = new Bracelets(driver);
        driver.get(getUrl + "catalog/braslety");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet(listOfLinks);
        boolean check = set.size() == listOfLinks.size();
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleBrooches() {
        brooches = new Brooches(driver);
        driver.get(getUrl + "catalog/broshi");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet(listOfLinks);
        boolean check = set.size() == listOfLinks.size();
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleEarrings() {
        earrings = new Earrings(driver);
        driver.get(getUrl + "catalog/sergi");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet(listOfLinks);
        boolean check = set.size() == listOfLinks.size();
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleNecklaces() {
        necklaces = new Necklaces(driver);
        driver.get(getUrl + "catalog/kole");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet(listOfLinks);
        boolean check = set.size() == listOfLinks.size();
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleRings() {
        rings = new Rings(driver);
        driver.get(getUrl + "catalog/koltsa");
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        Set<WebElement> set = new HashSet(listOfLinks);
        boolean check = set.size() == listOfLinks.size();
        assertEquals(true, check);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
