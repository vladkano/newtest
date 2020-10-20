import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class MainPageBannerTest {

    private WebDriver driver;
    private MainPageBanner banner;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://176.53.182.129:8088/");
        banner = new MainPageBanner(driver);
    }

    //Отображение баннеров на главной странице
    @Test
    public void mainBannerIsVisible() {
        List<WebElement> mainBanner = driver.findElements(By.xpath("//div[@class='banner main-banner']"));
        Assert.assertEquals(1, mainBanner.size());
    }

    @Test
    public void bannersIsVisible() {
        List<String> listBanners = new ArrayList<>();
        List<WebElement> banners = driver.findElements(By.xpath("//div[@class='banner']//h3[@class='banner__title']"));
        for (WebElement name : banners) {
            String textBanner = name.getText();
            listBanners.add(textBanner);
        }
        List<String> sqlList = banner.listOfBanners();
        Assert.assertEquals(sqlList, listBanners);
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
        Assert.assertEquals(sqlList, bestsellers);
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
        Assert.assertEquals(sqlList, bestsellers);
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
        Assert.assertEquals(sqlList, bestsellers);
    }

    //Отображение блока Shop The Look
    @Test
    public void shopTheLookIsVisible() {
        int banners = driver.findElements(By.xpath("//div[@class='banner']//h3[@class='banner__title']")).size();
        boolean visible = banners > 0;
        Assert.assertEquals(true, visible);
    }


    //ссылки: переход на верную страницу

    @Test
    public void mainBannerLink() {
        String href = banner.getMainCatalogHref();
        banner.clickToMainCatalogHref();
        String header = banner.getMainCatalogHeader();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(href, url);
        Assert.assertEquals("ВСЕ УКРАШЕНИЯ", header);

    }

//    @Test
//    public void firstBannerLink() {
//        String href = banner.getCatalogHref();
//        banner.clickToCatalogHref();
//        String url = driver.getCurrentUrl();
//        String header = banner.getCatalogHeader();
//        Assert.assertEquals(href, url);
//        Assert.assertEquals("404", header);
//    }
//
//
//    @Test
//    public void sixBannerLink() {
//        String href = banner.getSixCatalogHref();
//        banner.clickToSixCatalogHref();
//        String url = driver.getCurrentUrl();
//        String header = banner.getCatalogHeader();
//        Assert.assertEquals(href, url);
//        Assert.assertEquals("404", header);
//    }

    @Test
    public void bestsellersDesignerLink() {
        banner.clickToCarouselButton();
        String designerName = banner.getDesignerName();
        banner.clickToDesignerButton();
        String header = banner.getDesignerHeader();
        Assert.assertEquals(designerName, header);
    }

    @Test
    public void bestsellersNameLink() {
        String name = banner.getName();
        banner.clickToBestsellerNameButton();
        String header = banner.getBestsellerNameHeader();
        Assert.assertEquals(name, header);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
