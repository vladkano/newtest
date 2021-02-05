import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.*;

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
    private Shops shops;
    private Footer footer;
    private Wishlist wishlist;
    private Certificate certificate;
    private Man man;

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
        certificate.clickToСertificatesButton();
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
        assertEquals(getUrl + "wishlist", url);
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
        String header = footer.getInstaHeader();
        assertEquals("https://www.instagram.com/poisondropru/", url);
        assertEquals("poisondropru", header);
    }

    @Test()
    public void facebookButton() {
        footer = new Footer(driver);
        footer.clickToFacebookButton();
        String url = driver.getCurrentUrl();
        String header = footer.getFacebookHeader();
        assertEquals("https://www.facebook.com/poisondrop", url);
        assertEquals("Poison Drop", header);
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
        assertEquals("+7 495 255-15-33", header);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
