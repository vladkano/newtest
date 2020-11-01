import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FiltersTest {

    private WebDriver driver;
    private Filters filters;
    private Material material;
    private Colors colors;
    private Size size;
    private Designers designers;
    private static EarringsPage earrings;
    private static NecklacesPage necklaces;
    private static BraceletsPage bracelets;
    private static RingsPage rings;
    private int siteSize = 0;
    private List<String> siteList = new ArrayList<>();
    private By numberOfItem = By.xpath("//h3[@class='catalog-card__name']");

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
        driver.get("http://176.53.182.129:8088/catalog");
        filters = new Filters(driver);
    }

    //Проверяем работу фильтров

    //ТИП ИЗДЕЛИЯ
    @Test
    public void typeOfItemEarrings() {
        earrings = new EarringsPage(driver);
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToFilterButton();
        List<String> sqlList = earrings.getNames();
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

        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void typeOfItemRings() {
        rings = new RingsPage(driver);
        filters.clickToFilterButton();
        filters.clickToAllRingsButton();
        filters.clickToFilterButton();

        List<String> sqlList = rings.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        //сравниваем 2,11 элементы. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
        Assert.assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void typeOfItemNecklaces() {
        necklaces = new NecklacesPage(driver);
        filters.clickToFilterButton();
        filters.clickToAllNecklacesButton();
        filters.clickToFilterButton();

        List<String> sqlList = necklaces.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(3), siteList.get(3));
        Assert.assertEquals(sqlList.get(4), siteList.get(4));
    }

    @Test
    public void typeOfItemBracelets() {
        bracelets = new BraceletsPage(driver);
        filters.clickToFilterButton();
        filters.clickToAllBraceletsButton();
        filters.clickToFilterButton();

        List<String> sqlList = bracelets.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(3), siteList.get(3));
        Assert.assertEquals(sqlList.get(4), siteList.get(4));
    }

    //МАТЕРИАЛЫ
    //Есть материалы
    @Test
    public void getPearl() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToZemcugButton();
        filters.clickToFilterButton();

        List<String> sqlList = material.getListOfZemcug();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getCrystals() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToKristallyButton();
        filters.clickToFilterButton();

        List<String> sqlList = material.getListOfKristally();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем все элементы и размеры списков.
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList, siteList);
    }

    @Test
    public void getStones() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToKamenButton();
        filters.clickToFilterButton();

        List<String> sqlList = material.getListOfKamen();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        //сравниваем 1,2 элементы. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getGlass() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToStekloButton();
        filters.clickToFilterButton();

        List<String> sqlList = material.getListOfSteklo();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем все элементы и размеры списков.
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList, siteList);
    }

    @Test
    public void getAlloy() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToSplavButton();
        filters.clickToFilterButton();

        List<String> sqlList = material.getListOfSplav();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем все элементы и размеры списков.
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList, siteList);
    }

    //Из чего
    @Test
    public void getBronze() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToBronzeButton();
        filters.clickToFilterButton();

        List<String> sqlList = material.getListOfBronze();
        int sqlSize = sqlList.size();

        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем все элементы и размеры списков.
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList, siteList);
    }

    @Test
    public void getSilver() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToSilverButton();
        filters.clickToFilterButton();

        List<String> sqlList = material.getListOfSilver();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        //сравниваем 1,2 элементы. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlList.get(2), siteList.get(2));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
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

        List<String> sqlList = colors.getListOfGreenColor();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getBlue() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToBlueButton();
        filters.clickToFilterButton();

        List<String> sqlList = colors.getListOfBlueColor();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getMix() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToMixButton();
        filters.clickToFilterButton();

        List<String> sqlList = colors.getListOfMixColor();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    //Тип покрытия
    @Test
    public void getRhodium() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToRodiiButton();
        filters.clickToFilterButton();

        List<String> sqlList = colors.getListOfRodii();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    //Тип покрытия
    @Test
    public void getPinkGold() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
        colors.clickToPinkGoldButton();
        filters.clickToFilterButton();

        List<String> sqlList = colors.getListOfPinkGold();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(9), siteList.get(9));
        Assert.assertEquals(sqlList.get(5), siteList.get(5));
    }

    //Размер кольца
    @Test
    public void getFirstSize() {
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToFirstSizeButton();
        filters.clickToFilterButton();

        List<String> sqlList = size.getListOfFirstSize();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getSecondSize() {
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToSecondSizeButton();
        filters.clickToFilterButton();

        List<String> sqlList = size.getListOfSecondSize();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getUniversalSize() {
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToUniversalSizeButton();
        filters.clickToFilterButton();

        List<String> sqlList = size.getListOfUniversalSize();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        //сравниваем 1,2 элементы. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }


    //Дизайнеры
    @Test
    public void getSinitsyn() {
        designers = new Designers(driver);
        filters.clickToFilterButton();
        designers.clickToDesignersButton();
        designers.clickToSinitsynButton();
        filters.clickToFilterButton();

        List<String> sqlList = designers.getListOfSinitsyn();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getProstoJewlry() {
        designers = new Designers(driver);
        filters.clickToFilterButton();
        designers.clickToDesignersButton();
        designers.clickToJewlryButton();
        filters.clickToFilterButton();

        List<String> sqlList = designers.getListOfJewlry();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void getAvgvst() {
        designers = new Designers(driver);
        filters.clickToFilterButton();
        designers.clickToDesignersButton();
        designers.clickToAvgvstButton();
        filters.clickToFilterButton();

        List<String> sqlList = designers.getListOfAvgvst();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        //сравниваем 1,2 элементы. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlList.get(2), siteList.get(2));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }


    //Проверка кнопки "Сбросить" фильтр
    //Внутри фильтра
    @Test
    public void resetFilter() throws InterruptedException {
        earrings = new EarringsPage(driver);
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        Thread.sleep(1000);
        String countHeader = filters.getCountHeader();
        filters.clickToResetButton();
        Thread.sleep(1000);
        String countHeader2 = filters.getCountHeader();
        boolean b = countHeader.equals(countHeader2);
//        System.out.println(countHeader);
//        System.out.println(countHeader2);
        Assert.assertEquals(false, b);
    }

    //Выйдя из фильтра
    @Test
    public void resetFilterExit() throws InterruptedException {
        earrings = new EarringsPage(driver);
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
        Assert.assertEquals(false, b);
    }

    //При перезагрузке кнопка сбросить пропадает запостил баг


    //Тесты кнопок с фильтрами(шаблоны фильтров)
    @Test
    public void filterButtonEarrings() {
        earrings = new EarringsPage(driver);
        filters.clickToEarringsButton();

        List<String> sqlList = earrings.getNames();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void filterButtonRings() {
        rings = new RingsPage(driver);
        filters.clickToRingsButton();

        List<String> sqlList = rings.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        //сравниваем 2,11 элементы. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
        Assert.assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void filterButtonNecklaces() {
        necklaces = new NecklacesPage(driver);
        filters.clickToNecklacesButton();

        List<String> sqlList = necklaces.getNames();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize,siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void filterButtonBracelets() {
        bracelets = new BraceletsPage(driver);
        filters.clickToBraceletsButton();

        List<String> sqlList = bracelets.getNames();
        Collections.sort(sqlList);
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            Collections.sort(siteList);
            siteSize = siteList.size();
        }
        //сравниваем 1,2 элементы и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assert.assertEquals(sqlSize, siteSize);
        Assert.assertEquals(sqlList.get(0), siteList.get(0));
        Assert.assertEquals(sqlList.get(1), siteList.get(1));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
