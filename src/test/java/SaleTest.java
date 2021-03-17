
import filters.Filters;
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
import sections.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTest {

    private static WebDriver driver;
    private Sale sale;
    private Filters filters;
    private List<String> siteList = new ArrayList<>();
    private By numberOfItem = By.xpath("//h3[@class='catalog-card__name']");
    private List<Integer> priceList = new ArrayList<>();

    //private String getUrl = "http://176.53.182.129:8088/catalog/sale/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/sale/";
    private String getUrl = "https://poisondrop.ru/catalog/sale/";

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
        driver.get(getUrl);
        sale = new Sale(driver);
        filters = new Filters(driver);
    }

    //Кол-во намименование в базе и на странице, выборочная проверка по наименованию
    @Test
    public void namesOfSale() {
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = sale.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем размеры и содержание списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    //Кол-во намименований в базе и на странице, проверка по наименованию дезайнера
    @Test
    public void designersOfSale() {
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = sale.getDesigners();
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

    //Кол-во наименований в базе и на странице, проверка по цене со скидкой.
    @Test
    public void finalPriceOfSale() {
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = sale.getFinalPrice();
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

    //Кол-во наименований в базе и на странице, проверка по цене без скидки.
    @Test
    public void priceOfSale() {
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<Integer> sqlList = sale.getOldPrice();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='price-block__price price-block__price_old']"));
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

    //Проверка отображаения размера скидки.
    @Test
    public void saleOfSale() {
        //sql:
        List<Integer> sqlList = sale.getSale();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='price-block__sale']"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer sale = parseInt(result);
            priceList.add(sale);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
