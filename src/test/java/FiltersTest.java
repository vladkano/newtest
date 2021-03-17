import catalog.*;
import filters.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiltersTest {

    private WebDriver driver;
    private Filters filters;
    private Material material;
    private Colors colors;
    private Size size;
    private Designers designers;
    private static Earrings earrings;
    private static Necklaces necklaces;
    private static Bracelets bracelets;
    private static Rings rings;
    private static Brooches brooches;
    private List<String> siteList = new ArrayList<>();
    private By numberOfItem = By.xpath("//h3[@class='catalog-card__name']/a");
    private int siteSize;
    private CatalogNavigation navigation;

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

        filters = new Filters(driver);
    }

    //Проверяем работу фильтров

    //ТИП ИЗДЕЛИЯ
    @Test
    public void typeOfItemEarrings() {
        earrings = new Earrings(driver);
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = earrings.getNamesForFilters();
        int sqlSize = sqlList.size();
//        System.out.println("sql size :" + sqlSize);
//        System.out.println("from sql: " + sqlList);
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
//        System.out.println("site size :" + siteSize);
//        System.out.println("from site: " + siteList);
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void typeOfItemRings() {
        rings = new Rings(driver);
        filters.clickToFilterButton();
        filters.clickToAllRingsButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = rings.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void typeOfItemNecklaces() {
        necklaces = new Necklaces(driver);
        filters.clickToFilterButton();
        filters.clickToAllNecklacesButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = necklaces.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void typeOfItemBracelets() {
        bracelets = new Bracelets(driver);
        navigation = new CatalogNavigation(driver);

        filters.clickToFilterButton();
        filters.clickToAllBraceletsButton();
//        filters.clickToFilterButton();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i <17; i++ ) {
//            navigation.clickOnShowMoreButton();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }



        List<String> sqlList = bracelets.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
//        assertEquals(sqlList.subList(0, 832), siteList.subList(0, 834));
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void typeOfItemBrooches() {
        brooches = new Brooches(driver);
        filters.clickToFilterButton();
        filters.clickToAllBroochesButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = brooches.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //МАТЕРИАЛЫ
    @Test
    public void getPearl() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToZemcugButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = material.getListOfZemcug();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getCrystals() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToKristallyButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = material.getListOfKristally();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getStones() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToKamenButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = material.getListOfKamen();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getGlass() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToStekloButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = material.getListOfSteklo();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //Из чего
    @Test
    public void getBronze() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToBronzeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = material.getListOfBronze();
        int sqlSize = sqlList.size();

        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getSilver() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToSilverButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = material.getListOfSilver();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }


    //Тест слайдера с ценой
    @Test
    public void price() {
        filters.clickToFilterButton();
        filters.clickToPriceButton();
        Actions act = new Actions(driver);
        WebElement priceBar = driver.findElement(By.xpath("//div[@class='slider-dot slider-always']"));
        Actions actions = act.dragAndDropBy(priceBar, 100, 0);
        actions.build().perform();
    }


    //Цвета
    @Test
    public void getGreen() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToGreenButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = colors.getListOfGreenColor();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getBlue() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToBlueButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = colors.getListOfBlueColor();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getMix() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToMixButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = colors.getListOfMixColor();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //Тип покрытия:
    @Test
    public void getRhodium() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToRodiiButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = colors.getListOfRodii();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getPinkGold() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToPinkGoldButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = colors.getListOfPinkGold();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //Размер кольца
//    @Test
//    public void getFirstSize() {
//        size = new Size(driver);
//        filters.clickToFilterButton();
//        size.clickToSizeButton();
//        size.clickToFirstSizeButton();
//        filters.clickToFilterButton();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<String> sqlList = size.getListOfFirstSize();
//        int sqlSize = sqlList.size();
//        List<WebElement> elements = driver.findElements(numberOfItem);
//        for (WebElement text : elements) {
//            String s = text.getAttribute("textContent");
//            siteList.add(s);
//            siteSize = siteList.size();
//        }
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        assertEquals(sqlSize, numberOnly);
//        assertEquals(sqlList.get(0), siteList.get(0));
//        assertEquals(sqlList.get(2), siteList.get(2));
//    }
//
//    @Test
//    public void getSecondSize() {
//        size = new Size(driver);
//        filters.clickToFilterButton();
//        size.clickToSizeButton();
//        size.clickToSecondSizeButton();
//        filters.clickToFilterButton();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<String> sqlList = size.getListOfSecondSize();
//        int sqlSize = sqlList.size();
//        List<WebElement> elements = driver.findElements(numberOfItem);
//        for (WebElement text : elements) {
//            String s = text.getAttribute("textContent");
//            siteList.add(s);
//            siteSize = siteList.size();
//        }
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        assertEquals(sqlSize, numberOnly);
//        assertEquals(sqlList.get(0), siteList.get(0));
//        assertEquals(sqlList.get(2), siteList.get(2));
//    }
//
//    @Test
//    public void getUniversalSize() {
//        size = new Size(driver);
//        filters.clickToFilterButton();
//        size.clickToSizeButton();
//        size.clickToUniversalSizeButton();
//        filters.clickToFilterButton();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<String> sqlList = size.getListOfUniversalSize();
//        int sqlSize = sqlList.size();
//        List<WebElement> elements = driver.findElements(numberOfItem);
//        for (WebElement text : elements) {
//            String s = text.getAttribute("textContent");
//            siteList.add(s);
//        }
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        assertEquals(sqlSize, numberOnly);
//        assertEquals(sqlList.get(0), siteList.get(0));
//        assertEquals(sqlList.get(2), siteList.get(2));
//    }


    //Дизайнеры
    @Test
    public void getSinitsyn() {
        designers = new Designers(driver);
        filters.clickToFilterButton();
        designers.clickToDesignersButton();
        designers.clickToSinitsynButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = designers.getListOfSinitsyn();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getProstoJewlry() {
        designers = new Designers(driver);
        filters.clickToFilterButton();
        designers.clickToDesignersButton();
        designers.clickToJewlryButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = designers.getListOfJewlry();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getAvgvst() {
        designers = new Designers(driver);
        filters.clickToFilterButton();
        designers.clickToDesignersButton();
        designers.clickToAvgvstButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = designers.getListOfAvgvst();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }


    //Проверка кнопки "Сбросить" фильтр
    //Внутри фильтра
    @Test
    public void resetFilter() throws InterruptedException {
        earrings = new Earrings(driver);
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        Thread.sleep(2000);
        String countHeader = filters.getCountHeader();
        filters.clickToResetButton();
        Thread.sleep(2000);
        String countHeader2 = filters.getCountHeader();
        boolean b = countHeader.equals(countHeader2);
//        System.out.println(countHeader);
//        System.out.println(countHeader2);
        assertEquals(false, b);
    }

    //Выйдя из фильтра
    @Test
    public void resetFilterExit() throws InterruptedException {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToFilterButton();
        Thread.sleep(500);
        String countHeader = filters.getCountHeader();
        filters.clickToResetButton();
        Thread.sleep(500);
        String countHeader2 = filters.getCountHeader();
        boolean b = countHeader.equals(countHeader2);
//        System.out.println(countHeader);
//        System.out.println(countHeader2);
        assertEquals(false, b);
    }

    //При перезагрузке кнопка сбросить пропадает запостил баг


    //Тесты кнопок с фильтрами(шаблоны фильтров)
    @Test
    public void filterButtonEarrings() {
        earrings = new Earrings(driver);
        filters.clickToEarringsButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = earrings.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void filterButtonRings() {
        rings = new Rings(driver);
        filters.clickToRingsButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = rings.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void filterButtonNecklaces() {
        necklaces = new Necklaces(driver);
        filters.clickToNecklacesButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = necklaces.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void filterButtonBracelets() {
        bracelets = new Bracelets(driver);
        filters.clickToBraceletsButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = bracelets.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void filterButtonBrooches() {
        brooches = new Brooches(driver);
        filters.clickToBroochesButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> sqlList = brooches.getNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
