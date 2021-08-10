package baseForTests;

import base.Base;
import basket.Basket;
import catalog.*;
import collectionAndSet.Collection;
import collectionAndSet.Set;
import filters.*;
import filters.DesignersFilter;
import mainPage.MainPage;
import mainPage.MainPageBanner;
import order.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import personal.PersonalData;
import productCards.Picture;
import productCards.ProductCard;
import search.Search;
import sections.*;
import tags.Tags;

import java.util.ArrayList;
import java.util.List;

public class TestBase {

    protected WebDriver driver;
    protected Basket basket;
    protected Filters filters;
    protected CatalogNavigation navigation;
    protected Earrings earrings;
    protected Necklaces necklaces;
    protected Bracelets bracelets;
    protected Rings rings;
    protected Brooches brooches;
    protected Pirsing pirsing;
    protected Man man;
    protected Certificate certificate;
    protected Order order;
    protected Collection collection;
    protected Material material;
    protected Colors colors;
    protected Size size;
    protected DesignersFilter designersFilter;
    protected Designers designers;
    protected MainPageBanner banner;
    protected NameNecklaces nameNecklaces;
    protected Sale sale;
    protected NewItems newItems;
    protected Jewelry jewelry;
    protected Trends trends;
    protected ShopTheLook shopTheLook;
    protected Shops shops;
    protected Footer footer;
    protected Wishlist wishlist;
    protected Tags tag;
    protected MainPage mainPage;
    protected PersonalData personalData;
    protected Search search;
    protected Picture picture;
    protected Set set;
    protected ProductCard productCard;
    protected Base base;


    protected By numberOfItem = By.xpath("//h3[@class='catalog-card__name']");
    protected By numberOfPictures = By.xpath("//span[@class='picture catalog-card__image-hovered']");
    protected By linkOfCollection = By.xpath("//ul[@class='product-modification__variants']//a");
    protected By countOfBanners = By.xpath("//div[@class='banner']//h3[@class='banner__title']");
    protected By namesOfTrends = By.xpath("//h4");
    protected By numberOfDesigners = By.xpath("//li[@class='index-designers__name']/a");
    protected By trendBanners = By.xpath("//span[@class='picture']");
    protected By nameLink = By.xpath("//h3[@class='catalog-card__name']/a");

    protected List<String> siteList = new ArrayList<>();
    protected List<Integer> priceList = new ArrayList<>();
    protected int siteSize = 0;
    protected final int numberOfFoto = 48;


    //Бой
    protected String getUrl = "https://poisondrop.ru/";
    //Тест
//    protected String getUrl = "https://qa.poisondrop.org.ru/";

    //Накст
//    protected String getUrl = "https://nuxt.poisondrop.org.ru/";

    //1С(Сталинград)
//    protected String getUrl = "https://stalingrad.poisondrop.org.ru/";

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
