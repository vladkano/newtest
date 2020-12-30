import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectionsTest {
    private WebDriver driver;
    private NewItems newItems;
    private Jewelry jewelry;
    private Trends trends;
    private Designers designers;
    private ShopTheLook shopTheLook;
    private Sale sale;
    //private String getUrl = "http://176.53.182.129:8088/";
    //private String getUrl = "http://176.53.181.34:8088/";
    private String getUrl = "https://poisondrop.ru/";

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
    }

    //Проверка что кнопки разделов на главной странице работают правильно
    @Test()
    public void newItemsButton() {
        newItems = new NewItems(driver);
        newItems.clickToNewItemsButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "catalog/new/", url);
    }

    @Test()
    public void jewelryButton() {
        jewelry = new Jewelry(driver);
        jewelry.clickToJewelryButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "jewelry/", url);
    }

    @Test()
    public void trendsButton() {
        trends = new Trends(driver);
        trends.clickToTrendsButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "trend/", url);
    }

    @Test()
    public void designersButton() {
        designers = new Designers(driver);
        designers.clickToDesignersButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "designers/", url);
    }

    @Test()
    public void shopTheLookButton() {
        shopTheLook = new ShopTheLook(driver);
        shopTheLook.clickToShopTheLookButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "shop-the-look/", url);
    }

    @Test()
    public void saleButton() {
        sale = new Sale(driver);
        sale.clickToSaleButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "catalog/sale/", url);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
