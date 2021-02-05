import io.github.bonigarcia.wdm.WebDriverManager;
import mainPage.MainPageBanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageBannerTest {

    private WebDriver driver;
    private MainPageBanner banner;
    private By countOfBanners = By.xpath("//div[@class='banner']//h3[@class='banner__title']");

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
        banner = new MainPageBanner(driver);
    }

    //Отображение баннеров на главной странице
    @Test
    public void mainBannerIsVisible() {
        List<WebElement> mainBanner = driver.findElements(By.xpath("//div[@class='banner main-banner']"));
        assertEquals(1, mainBanner.size());
    }

    @Test
    public void bannersIsVisible() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        assertEquals(banner.listOfBanners(), banners.size());
    }


    //Отображение блока бестселлеров на главной странице в правильном порядке
    @Test
    public void bestsellersIsVisible() {
        List<String> bestsellers = new ArrayList<>();
        List<WebElement> best = driver.findElements(By.xpath("//div[@class='container index-page__bestsellers-slider']//h3[@class='catalog-card__name']"));
//        List<WebElement> elements = best.subList(0, 5);
        for (WebElement name : best) {
            String textBanner = name.getAttribute("textContent");
            bestsellers.add(textBanner);
        }
        List<String> sqlList = banner.listOfBests();
        assertEquals(sqlList, bestsellers);
    }

    @Test
    public void bestsellersDesigners() {
        List<String> bestsellers = new ArrayList<>();
        List<WebElement> best = driver.findElements(By.xpath("//div[@class='catalog-card__designer']//a"));
//        List<WebElement> elements = best.subList(0, 5);
        for (WebElement name : best) {
            String textBanner = name.getAttribute("textContent");
            bestsellers.add(textBanner);
        }
        List<String> sqlList = banner.listOfDesigners();
        assertEquals(sqlList, bestsellers);
    }

    @Test
    public void bestsellersPrice() {
        List<Integer> bestsellers = new ArrayList<>();
        List<WebElement> best = driver.findElements(By.xpath("//div[@class='container index-page__bestsellers-slider']//b"));
//        List<WebElement> elements = best.subList(0, 5);
        for (WebElement name : best) {
            String s = name.getAttribute("textContent");
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            bestsellers.add(price);
        }
        List<Integer> sqlList = banner.listPriceOfBests();
        assertEquals(sqlList, bestsellers);
    }

    //Отображение блока Shop The Look
    @Test
    public void shopTheLookIsVisible() {
        int banners = driver.findElements(countOfBanners).size();
        boolean visible = banners > 0;
        assertEquals(true, visible);
    }


    //ссылки: переход на верную страницу

    @Test
    public void mainBannerLink() {
        String href = banner.getMainCatalogHref();
        banner.clickToMainCatalogHref();
        String header = banner.getMainCatalogHeader();
        String url = driver.getCurrentUrl();
        assertEquals(href, url);
        assertEquals("Все украшения", header);

    }

    @Test
    public void firstBannerLink() {
        String href = banner.getCatalogHref();
        banner.clickToCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getMainCatalogHeader();
        assertEquals(href, url);
        assertEquals("Все украшения", header);
    }


    @Test
    public void sixBannerLink() {
        String href = banner.getSixCatalogHref();
        banner.clickToSixCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getMainCatalogHeader();
        assertEquals(href, url);
        assertEquals("Все украшения", header);
    }

    @Test
    public void bestsellersDesignerLink() {
        banner.clickToCarouselButton();
        String designerName = banner.getDesignerName();
        banner.clickToDesignerButton();
        String header = banner.getDesignerHeader();
        assertEquals(designerName, header);
    }

    @Test
    public void bestsellersNameLink() {
        String name = banner.getName();
        banner.clickToBestsellerNameButton();
        String header = banner.getBestsellerNameHeader();
        assertEquals(name, header);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
