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

    WebDriver driver;
    Basket basket;
    Filters filters;
    CatalogNavigation navigation;
    Earrings earrings;
    Necklaces necklaces;
    Bracelets bracelets;
    Rings rings;
    Brooches brooches;
    Pirsing pirsing;
    Man man;
    Certificate certificate;
    Order order;
    Collection collection;
    Material material;
    Colors colors;
    Size size;
    DesignersFilter designersFilter;
    Designers designers;
    MainPageBanner banner;
    NameNecklaces nameNecklaces;
    Sale sale;
    NewItems newItems;
    Jewelry jewelry;
    Trends trends;
    ShopTheLook shopTheLook;
    Shops shops;
    Footer footer;
    Wishlist wishlist;
    Tag tag;
    MainPage mainPage;
    PersonalData personalData;

    By numberOfItem = By.xpath("//h3[@class='catalog-card__name']");
    By numberOfPictures = By.xpath("//span[@class='picture catalog-card__image-hovered']");
    By linkOfCollection = By.xpath("//ul[@class='product-modification__variants']//a");
    By countOfBanners = By.xpath("//div[@class='banner']//h3[@class='banner__title']");
    By namesOfTrends = By.xpath("//h4");
    By numberOfDesigners = By.xpath("//li[@class='index-designers__name']/a");
    By trendBanners = By.xpath("//span[@class='picture']");

    List<String> siteList = new ArrayList<>();
    List<Integer> priceList = new ArrayList<>();
    int siteSize = 0;
    final int numberOfFoto = 48;


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
