package functionalTests;

import baseForTests.TestBase;
import filters.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FiltersTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl + "catalog/");
        filters = new Filters(driver);
        filters.clickToOkButton();
    }

    public void getProductsListFromPage() {
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
    }

    //Проверяем работу фильтров
    //ТИП ИЗДЕЛИЯ
    @Test
    public void typeOfItemEarrings() {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getEarringNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(5), siteList.get(5)));
    }

    @Test
    public void typeOfItemRings() {
        filters.clickToFilterButton();
        filters.clickToAllRingsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getRingNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(10), siteList.get(10)));
    }

    @Test
    public void typeOfItemNecklaces() {
        filters.clickToFilterButton();
        filters.clickToAllNecklacesButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getNecklacesNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(8), siteList.get(8)));
    }

    @Test
    public void typeOfItemBracelets() {
        filters.clickToFilterButton();
        filters.clickToAllBraceletsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getBraceletsNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    @Test
    public void typeOfItemBrooches() {
        filters.clickToFilterButton();
        filters.clickToAllBroochesButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getBroochesNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    //ВСТАВКИ
    public void getFilterByInserts() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToInsertButton();
    }

    @Test
    public void getPearl() {
        getFilterByInserts();
        material.clickToZemcugButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfZemcug();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    @Test
    public void getCrystals() {
        getFilterByInserts();
        material.clickToKristallyButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfKristally();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    @Test
    public void getStones() {
        getFilterByInserts();
        material.clickToKamenButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfKamen();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2).substring(0,20), siteList.get(2).substring(0,20)));
    }

    @Test
    public void getGlass() {
        getFilterByInserts();
        material.clickToStekloButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfSteklo();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    //МАТЕРИАЛ
    public void getFilterByMaterials() {
        material = new Material(driver);
        filters.clickToFilterButton();
        material.clickToMaterialButton();
    }


    @Test
    public void getBronze() {
        getFilterByMaterials();
        material.clickToBronzeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfBronze();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    @Test
    public void getSilver() {
        getFilterByMaterials();
        material.clickToSilverButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfSilver();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }


    //Тест слайдера с ценой
    @Test
    public void rightBarWithPrice() {
        filters.clickToFilterButton();
        String firstPriceRangeRight = filters.getPriceRangeRight();
        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slider-dot']")));
        WebElement priceBarRight = driver.findElement(By.xpath("(//div[@class='slider-dot'])[2]"));
        Actions actionsPriceBarRight = act.dragAndDropBy(priceBarRight, -263, 0);
        actionsPriceBarRight.build().perform();
        sleep(1000);
        String secondPriceRangeRight = filters.getPriceRangeRight();
        assertNotEquals(firstPriceRangeRight, secondPriceRangeRight);
    }


    @Test
    public void leftBarWithPrice() {
        filters.clickToFilterButton();
        String firstPriceRangeLeft = filters.getPriceRangeLeft();
        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slider-dot']")));
        WebElement priceBarLeft = driver.findElement(By.xpath("//div[@class='slider-dot']"));
        Actions actionsPriceBarLeft = act.dragAndDropBy(priceBarLeft, 100, 0);
        actionsPriceBarLeft.build().perform();
        sleep(1000);
        String secondPriceRangeLeft = filters.getPriceRangeLeft();
        assertNotEquals(firstPriceRangeLeft, secondPriceRangeLeft);
    }


    //Цвета
    public void getColorFilter() {
        colors = new Colors(driver);
        filters.clickToFilterButton();
        colors.clickToColorButton();
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
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
        //сравниваем размеры и содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size())));
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
        //сравниваем размеры и содержание списков
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size())));
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
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }


    //Проверка кнопки "Сбросить всё" фильтр
    //Внутри фильтра
    @Test
    public void resetFilter() {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        String countHeader = filters.getCountHeader();
        filters.clickToResetButtonInFilter();
        String countHeader2 = filters.getCountHeader();
        assertNotEquals(countHeader, countHeader2);
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
        assertNotEquals(countHeader, countHeader2);
    }


    /*
    Сброс фильтров по категориям(Последовательно выбираем фильтры: Серьги, Ювелирный сплав, белый).
    Затем сбрасываем их в обратной последовательности.
    Смотрим, чтобы менялось кол-во
     */

    @Test
    public void resetFiltersByCategory()  {
        material = new Material(driver);
        colors = new Colors(driver);
        filters.clickToFilterButton();
        sleep(2000);
        String numberOfProducts1 = filters.getNumberOfProducts();
        filters.clickToAllEarringsButton();
        sleep(2000);
        String numberOfProducts2 = filters.getNumberOfProducts();
        material.clickToMaterialButton();
        sleep(2000);
        material.clickToJewelryAlloyButton();
        sleep(2000);
        String numberOfProducts3 = filters.getNumberOfProducts();
        colors.clickToColorButton();
        sleep(2000);
        colors.clickToWhiteButton();
        sleep(2000);
        String numberOfProducts4 = filters.getNumberOfProducts();
        colors.clickToWhiteButton();
        sleep(2000);
        String numberOfProducts5 = filters.getNumberOfProducts();
        material.clickToJewelryAlloyButton();
        sleep(2000);
        String numberOfProducts6 = filters.getNumberOfProducts();
        filters.clickToAllEarringsButton2();
        sleep(2000);
        String numberOfProducts7 = filters.getNumberOfProducts();
        Assertions.assertAll(
                () -> assertNotEquals(numberOfProducts1, numberOfProducts2),
                () -> assertNotEquals(numberOfProducts3, numberOfProducts4),
                () -> assertEquals(numberOfProducts5, numberOfProducts3),
                () -> assertEquals(numberOfProducts6, numberOfProducts2),
                () -> assertEquals(numberOfProducts7, numberOfProducts1));
    }


    //Фильтры со скидками

    /*
    /Проверка фильтра со скидками(переключение между фильтрами: Все скидки, от 10, 30, 50 и 70%)
    Смотрим чтобы менялось кол-во
     */

    @Test
    public void discountsFilter() {
        filters.clickToFilterButton();
        String numberOfProducts1 = filters.getNumberOfProducts();
        filters.clickToAllDiscountsButton();
        sleep(2000);
        String numberOfProducts2 = filters.getNumberOfProducts();
        filters.clickToTenPercentButton();
        sleep(2000);
        String numberOfProducts3 = filters.getNumberOfProducts();
        filters.clickToThirtyPercentButton();
        sleep(2000);
        String numberOfProducts4 = filters.getNumberOfProducts();
        filters.clickToFiftyPercentButton();
        sleep(2000);
        String numberOfProducts5 = filters.getNumberOfProducts();

        //Скидки 70% бывают не всегда
//        filters.clickToSeventyPercentButton();
//        Thread.sleep(2000);
//        String numberOfProducts6 = filters.getNumberOfProducts();
        Assertions.assertAll(
                () -> assertNotEquals(numberOfProducts1, numberOfProducts2),
                () -> assertEquals(numberOfProducts2, numberOfProducts3),
                () -> assertNotEquals(numberOfProducts3, numberOfProducts4),
                () -> assertNotEquals(numberOfProducts4, numberOfProducts5));
//                () -> assertNotEquals(numberOfProducts5, numberOfProducts6));
    }

    //Проверяем, что выводится правильное кол-во изделий

    //Все скидки
    @Test
    public void allDiscounts() {
        filters.clickToFilterButton();
        filters.clickToAllDiscountsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getTenPercentDiscountItems();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    //Скидки 30%
    @Test
    public void thirtyPercentDiscounts() {
        filters.clickToFilterButton();
        filters.clickToThirtyPercentButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getThirtyPercentDiscountItems();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    //Скидки 50%
    @Test
    public void fiftyPercentDiscounts() {
        filters.clickToFilterButton();
        filters.clickToFiftyPercentButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getFiftyPercentDiscountItems();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }


    //Скидки 70% бывают не всегда
//    @Test
//    public void seventyPercentDiscounts() {
//        filters.clickToFilterButton();
//        filters.clickToSeventyPercentButton();
//        filters.clickToShowProductsButton();
//        List<String> sqlList = filters.getSeventyPercentDiscountItems();
//        getProductsListFromPage();
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        Assertions.assertAll(
//                () -> assertEquals(sqlList.size(), numberOnly),
//                () -> assertEquals(sqlList.get(0), siteList.get(0)),
//                () -> assertEquals(sqlList.get(2), siteList.get(2)));
//    }


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
