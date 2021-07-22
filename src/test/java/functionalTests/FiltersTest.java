package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import catalog.*;
import filters.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiltersTest extends TestBase {

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
        driver.get(getUrl + "catalog/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        filters = new Filters(driver);
        basket = new Basket(driver);
        basket.clickToOkButton();
    }

    //Проверяем работу фильтров

    //ТИП ИЗДЕЛИЯ
    @Test
    public void typeOfItemEarrings() {
        earrings = new Earrings(driver);
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToFilterButton();
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
        assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void typeOfItemRings() {
        rings = new Rings(driver);
        filters.clickToFilterButton();
        filters.clickToAllRingsButton();
        filters.clickToFilterButton();
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
        assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void typeOfItemNecklaces() {
        necklaces = new Necklaces(driver);
        filters.clickToFilterButton();
        filters.clickToAllNecklacesButton();
        filters.clickToFilterButton();
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
        assertEquals(sqlList.get(0).substring(0, 28), siteList.get(0).substring(0, 28));
        assertEquals(sqlList.get(8).substring(0, 23), siteList.get(8).substring(0, 23));
    }

    @Test
    public void typeOfItemBracelets() {
        bracelets = new Bracelets(driver);
        navigation = new CatalogNavigation(driver);
        filters.clickToFilterButton();
        filters.clickToAllBraceletsButton();
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
        List<String> sqlList = brooches.getNamesForFilters();
        int sqlSize = sqlList.size();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfItem));
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
        assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //МАТЕРИАЛЫ
    @Test
    public void getPearl() {
        material = new Material(driver);
        navigation = new CatalogNavigation(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToZemcugButton();
        filters.clickToFilterButton();
        navigation.clickOnShowMoreButton();
//        navigation.clickOnShowMoreButton();
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
//        assertEquals(sqlList, siteList);
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
        assertEquals(sqlList.get(1), siteList.get(1));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getStones() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToKamenButton();
        filters.clickToFilterButton();
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
        assertEquals(sqlList.get(0).substring(0, 9), siteList.get(0).substring(0, 9));
        assertEquals(sqlList.get(2).substring(0, 22), siteList.get(2).substring(0, 22));
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
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
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
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getUniversalSize() {
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToUniversalSizeButton();
        filters.clickToFilterButton();
        List<String> sqlList = size.getListOfUniversalSize();
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


    //Дизайнеры
    @Test
    public void getSinitsyn() {
        designersFilter = new DesignersFilter(driver);
        filters.clickToFilterButton();
        designersFilter.clickToDesignersButton();
        designersFilter.clickToSinitsynButton();
        filters.clickToFilterButton();
        List<String> sqlList = designersFilter.getListOfSinitsyn();
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
        designersFilter = new DesignersFilter(driver);
        filters.clickToFilterButton();
        designersFilter.clickToDesignersButton();
        designersFilter.clickToJewlryButton();
        filters.clickToFilterButton();
        List<String> sqlList = designersFilter.getListOfJewlry();
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
        designersFilter = new DesignersFilter(driver);
        filters.clickToFilterButton();
        designersFilter.clickToDesignersButton();
        designersFilter.clickToAvgvstButton();
        filters.clickToFilterButton();
        List<String> sqlList = designersFilter.getListOfAvgvst();
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
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        Thread.sleep(500);
        String countHeader = filters.getCountHeader();
        filters.clickToResetButton();
        Thread.sleep(500);
        String countHeader2 = filters.getCountHeader();
        boolean b = countHeader.equals(countHeader2);
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
        assertEquals(false, b);
    }


    //Тесты кнопок с фильтрами(шаблоны фильтров)
    @Test
    public void filterButtonEarrings() {
        earrings = new Earrings(driver);
        filters.clickToEarringsButton();
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
        assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void filterButtonRings() {
        rings = new Rings(driver);
        filters.clickToRingsButton();
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
        assertEquals(sqlList.get(2).substring(0, 20), siteList.get(2).substring(0, 20));
    }

    @Test
    public void filterButtonNecklaces() {
        necklaces = new Necklaces(driver);
        filters.clickToNecklacesButton();
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
        assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20));
        assertEquals(sqlList.get(2).substring(0, 20), siteList.get(2).substring(0, 20));
    }

    @Test
    public void filterButtonBracelets() {
        bracelets = new Bracelets(driver);
        filters.clickToBraceletsButton();
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
    public void filterButtonBrooches() throws InterruptedException {
        brooches = new Brooches(driver);
        filters.clickToBroochesButton();
        Thread.sleep(500);
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
        assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
