package catalog;

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

public class Necklaces {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;
    private static String getUrl = "https://poisondrop.ru/catalog/";

    By imageLink = By.xpath("//picture/img");
    By nameLink = By.xpath("//h3[@class='catalog-card__name']/a");
    By designerLink = By.xpath("//div[@class='catalog-card__designer']/a");

    By nameHeader = By.xpath("//h1[@class='product-main-info__product-name']");
    By designerHeader = By.xpath("//b[@class='product-main-info__designer-name']");

    public Necklaces(WebDriver driver) {
        this.driver = driver;
    }

    public Necklaces clickOnImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(imageLink));
        return this;
    }

    public Necklaces clickOnNameLink() {
        List<WebElement> elements = driver.findElements(nameLink);
        elements.get(1).click();
        return this;
    }

    public Necklaces clickOnDesignerLink() {
        List<WebElement> elements = driver.findElements(designerLink);
        elements.get(2).click();
        return this;
    }

    public String getImageHeader() {
        List<WebElement> elements = driver.findElements(imageLink);
        return elements.get(0).getAttribute("alt");
    }

    public String getNameHeader() {
        List<WebElement> elements = driver.findElements(nameLink);
        return elements.get(1).getAttribute("textContent");
    }

    public String getDesignerHeader() {
        List<WebElement> elements = driver.findElements(designerLink);
        return elements.get(2).getAttribute("textContent");
    }

    public String getHeader() {
        return driver.findElement(nameHeader).getText();
    }

    public String getNextDesignerHeader() {
        return driver.findElement(designerHeader).getText();
    }

    public List<String> getNames() {
        worker = new DBWorker();
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
                "and catalog_id=2 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'kole' " +
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
        //worker.getSession().disconnect();
//        System.out.println("метод getNames: " + text);

        return text;
    }

    public List<String> getNamesForFilters() {
        worker = new DBWorker();
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
                "and catalog_id=2 and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
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
        //worker.getSession().disconnect();
//        System.out.println("метод getNames: " + text);

        return text;
    }

    public List<String> getDesigners() {
        worker = new DBWorker();
        String designer;
        List<String> text = new ArrayList<>();
        String query = "SELECT designer.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=2 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'kole' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                designer = resultSet.getString("name");
//                System.out.println(designer);
                text.add(designer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //worker.getSession().disconnect();
//        System.out.println("метод getDesigner: " + text);

        return text;
    }

    public List<Integer> getPrice() {
        worker = new DBWorker();
        int price, discount;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT item_sku.price, (price * discount/100) as discount from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=2 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'kole' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                price = resultSet.getInt("price");
                discount =  resultSet.getInt("discount");
                int priceNew = price - discount;
//                System.out.println(discount);
                text.add(priceNew);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //worker.getSession().disconnect();
//        System.out.println("метод getPrice: " + text);
        return text;
    }

    //Вытаскиваем все браслеты, которые входят в коллекции
    //Вытаскиваем ссылку
    public String getFirstLinkOfCollection() {
        DBWorker worker = new DBWorker();
        String name;
        String name2;
        String name3;
        String name4;

        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=2 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and balance > 0 and section = 'catalog' and subsection = 'kole'" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection.url");
                name3 = resultSet.getString("item_collection_characteristic.url");
                name4 = resultSet.getString("item_collection_characteristic_value.url");

                list.add(getUrl + name + "/" + name2 + "/?" + name3 + "=" + name4);
//                System.out.println(name + name2 + name3 + name4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(list);
        String first = list.get(0);
//        System.out.println(first);
//        String second = list.get(1);
//        second = second.substring(second.indexOf('?'));
//        String replStr1 = second.replace('?', '&');
//        String itog = first + replStr1;

//        System.out.println(itog);
        //worker.getSession().disconnect();
        return first;
    }



//    public static void main(String[] args) {
//
////        String query = "SELECT COUNT(*) id from item where catalog_id=2 and is_archive = 0";
//        String query = "SELECT COUNT(*)id from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "where catalog_id=2 and is_archive = 0 and price != 0 and item_sku.url is not null";
//
////
////        String query = "SELECT COUNT(*) id from item " +
////                "JOIN designer ON item.designer_id = designer.id " +
////                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_sku.item_id and price != 0 and item_sku.url is not null)" +
////                " and catalog_id=2 and is_archive = 0";
//
//        try {
//            Statement statement = worker.getCon().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                System.out.println(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        //worker.getSession().disconnect();
//    }
}
