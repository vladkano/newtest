package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import filters.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        driver.manage().window().setSize(new Dimension(1920, 1080));
        filters = new Filters(driver);
        basket = new Basket(driver);
        basket.clickToOkButton();
    }

    //Проверяем работу фильтров

    //ТИП ИЗДЕЛИЯ
    @Test
    public void typeOfItemEarrings() {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getEarringNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(1), siteList.get(1));
    }

    @Test
    public void typeOfItemRings() {
        filters.clickToFilterButton();
        filters.clickToAllRingsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getRingNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(10), siteList.get(10));
    }

    @Test
    public void typeOfItemNecklaces() {
        filters.clickToFilterButton();
        filters.clickToAllNecklacesButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getNecklacesNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0).substring(0, 28), siteList.get(0).substring(0, 28));
        assertEquals(sqlList.get(8).substring(0, 23), siteList.get(8).substring(0, 23));
    }

    @Test
    public void typeOfItemBracelets() {
        filters.clickToFilterButton();
        filters.clickToAllBraceletsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getBraceletsNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void typeOfItemBrooches() {
        filters.clickToFilterButton();
        filters.clickToAllBroochesButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getBroochesNamesForFilters();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //ВСТАВКИ
    @Test
    public void getPearl() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToInsertButton();
        material.clickToZemcugButton();
        filters.clickToShowProductsButton();
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
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getCrystals() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToInsertButton();
        material.clickToKristallyButton();
        filters.clickToShowProductsButton();
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
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(1), siteList.get(1));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getStones() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToInsertButton();
        material.clickToKamenButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfKamen();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getGlass() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToInsertButton();
        material.clickToStekloButton();
        filters.clickToShowProductsButton();
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
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //МАТЕРИАЛ
    @Test
    public void getBronze() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
        material.clickToBronzeButton();
        filters.clickToShowProductsButton();
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
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
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
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfSilver();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
        }
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlSize, numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }


    //Тест слайдера с ценой
    @Test
    public void price() {
        filters.clickToFilterButton();
        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slider-dot']")));
        WebElement priceBar = driver.findElement(By.xpath("//div[@class='slider-dot']"));
        Actions actions = act.dragAndDropBy(priceBar, 100, 0);
        actions.build().perform();
    }


    //Цвета

    public void getColorFilter() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
    }

    public void getProductsListFromPage() {
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
    }

    @Test
    public void getGreen() {
        getColorFilter();
        colors.clickToGreenButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colors.getListOfGreenColor();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0).substring(0, 9), siteList.get(0).substring(0, 9));
        assertEquals(sqlList.get(2).substring(0, 22), siteList.get(2).substring(0, 22));
    }

    @Test
    public void getBlue() {
        getColorFilter();
        colors.clickToBlueButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colors.getListOfBlueColor();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getMix() {
        getColorFilter();
        colors.clickToMixButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colors.getListOfMixColor();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //ПОКРЫТИЕ

    public void getCoveringFilter() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToCoveringButton();
    }

    @Test
    public void getRhodium() {
        getCoveringFilter();
        colors.clickToRodiiButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colors.getListOfRodii();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getPinkGold() {
        getCoveringFilter();
        colors.clickToPinkGoldButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colors.getListOfPinkGold();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    //Размер кольца

    public void getSizeFilter() {
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToSizeButton();
    }

    @Test
    public void getFirstSize() {
        getSizeFilter();
        size.clickToFirstSizeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = size.getListOfFirstSize();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getSecondSize() {
        getSizeFilter();
        size.clickToSecondSizeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = size.getListOfSecondSize();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getUniversalSize() {
        getSizeFilter();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = size.getListOfUniversalSize();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }


    //Дизайнеры

    public void getDesignersFilter() {
        designersFilter = new DesignersFilter(driver);
        filters.clickToFilterButton();
        designersFilter.clickToDesignersButton();
    }

    @Test
    public void getSinitsyn() {
        getDesignersFilter();
        designersFilter.clickToSinitsynButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = designersFilter.getListOfSinitsyn();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getProstoJewlry() {
        getDesignersFilter();
        designersFilter.clickToJewlryButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = designersFilter.getListOfJewlry();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }

    @Test
    public void getAvgvst() {
        getDesignersFilter();
        designersFilter.clickToAvgvstButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = designersFilter.getListOfAvgvst();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        assertEquals(sqlList.size(), numberOnly);
        assertEquals(sqlList.get(0), siteList.get(0));
        assertEquals(sqlList.get(2), siteList.get(2));
    }


    //Проверка кнопки "Сбросить всё" фильтр
    //Внутри фильтра
    @Test
    public void resetFilter() throws InterruptedException {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        String countHeader = filters.getCountHeader();
        filters.clickToResetButtonInFilter();
        String countHeader2 = filters.getCountHeader();
        assertFalse(countHeader.equals(countHeader2));
    }

    //Выйдя из фильтра
    @Test
    public void resetFilterExit() {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToShowProductsButton();
        String countHeader = filters.getCountHeader();
        filters.clickToCatalogResetButton();
        String countHeader2 = filters.getCountHeader();
        assertFalse(countHeader.equals(countHeader2));
    }


    //Тесты кнопок с фильтрами(шаблоны фильтров) временно отключен, будет что-то новое(Юра Пронин)
//    @Test
//    public void filterButtonEarrings() {
//        earrings = new Earrings(driver);
//        filters.clickToEarringsButton();
//        List<String> sqlList = earrings.getNamesForFilters();
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
//        assertEquals(sqlList.get(1), siteList.get(1));
//    }
//
//    @Test
//    public void filterButtonRings() {
//        rings = new Rings(driver);
//        filters.clickToRingsButton();
//        List<String> sqlList = rings.getNamesForFilters();
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
//        assertEquals(sqlList.get(2).substring(0, 20), siteList.get(2).substring(0, 20));
//    }
//
//    @Test
//    public void filterButtonNecklaces() {
//        necklaces = new Necklaces(driver);
//        filters.clickToNecklacesButton();
//        List<String> sqlList = necklaces.getNamesForFilters();
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
//        assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20));
//        assertEquals(sqlList.get(2).substring(0, 20), siteList.get(2).substring(0, 20));
//    }
//
//    @Test
//    public void filterButtonBracelets() {
//        bracelets = new Bracelets(driver);
//        filters.clickToBraceletsButton();
//        List<String> sqlList = bracelets.getNamesForFilters();
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
//    public void filterButtonBrooches() throws InterruptedException {
//        brooches = new Brooches(driver);
//        filters.clickToBroochesButton();
//        Thread.sleep(500);
//        List<String> sqlList = brooches.getNamesForFilters();
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
//        assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20));
//        assertEquals(sqlList.get(2), siteList.get(2));
//    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
