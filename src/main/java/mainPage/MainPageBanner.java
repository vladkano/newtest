package mainPage;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainPageBanner extends Base {

    By carouselButton = By.xpath("//button[@aria-label='Carousel Page 2']");
    By designerButton = By.xpath("//*[@id='tns1-item5']//span");
    By mainCatalogHref = By.xpath("//div[@class='banner main-banner']//a[@class ='banner__link']");
    By bestsellerNameButton = By.xpath("//*[@id='tns1-item1']//span");
    By countOfBanners = By.xpath("//div[@class='banner__content']/a");

    By designerButtonHeader = By.xpath("//*[@id='tns1-item5']//div[@class='catalog-card__designer']/a");
    By nameButtonHeader = By.xpath("//*[@id='tns1-item1']//h3[@class='catalog-card__name']/a");
    By designerHeader = By.xpath("//b[@class='product-main-info__designer-name']");
    By mainCatalogHeader = By.xpath("//span[text()='Фильтры']");
    By bestsellerNameHeader = By.xpath("//h1[@class='product-main-info__product-name']");

    public MainPageBanner(WebDriver driver) {
        super(driver);
    }

    //находит элемент на странице перекрытый другими элементами и кликает на него
    public MainPageBanner clickToCarouselButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(carouselButton));
        return this;
    }

    public MainPageBanner clickToDesignerButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designerButton));
        return this;
    }

    public String getDesignerName() {
        return driver.findElement(designerButtonHeader).getAttribute("textContent");
    }

    public String getName() {
        return driver.findElement(nameButtonHeader).getAttribute("textContent");
    }

    public String getDesignerHeader() {
        return driver.findElement(designerHeader).getText();
    }

    public String getMainCatalogHref() {
        return driver.findElement(mainCatalogHref).getAttribute("href");
    }

    public MainPageBanner clickToMainCatalogHref() {
        driver.findElement(mainCatalogHref).click();
        return this;
    }

    public String getMainCatalogHeader() {
        return driver.findElement(mainCatalogHeader).getAttribute("textContent");
    }


    public String getCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement first = banners.get(0);
        return first.getAttribute("href");
    }

    public MainPageBanner clickToCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement first = banners.get(0);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", first);
        return this;

    }

    public String getSixCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement six = banners.get(5);
        return six.getAttribute("href");
    }

    public MainPageBanner clickToSixCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement six = banners.get(5);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", six);
        return this;
    }

    public MainPageBanner clickToBestsellerNameButton() {
//        driver.findElement(bestsellerNameButton).click();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(bestsellerNameButton));
        return this;
    }

    public String getBestsellerNameHeader() {
        return driver.findElement(bestsellerNameHeader).getAttribute("textContent");
    }


    //SQL
    public Integer listOfBanners() {
        Integer count = 0;
        String query = "SELECT count(url) as countURL from main_page_blocks " +
                "where `show` = 1 " +
                "LIMIT 12";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt("countURL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(count);
        return count;
    }


    public List<String> listOfBests() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT name from item_sku " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "group by bestsellers.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);

//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list);
        return list;
    }

    public List<String> listOfDesigners() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT designer.name from designer " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "group by bestsellers.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list);
        return list;
    }


    public List<Integer> listPriceOfBests() {
        Integer price;
        List<Integer> list = new ArrayList<>();

        String query = "SELECT price from item_sku " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "group by bestsellers.id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                price = resultSet.getInt("price");
                list.add(price);
//                System.out.println(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list);
        return list;
    }


    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        Integer count = 0;
        String query = "SELECT count(url) as countURL from main_page_blocks " +
                "where `show` = 1 " +
                "LIMIT 12";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt("countURL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        worker.getSession().disconnect();
    }
}
