package functionalTests;

import baseForTests.TestBase;
import io.qameta.allure.Epic;
import mainPage.MainPageBanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тесты отображения баннеров на главной странице")
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
        List<WebElement> mainBanner = driver.findElements(By.xpath("//div[@class='main-banner']"));
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
        List<WebElement> best = driver.findElements(By.xpath("//h3[@class='catalog-card__name']/a"));
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
        List<WebElement> best = driver.findElements(By.xpath("//b[@class='price-block__price']"));
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
    //Раздел отключен 14.03.2022(поэтому проверяем, что раздел недоступен)
    @Test
    public void shopTheLookIsNotVisible() {
        int banners = driver.findElements(By.xpath("//div[@class='frisbuy-post-provider-instagram']")).size();
        assertTrue(banners <= 0);
    }


    //ссылки: переход на верную страницу
    @Test
    public void mainBannerLink() {
        banner.clickToOkButton();
        String href = banner.getMainCatalogHref();
        banner.clickToMainCatalogHref();
        String header = banner.getFirstCatalogHeader();
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals("Фильтр", header));
    }

    @Test
    public void firstBannerLink() {
        String href = banner.getCatalogHref();
        banner.clickToCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getFirstCatalogHeader();
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals("Фильтр", header));
    }

    @Test
    public void sixBannerLink() {
        String href = banner.getSixCatalogHref();
        banner.clickToSixCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getFirstCatalogHeader();
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals("Фильтр", header));
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
