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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest {


    //Полностью переделать, есть баги, которые сейчас на исправлении

    private static WebDriver driver;
    private static Collection collection;
    //private String getUrl = "http://176.53.182.129:8088/catalog/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/";
    private String getUrl = "https://poisondrop.ru/catalog/";

    public List<WebElement> getListOfLinks() {
        List<WebElement> site = driver.findElements(By.xpath("//div[@class=\"catalog-card catalog__card\"]//a[text()='Покрытое серебром кольцо Etty с лазуритом (17,5)']/following::span"));
        return site;
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        collection = new Collection(driver);
    }

    //Проверка количества товаров в коллекции: база sql и на сайте
    @Test
    public void countOfCollectionItems() {
        List<String> namesItems = collection.getNamesItems();
        System.out.println(namesItems);
        List<WebElement> site = driver.findElements(By.xpath("//div[@class='catalog-card__modifications']"));
        assertEquals(namesItems.size(), site.size());
    }

    //Проверка правильности формирования ссылок
    @Test
    public void firstLinkOfItems() {
        String linkFromSql = collection.getFirstLink();
        String href = collection.getFirstHref();
        assertEquals(linkFromSql, href);
    }

    @Test
    public void secondLinkOfItems() {
        String linkFromSql = collection.getSecondLink();
        String href = collection.getSecondHref();
        assertEquals(linkFromSql, href);
    }

    //Проверка ссылок

    @Test
    public void linkOnItem() {
        collection.clickOnFirstHref();
        String linkHeader = collection.getLinkHeader();
        assertEquals("Малое серебряное кольцо-шарик с черным ситаллом, из коллекции Lollipops (18)", linkHeader);
    }

    @Test
    public void secondLinkOnItem() {
        String header = collection.getSecondHrefHeader();
        collection.clickOnSecondHref();
        String linkHeader = collection.getSecondLinkHeader();
        assertEquals(header, linkHeader);
    }


    //Проверка что под стоимостью, отображаются ссылки на другие товары в коллекции:

    @Test
    public void checkALink() {
        List<WebElement> listOfLinks = getListOfLinks();
        String s = listOfLinks.get(0).getAttribute("textContent");
        assertEquals("A", s);
    }

    @Test
    public void checkBLink() {
        List<WebElement> listOfLinks = getListOfLinks();
        String s = listOfLinks.get(2).getAttribute("textContent");
        assertEquals("B", s);
    }

    @Test
    public void checkRedLink() {
        List<WebElement> listOfLinks = getListOfLinks();
        String s = listOfLinks.get(4).getAttribute("textContent");
        assertEquals("Красный", s);
    }

    @Test
    public void checkGoldLink() {
        List<WebElement> listOfLinks = getListOfLinks();
        String s = listOfLinks.get(6).getAttribute("textContent");
        assertEquals("Золото", s);
    }

    @Test
    public void checkSilverLink() {
        List<WebElement> listOfLinks = getListOfLinks();
        String s = listOfLinks.get(8).getAttribute("textContent");
        assertEquals("Серебро", s);
    }


    //Проверка, что под товаром ссылки на другие товары коллекции не дублируются

    @Test
    public void checkDoubleA() {
        List<WebElement> listOfLinks = driver.findElements(By.xpath("//div[3]/div[2]/div[3]/div/div[1]/ul/li/span[text()='A']"));
        boolean check = listOfLinks.size() <= 2;
        System.out.println(listOfLinks.size());
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleB() {
        List<WebElement> listOfLinks = driver.findElements(By.xpath("//div[3]/div[2]/div[3]/div/div[1]/ul/li/span[text()='B']"));
        boolean check = listOfLinks.size() <= 2;
        System.out.println(listOfLinks.size());
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleRed() {
        List<WebElement> listOfLinks = driver.findElements(By.xpath("//div[3]/div[2]/div[3]/div/div[contains(@class, 'color')]/ul/li/span[text()='Красный']"));
        boolean check = listOfLinks.size() <= 1;
        System.out.println(listOfLinks.size());
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleSilver() {
        List<WebElement> listOfLinks = driver.findElements(By.xpath("//div[3]/div[2]/div[3]/div/div[contains(@class, 'picture')]/ul/li/span[text()='Серебро']"));
        boolean check = listOfLinks.size() <= 1;
        System.out.println(listOfLinks.size());
        assertEquals(true, check);
    }

    @Test
    public void checkDoubleGold() {
        List<WebElement> listOfLinks = driver.findElements(By.xpath("//div[3]/div[2]/div[3]/div/div[contains(@class, 'picture')]/ul/li/span[text()='Золото']"));
        boolean check = listOfLinks.size() <= 1;
        System.out.println(listOfLinks.size());
        assertEquals(true, check);
    }

    //Проверка, что под товаром ссылки на другие предметы коллекции работают корректно

    @Test
    public void checkLinkPhilippeAudibert() {
        String link = collection.getLinkLinkPhilippeAudibertText();
        collection.clickOnLinkPhilippeAudibert();
        String url = driver.getCurrentUrl();
        assertEquals(link, url);
    }

    @Test
    public void checkLinkLAV() {
        String link = collection.getLinkLAVText();
        collection.clickOnLinkLAV();
        String url = driver.getCurrentUrl();
        assertEquals(link, url);
    }

    @Test
    public void checkLinkAvgvst() {
        String link = collection.getLinkAvgvstText();
        collection.clickOnAvgvst();
        String url = driver.getCurrentUrl();
        assertEquals(link, url);
    }

    @Test
    public void checkLinkLisaSmith() {
        String link = collection.getLinkLisaSmith();
        collection.clickOnLisaSmith();
        String url = driver.getCurrentUrl();
        assertEquals(link, url);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
