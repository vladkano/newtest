
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

public class CatalogNavigationTest {
    private WebDriver driver;
    private CatalogNavigation navigation;
    private By numberOfItem = By.xpath("//h3[@class='catalog-card__name']");
    //private String getUrl = "http://176.53.182.129:8088/catalog/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/";
    private String getUrl = "https://poisondrop.ru/catalog/";

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

        navigation = new CatalogNavigation(driver);
    }

    //Кнопка "Показать ещё":
    //Кол-во единиц на странице после нажатия кнопки "Показать ещё"
    @Test()
    public void numberOfItem() {
        driver.get(getUrl + "koltsa");
        navigation.clickOnShowMoreButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(96, numbers.size());
    }

    //Проверка отсутствия кнопки "Показать ещё", отображается только если в разделе больше 48 продуктов
    @Test
    public void showMoreNotVisible() {
        driver.get(getUrl + "?type_product=klipsy");
        int numbers = driver.findElements(By.xpath("//span[text()='Показать ещё']")).size();
        assertEquals(0, numbers);
    }

    //Проверка отсутствия кнопки "Показать ещё" при переходе на последнюю страницу каталога
    @Test
    public void showMoreLastPage() {
        driver.get(getUrl + "broshi");
        navigation.clickOnShowMoreButton();
//        navigation.clickOnShowMoreButton();
        int numbers = driver.findElements(By.xpath("//span[text()='Показать ещё']")).size();
        assertEquals(0, numbers);
    }

    //Постраничная навигация:
    //Кол-во страниц в каталоге колец
    @Test
    public void numberOfPages() {
        driver.get(getUrl + "koltsa");
        double count = Math.ceil((double) navigation.countRings() / 48);
        int countOfRings = (int) count;
        int numberOfPages = Integer.parseInt(navigation.getNumberOfPages());
        assertEquals(countOfRings, numberOfPages);
    }

    //На странице 48 продуктов
    @Test()
    public void pageNumber48() {
        driver.get(getUrl + "koltsa");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(48, numbers.size());
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
