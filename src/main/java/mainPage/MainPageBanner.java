package mainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainPageBanner {

    private WebDriver driver;
    private DBWorker worker = new DBWorker();


    public MainPageBanner(WebDriver driver) {
        this.driver = driver;
    }

    By carouselButton = By.xpath("//button[@aria-label='Carousel Page 2']");
    By designerButton = By.xpath("//*[@id='tns1-item5']//span");
    By mainCatalogHref = By.xpath("//div[@class='banner main-banner']//a[@class ='banner__link']");
    By catalogHref = By.xpath("//div[@class='banner']//a[@aria-labelledby='banner-1']");
    By sixCatalogHref = By.xpath("//div[@class='banner']//a[@aria-labelledby='banner-6']");
    By bestsellerNameButton = By.xpath("//*[@id='tns1-item1']//span");

    By designerButtonHeader = By.xpath("//*[@id='tns1-item5']//div[@class='catalog-card__designer']/a");
    By nameButtonHeader = By.xpath("//*[@id='tns1-item1']//h3[@class='catalog-card__name']/a");
    By designerHeader = By.xpath("//b[@class='product-main-info__designer-name']");
    By mainCatalogHeader = By.xpath("//li[@class='main-menu__list-item']/a[text()='Все украшения']");
    By bestsellerNameHeader = By.xpath("//h1[@class='product-main-info__product-name']");

    //находит элемент на странице перекрытый другими элементами и кликает на него
    public MainPageBanner clickToCarouselButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(carouselButton));
        return this;
    }

    public MainPageBanner clickToDesignerButton() {
        driver.findElement(designerButton).click();
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
        return driver.findElement(catalogHref).getAttribute("href");
    }

    public MainPageBanner clickToCatalogHref() {
        driver.findElement(catalogHref).click();
        return this;
    }

    public String getSixCatalogHref() {
        return driver.findElement(sixCatalogHref).getAttribute("href");
    }

    public MainPageBanner clickToSixCatalogHref() {
        driver.findElement(sixCatalogHref).click();
        return this;
    }

    public MainPageBanner clickToBestsellerNameButton() {
        driver.findElement(bestsellerNameButton).click();
        return this;
    }

    public String getBestsellerNameHeader() {
        return driver.findElement(bestsellerNameHeader).getAttribute("textContent");
    }


    //SQL
    public List<String> listOfBanners() {
        String bannerName;
        List<String> listBanners = new ArrayList<>();
        String query = "SELECT  name from main_page_blocks "
                + "where `show` = 1 " +
                "group by position " +
                "LIMIT 12";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                bannerName = resultSet.getString("name");
                listBanners.add(bannerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //worker.getSession().disconnect();
        return listBanners;
    }


    public List<String> listOfBests() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT name from item_sku " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "group by position " +
                "LIMIT 12";
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
        //worker.getSession().disconnect();
        return list;
    }

    public List<String> listOfDesigners() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT designer.name from designer " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "group by position " + "LIMIT 12";
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
        //worker.getSession().disconnect();
        return list;
    }


    public List<Integer> listPriceOfBests() {
        Integer price;
        List<Integer> list = new ArrayList<>();

        String query = "SELECT price from item_sku " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "group by position " +
                "LIMIT 12";
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
        //worker.getSession().disconnect();

        return list;
    }


    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        DBWorker worker = new DBWorker();
        String name;
//        Integer price;

        List<String> list = new ArrayList<>();
        String query = "SELECT designer.name from designer " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "group by position " + "LIMIT 5";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
//                price = resultSet.getInt("price");
                list.add(name);

                System.out.println(name);
//                System.out.println(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(list);
        //worker.getSession().disconnect();
    }
}
