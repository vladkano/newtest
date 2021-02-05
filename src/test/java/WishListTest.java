import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import sections.Wishlist;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishListTest {

    private WebDriver driver;
    private Wishlist wishlist;

    //private String getUrl = "http://176.53.182.129:8088/";
    //private String getUrl = "http://176.53.181.34:8088/";
    private String getUrl = "https://poisondrop.ru/";


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver();
        driver.get(getUrl+ "catalog/");
        wishlist = new Wishlist(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    //проверка что товар добавился в избранное + урл и заголовок корректны
    @Test()
    public void wishListCheckButton() {
        wishlist.clickToItemButton();
        wishlist.clickToWishListInCardListButton();
        wishlist.clickToWishListButton();
        int numbers = driver.findElements(By.xpath("//h3/a")).size();
        boolean a = numbers > 0;
        assertEquals(a, true);
        String url = driver.getCurrentUrl();
        String header = wishlist.getWishListHeader();
        assertEquals(getUrl + "wishlist", url);
        assertEquals("Избранное", header);
    }


    //перенос из избранного в корзину

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
