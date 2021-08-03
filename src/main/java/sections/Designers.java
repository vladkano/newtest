package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Designers {

    private WebDriver driver;
    private static DBWorker worker = new DBWorker();
    private String url = "https://poisondrop.ru/designers/";


    public Designers(WebDriver driver) {
        this.driver = driver;
    }

    By designersButton = By.xpath("//a[text()='Дизайнеры']");
    By designersFirstHref = By.xpath("//div[@class='new-designers__wrapper']/a");
    By designersSecondHref = By.xpath("//div[@class='new-designers__wrapper']/a[2]");
    By designersThirdHref = By.xpath("//div[@class='new-designers__wrapper']/a[3]");
    By firstPopularHref = By.xpath("//div[@class='popular-designers__item']/a");
    By lastPopularHref = By.xpath("//div[@class='popular-designers__item'][14]/a");
    By designer = By.xpath("//li[@class='index-designers__name']/a");
    By designersNames = By.xpath("//div[@class='catalog-card__designer']/a");
    By numberOfItem = By.xpath("//h3[@class='catalog-card__name']/a");

    //карточка товара
    By designerPhoto = By.xpath("//span[@class='picture designer-block__picture']//img");
    By designerName = By.xpath("//a[@class='designer-block__link link']");
    By designerText = By.xpath("//p[@class='designer-block__description']");
    By designerButton = By.xpath("//a[@class='button-border link']/span[@class='button__content']");


    public String getDescriptions() {
        List<WebElement> designersName = driver.findElements(designersNames);
        return designersName.get(3).getText();
    }

    public Designers clickOnItemName() {
        List<WebElement> itemsName = driver.findElements(numberOfItem);
        itemsName.get(3).click();
        return this;
    }

    public Designers clickToDesignerButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designerButton));
        return this;
    }

    public String getDesignerText() {
        return driver.findElement(designerText).getText();
    }

    public String getDesignerName() {
        return driver.findElement(designerName).getText();
    }

    public String getDesignerPhotoAlt() {
       return driver.findElement(designerPhoto).getAttribute("alt");
    }

    public String getFirstDesignerHref() {
        List<WebElement> designersList =  driver.findElements(designer);
        return designersList.get(0).getAttribute("href");
    }

    public String get10DesignerHref() {
        List<WebElement> designersList =  driver.findElements(designer);
        return designersList.get(9).getAttribute("href");
    }

    public String get20dDesignerHref() {
        List<WebElement> designersList =  driver.findElements(designer);
        return designersList.get(19).getAttribute("href");
    }

    public Designers clickToFirstDesignerLink() {
        List<WebElement> designersList =  driver.findElements(designer);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", designersList.get(0));
        return this;
    }

    public Designers clickTo10DesignerLink() {
        List<WebElement> designersList =  driver.findElements(designer);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", designersList.get(9));
        return this;
    }

    public Designers clickTo20DesignerLink() {
        List<WebElement> designersList =  driver.findElements(designer);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", designersList.get(19));
        return this;
    }


    public String getFirstPopularHref() {
        return driver.findElement(firstPopularHref).getAttribute("href");
    }

    public String getLastPopularHref() {
        return driver.findElement(lastPopularHref).getAttribute("href");
    }

    public Designers clickToDesignersButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designersButton));
        return this;
    }

    public String getDesignersFirstHref() {
        return driver.findElement(designersFirstHref).getAttribute("href");
    }

    public String getDesignersSecondHref() {
        return driver.findElement(designersSecondHref).getAttribute("href");
    }

    public String getDesignersThirdHref() {
        return driver.findElement(designersThirdHref).getAttribute("href");
    }


    public Designers clickToFirstDesignerHref() {
        driver.get(url + getUrls().get(0) + "/");
        return this;
    }

    public Designers clickToSecondDesignerHref() {
        driver.get(url + getUrls().get(1) + "/");
        return this;
    }

    public Designers clickToThirdDesignerHref() {
        driver.get(url + getUrls().get(2) + "/");
        return this;
    }

    public Designers clickToFirstPopularHref() {
        driver.get(url + getPopularUrls().get(0) + "/");
        return this;
    }

    public Designers clickToSecondPopularHref() {
        driver.get(url + getPopularUrls().get(getPopularUrls().size()-1) + "/");
        return this;
    }

    public String getDesignerDescription(String text) {
        String description = null;
        String query = "select description from designer " +
                "where `show` = 1 and name = " + "'" + text + "'" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                description = resultSet.getString("description");
//                System.out.println(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }

    public List<String> getFirstLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getUrls().get(0) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> getSecondLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getUrls().get(1) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getThirdLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getUrls().get(2) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item.id, item.name, designer.id, designer.name, catalog.id, catalog.name, catalog.url " +
                "order by item_catalog_position.position ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }


    public static List<String> getListOfDesigners() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "select designer.name from designer " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 " +
                "and item_sku.url is not null and balance > 0 and designer.show = 1 " +
                "group by designer.name" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(url);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static List<String> getUrls() {
        String url;
        List<String> text = new ArrayList<>();
        String query = "select url from designer " +
                "where `show` = 1 " +
                "group by id desc limit 3";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                url = resultSet.getString("url");
//                System.out.println(url);
                text.add(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static List<String> getPopularUrls() {
        String url;
        List<String> text = new ArrayList<>();
        String query = "select url from designer " +
                "where `show` = 1 and is_popular = 1 " +
                "group by id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                url = resultSet.getString("url");
//                System.out.println(url);
                text.add(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }



    public static List<String> getDesignersUrls() {
        String url;
        List<String> text = new ArrayList<>();
        String query = "select designer.url from designer " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 " +
                "and item_sku.url is not null and balance > 0 and designer.show = 1 " +
                "group by designer.name" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                url = resultSet.getString("url");
                text.add(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(text.get(0));
        return text;
    }

    public List<String> getFirstDesignerNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getDesignersUrls().get(0) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item.id, item.name, designer.id, designer.name, catalog.id, catalog.name, catalog.url " +
                "order by item_catalog_position.position ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> get10DesignerItemNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getDesignersUrls().get(9) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item.id, item.name, designer.id, designer.name, catalog.id, catalog.name, catalog.url " +
                "order by item_catalog_position.position ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> get20DesignerItemNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getDesignersUrls().get(19) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item.id, item.name, designer.id, designer.name, catalog.id, catalog.name, catalog.url " +
                "order by item_catalog_position.position ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> getFirstPopularLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getPopularUrls().get(0) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> getLastPopularLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'designer' and subsection = " + "'" + getPopularUrls().get(getPopularUrls().size()-1) + "'" +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public static void main(String[] args) {
        String description = "";
        String query = "select description from designer " +
                "where `show` = 1 and name = " + "'" + getPopularUrls().get(getPopularUrls().size()-1) + "'" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                description = resultSet.getString("description");
                System.out.println(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

    }
}
