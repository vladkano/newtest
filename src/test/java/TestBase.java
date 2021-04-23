import catalog.*;
import filters.*;
import filters.DesignersFilter;
import mainPage.MainPage;
import mainPage.MainPageBanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import personal.PersonalData;
import sections.*;

import java.util.ArrayList;
import java.util.List;

public class TestBase {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    WebDriver driver;
    Basket basket;
    Filters filters;
    CatalogNavigation navigation;
    By numberOfItem = By.xpath("//h3[@class='catalog-card__name']");
    Earrings earrings;
    Necklaces necklaces;
    Bracelets bracelets;
    Rings rings;
    Brooches brooches;
    Pirsing pirsing;
    Man man;
    By numberOfPictures = By.xpath("//span[@class='picture catalog-card__image-hovered']");
    final int numberOfFoto = 48;
    List<String> siteList = new ArrayList<>();
    int siteSize = 0;
    Certificate certificate;
    Order order;
    By linkOfCollection = By.xpath("//ul[@class='product-modification__variants']//a");
    Collection collection;
    Material material;
    Colors colors;
    Size size;
    DesignersFilter designersFilter;
    Designers designers;
    MainPageBanner banner;
    By countOfBanners = By.xpath("//div[@class='banner']//h3[@class='banner__title']");
    NameNecklaces nameNecklaces;
    Sale sale;
    List<Integer> priceList = new ArrayList<>();
    NewItems newItems;
    Jewelry jewelry;
    Trends trends;
    ShopTheLook shopTheLook;
    Shops shops;
    Footer footer;
    Wishlist wishlist;
    By namesOfTrends = By.xpath("//h4");
    By numberOfDesigners = By.xpath("//li[@class='index-designers__name']/a");
    Tag tag;
    By trendBanners = By.xpath("//span[@class='picture']");
    MainPage mainPage;
    PersonalData personalData;




    //Бой
    String getUrl = "https://poisondrop.ru/";
    //Тест
//    String getUrl = "https://qa.poisondrop.org.ru/";






    //Не используемые адреса
    //тест нового сервера
    //private String getUrl = "http://77.223.106.149/catalog/";

    //тесты
    //private String getUrl = "http://176.53.182.129:8088/catalog/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/";





//    @BeforeEach
//    public void start() {
//
//        WebDriverManager.chromedriver().setup();
////        WebDriverManager.firefoxdriver().setup();
////        WebDriverManager.edgedriver().setup();
//        ChromeOptions options = new ChromeOptions();
////        options.setHeadless(true);
//        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//        driver = new ChromeDriver(options);
////        driver = new FirefoxDriver(options);
////        driver = new EdgeDriver(options);
//
//        driver.get(getUrl);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//
//        Runtime.getRuntime().addShutdownHook(
//                new Thread(() -> { driver.quit(); driver = null; }));
//    }

}
