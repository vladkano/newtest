package functionalTests;

import baseForTests.TestBase;
import mainPage.MainPageBanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageBannerTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl);
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
        assertEquals(12, banners.size());
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
        assertTrue(banners > 0);
    }


    //ссылки: переход на верную страницу
    @Test
    public void mainBannerLink() {
        banner.clickToOkButton();
        String href = banner.getMainCatalogHref();
        banner.clickToMainCatalogHref();
        String header = banner.getMainBannerHeader();
        String url = driver.getCurrentUrl();
        assertEquals(href, url);
        assertEquals(" Стилизация уха – это такая игра. Соберите, как конструктор," +
                " сет из каффов и серег, замените пару деталей, поэкспериментируйте... ", header);
    }

    @Test
    public void firstBannerLink() {
        String href = banner.getCatalogHref();
        banner.clickToCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getMainCatalogHeader();
        assertEquals(href, url);
        assertEquals("Фильтр", header);
    }

    @Test
    public void sixBannerLink() {
        String href = banner.getSixCatalogHref();
        banner.clickToSixCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getMainCatalogHeader();
        assertEquals(href, url);
        assertEquals("Фильтр", header);
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
