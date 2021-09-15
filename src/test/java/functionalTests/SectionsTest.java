package functionalTests;

import baseForTests.TestBase;
import catalog.CatalogNavigation;
import filters.Filters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectionsTest extends TestBase {

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
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    //Проверка, что кнопки разделов на главной странице работают правильно
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
    public void forManButton() {
        man = new Man(driver);
        man.clickToManButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "catalog/men/", url);
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
    public void certificatesButton() {
        certificate = new Certificate(driver);
        certificate.clickToCertificateButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "certificate/", url);
    }

    @Test()
    public void saleButton() {
        sale = new Sale(driver);
        sale.clickToSaleButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "catalog/sale/", url);
    }

    @Test()
    public void shopsButton() {
        shops = new Shops(driver);
        shops.clickToShopsButton();
        String url = driver.getCurrentUrl();
        String shopsHeader = shops.getShopsHeader();
        assertEquals(getUrl + "shops/", url);
        assertEquals("МАГАЗИНЫ", shopsHeader);
    }

    @Test()
    public void wishListButton() {
        wishlist = new Wishlist(driver);
        wishlist.clickToWishListButton();
        String url = driver.getCurrentUrl();
        String header = wishlist.getWishListHeader();
        assertEquals(getUrl + "wishlist/", url);
        assertEquals("Избранное", header);
    }

    //Футер
    @Test()
    public void aboutButton() {
        footer = new Footer(driver);
        footer.clickToAboutButton();
        String url = driver.getCurrentUrl();
        String header = footer.getAboutHeader();
        assertEquals(getUrl + "about/", url);
        assertEquals("ПРИВЕТ\n" +
                "И ДОБРО ПОЖАЛОВАТЬ\n" +
                "В POISON DROP", header);
    }

    @Test()
    public void contactsButton() {
        footer = new Footer(driver);
        footer.clickToContactsButton();
        String url = driver.getCurrentUrl();
        String header = footer.getContactsHeader();
        assertEquals(getUrl + "contacts/", url);
        assertEquals("Контакты", header);
    }

    @Test()
    public void footerShopsButton() {
        footer = new Footer(driver);
        footer.clickToShopsButton();
        String url = driver.getCurrentUrl();
        String header = footer.getShopsHeader();
        assertEquals(getUrl + "shops/", url);
        assertEquals("МАГАЗИНЫ", header);
    }

    @Test()
    public void vacancyButton() {
        footer = new Footer(driver);
        footer.clickToVacancyButton();
        String url = driver.getCurrentUrl();
        String header = footer.getVacancyHeader();
        assertEquals(getUrl + "vacancy/", url);
        assertEquals("Доступные вакансии:", header);
    }

    @Test()
    public void soglashenieButton() {
        footer = new Footer(driver);
        footer.clickToSoglashenieButton();
        String url = driver.getCurrentUrl();
        String header = footer.getSoglashenieHeader();
        assertEquals(getUrl + "soglashenie-na-ispolzovanie-polzovatelskikh-materialov/", url);
        assertEquals("Соглашение на использование пользовательских материалов", header);
    }

    @Test()
    public void dostavkaButton() {
        footer = new Footer(driver);
        footer.clickToDostavkaButton();
        String url = driver.getCurrentUrl();
        String header = footer.getDostavkaHeader();
        assertEquals(getUrl + "dostavka-i-oplata/", url);
        assertEquals("Доставка и оплата", header);
    }

    @Test()
    public void obmenButton() {
        footer = new Footer(driver);
        footer.clickToObmenButton();
        String url = driver.getCurrentUrl();
        String header = footer.getObmenHeader();
        assertEquals(getUrl + "obmen-i-vozvrat/", url);
        assertEquals("Обмен и возврат", header);
    }

    @Test()
    public void garantiiButton() {
        footer = new Footer(driver);
        footer.clickToGarantiiButton();
        String url = driver.getCurrentUrl();
        String header = footer.getGarantiiHeader();
        assertEquals(getUrl + "garantii/", url);
        assertEquals("Гарантийный сервис", header);
    }

    @Test()
    public void ofertaButton() {
        footer = new Footer(driver);
        footer.clickToOfertaButton();
        String url = driver.getCurrentUrl();
        String header = footer.getOfertaHeader();
        assertEquals(getUrl + "oferta/", url);
        assertEquals("Оферта", header);
    }

    @Test()
    public void personalnyeDannyeButton() {
        footer = new Footer(driver);
        footer.clickToPersonalnyeDannyeButton();
        String url = driver.getCurrentUrl();
        String header = footer.getPersonalnyeDannyeHeader();
        assertEquals(getUrl + "polozhenie-ob-obrabotke-i-zashchite-personalnykh-dannykh/", url);
        assertEquals("Положение об обработке и защите персональных данных", header);
    }

    @Test()
    public void instaButton() {
        footer = new Footer(driver);
        footer.clickToInstaButton();
        String url = driver.getCurrentUrl();
        assertEquals("https://www.instagram.com/accounts/login/", url);
    }

    @Test()
    public void telegaButton() {
        footer = new Footer(driver);
        footer.clickToTelegaButton();
        String url = driver.getCurrentUrl();
        String header = footer.getTelegaHeader();
        assertEquals("https://t.me/impoisoned", url);
        assertEquals("I am Poisoned", header);
    }


    @Test()
    public void tikTokButton() {
        footer = new Footer(driver);
        footer.clickToTikTokButton();
        String url = driver.getCurrentUrl();
        assertEquals("https://www.tiktok.com/@poisondropru?lang=ru-RU", url);
    }

    @Test()
    public void youtubeButton() {
        footer = new Footer(driver);
        footer.clickToYoutubeButton();
        String url = driver.getCurrentUrl();
        String header = footer.getYoutubeHeader();
        assertEquals("https://www.youtube.com/channel/UCrZvQ1xKouTkYU_MxC6Hv4g", url);
        assertEquals("Poison Drop", header);
    }

    @Test()
    public void whatsAppButton() {
        footer = new Footer(driver);
        footer.clickToWhatsAppButton();
        String url = driver.getCurrentUrl();
        String header = footer.getWhatsAppHeader();
        assertEquals("https://api.whatsapp.com/send/?phone=74952551533&text&app_absent=0", url);
        assertEquals("Poison Drop by ООО \"ПойзонДроп\"", header);
    }


    //Создан таск https://poisondrop.atlassian.net/browse/PD-617
    //Золото и серебро:
    //Кол-во наименование в базе и на странице, выборочная проверка по наименованию

    @Test
    public void namesOfJewelry() {
        driver.get(getUrl + "jewelry/");
        jewelry = new Jewelry(driver);
        filters = new Filters(driver);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = jewelry.getNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем 1,8 и последние элементы, размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0).substring(0, 28), siteList.get(0).substring(0, 28));
        assertEquals(sqlList.get(7), siteList.get(7));
        assertEquals(sqlList.get(19).substring(0, 20), siteList.get(19).substring(0, 20));
    }

    //Проверка по наименованию дизайнера
    @Test
    public void designersOfJewelry() {
        driver.get(getUrl + "jewelry/");
        jewelry = new Jewelry(driver);
        //sql:
        List<String> sqlList = jewelry.getDesigners();
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
    public void priceOfJewelry() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(getUrl + "jewelry/");
        jewelry = new Jewelry(driver);
        //sql:
        List<Integer> sqlList = jewelry.getPrice();
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
    public void pictureOfJewelry() {
        driver.get(getUrl + "jewelry/");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    //Тренды:
    //Отображение баннеров, работа кнопки "Показать еще", наименование и порядок их отображения
    @Test
    public void bannersIsVisible() {
        driver.get(getUrl + "trend/");
        trends = new Trends(driver);
        navigation = new CatalogNavigation(driver);
        navigation.clickOnShowMoreButton();
        List<WebElement> banners = driver.findElements(trendBanners);
        //sql:
        List<String> sqlList = trends.getNames();
        //site:
        List<WebElement> elements = driver.findElements(namesOfTrends);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем кол-во
        assertEquals(trends.listOfBanners(), banners.size());
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, trends.listOfBanners() - 1), siteList.subList(0, trends.listOfBanners() - 1));
    }

    //Ссылки: переход на верную страницу
    @Test
    public void mainBannerLink() {
        driver.get(getUrl + "trend/");
        trends = new Trends(driver);
        String href = trends.getMainHref();
        trends.clickToMainHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(0);
        String s = description.replaceAll("<[^>]*>", "");
        assertEquals(href, url);
        assertEquals(s, header);
    }

    @Test
    public void firstBannerLink() {
        driver.get(getUrl + "trend/");
        trends = new Trends(driver);
        String href = trends.getFirstHref();
        trends.clickToFirstHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(1);
        assertEquals(href, url);
        assertEquals(description, header);
    }

    @Test
    public void secondBannerLink() {
        driver.get(getUrl + "trend/");
        trends = new Trends(driver);
        String href = trends.getSecondHref();
        trends.clickToSecondHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(2);
        assertEquals(href, url);
        assertEquals(description, header);
    }

    @Test
    public void thirdBannerLink() {
        driver.get(getUrl + "trend/");
        trends = new Trends(driver);
        String href = trends.getThirdHref();
        trends.clickToThirdHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(9);
        assertEquals(href, url);
        assertEquals(description, header);
    }

    @Test
    public void fourBannerLink() {
        driver.get(getUrl + "trend/");
        trends = new Trends(driver);
        String href = trends.getFourthFineHref();
        trends.clickToFourthFineHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(4);
        assertEquals(href, url);
        assertEquals(description.substring(0, 20), header.substring(0, 20));
    }


    //Дизайнеры:
    //Отображение баннеров, наименование и порядок их отображения
    //Новые
    @Test
    public void firstDesignersBannerLink() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.getDesignersFirstHref();
        designers.clickToFirstDesignerHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.getFirstLinkNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        //сравниваем размер, содержание и порядок списков
        //сравниваем 1,8 и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(7).substring(0, 20), siteList.get(7).substring(0, 20));
    }

    @Test
    public void secondDesignersBannerLink() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.getDesignersSecondHref();
        designers.clickToSecondDesignerHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.getSecondLinkNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        //сравниваем 1,8 и последние элементы, размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2).substring(0, 20), siteList.get(2).substring(0, 20));
        assertEquals(sqlList.get(sqlSize - 1), siteList.get(numberOnly - 1));
    }


    @Test
    public void thirdDesignersBannerLink() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.getDesignersThirdHref();
        designers.clickToThirdDesignerHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.getThirdLinkNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, sqlSize), siteList.subList(0, numberOnly));
    }

    //Популярные
    //Первый
    @Test
    public void firstPopularBannerLink() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.getFirstPopularHref();
        designers.clickToFirstPopularHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.getFirstPopularLinkNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        //сравниваем размер, содержание и порядок списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, numberOfFoto), siteList.subList(0, numberOfFoto));
    }

    //Последний
    @Test
    public void lastPopularBannerLink() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.getLastPopularHref();
        designers.clickToSecondPopularHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.getLastPopularLinkNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        //сравниваем размер, содержание и порядок списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, numberOfFoto), siteList.subList(0, numberOfFoto));
    }

    //Отображение корректного списка дизайнеров
    @Test
    public void listOfDesigners() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        //sql:
        List<String> sqlList = Designers.getListOfDesigners();
        //site:
        List<WebElement> elements = driver.findElements(numberOfDesigners);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем списки
        assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size()));
    }


    //Выборочная проверка ссылок и товаров по ссылкам
    @Test
    public void firstDesigner() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.getFirstDesignerHref();
        designers.clickToFirstDesignerLink();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.getFirstDesignerNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        //сравниваем размер, содержание и порядок списков
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.subList(0, sqlSize), siteList.subList(0, numberOnly));
    }

    @Test
    public void secondDesigner() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.get10DesignerHref();
        designers.clickTo10DesignerLink();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.get10DesignerItemNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        //сравниваем 1,8 и последние элементы, размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(7), siteList.get(7));
    }

    @Test
    public void thirdDesigner() {
        driver.get(getUrl + "designers/");
        designers = new Designers(driver);
        filters = new Filters(driver);
        String href = designers.get20dDesignerHref();
        designers.clickTo20DesignerLink();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //sql:
        List<String> sqlList = designers.get20DesignerItemNames();
        int sqlSize = sqlList.size();
        //site:
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(href, url);
        //сравниваем 1,8 и последние элементы, размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0).substring(0, 28), siteList.get(0).substring(0, 28));
        assertEquals(sqlList.get(2).substring(0, 27), siteList.get(2).substring(0, 27));
//        assertEquals(sqlList.get(47).substring(0, 28), siteList.get(47).substring(0, 28));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
