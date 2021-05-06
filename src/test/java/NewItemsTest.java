import filters.Filters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.NewItems;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewItemsTest extends TestBase{

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
        driver.get(getUrl + "catalog/new/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        newItems = new NewItems(driver);
        filters = new Filters(driver);
        basket = new Basket(driver);
        basket.clickToOkButton();
    }

    //Кол-во намименование в базе и на странице, проверка по наименованию
    @Test
    public void namesOfNewItems() {
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = newItems.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем содержание и порядок списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    //Проверка по наименованию дизайнера
    @Test
    public void designersOfNewItems() {
        //sql:
        List<String> sqlList = newItems.getDesigners();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//div/a[@class='link']"));
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    //Проверка на соответствие цены на сайте цене в базе.
    @Test
    public void priceOfNewItems() {
        //sql:
        List<Integer> sqlList = newItems.getPrice();
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

    //Проверяем отображение картинок и их количество.
    @Test
    public void pictureOfNewItems() {
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
