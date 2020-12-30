import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Collection {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;
    //private String getUrl = "http://176.53.182.129:8088/catalog/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/";
    private static String getUrl = "https://poisondrop.ru/catalog/";


    public Collection(WebDriver driver) {
        this.driver = driver;
    }

    By secondHref = By.xpath("//a[text()='Кольцо из серебра с сапфиром, из коллекции Nebula (15)']");
    By linkPhilippeAudibert = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=a']");
    By linkLAV = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=b&cvet=krasnyi&pokrytie=serebro']");
    By linkAvgvst = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=a&pokrytie=zoloto']");
    By linkLisaSmith = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=b&pokrytie=serebro']");
    By href = By.xpath("//div[@class='catalog-card catalog__card']/a");

    By linkHeader = By.xpath("//h1[@class='product-main-info__product-name']");
    By secondLinkHeader = By.xpath("//h1[text()='Кольцо из серебра с сапфиром, из коллекции Nebula (15)']");




    public String getHref() {
        List<WebElement> castButtons = driver.findElements(href);
        return castButtons.get(0).getAttribute("href");
    }


    public Collection clickOnFirstHref() {
        List<WebElement> castButtons = driver.findElements(href);
        castButtons.get(0).click();
        return this;
    }

    public String getSecondHref() {
        List<WebElement> castButtons = driver.findElements(href);
        return castButtons.get(1).getAttribute("href");
    }


    public Collection clickOnSecondHref() {
        List<WebElement> castButtons = driver.findElements(href);
        castButtons.get(1).click();
        return this;
    }

    public String getSecondLinkHeader() {
        return driver.findElement(secondLinkHeader).getAttribute("textContent");
    }


    public String getLinkLinkPhilippeAudibertText() {
        return driver.findElement(linkPhilippeAudibert).getAttribute("href");
    }

    public Collection clickOnLinkPhilippeAudibert() {
        driver.findElement(linkPhilippeAudibert).click();
        return this;
    }

    public String getLinkLAVText() {
        return driver.findElement(linkLAV).getAttribute("href");
    }

    public Collection clickOnLinkLAV() {
        driver.findElement(linkLAV).click();
        return this;
    }

    public String getLinkAvgvstText() {
        return driver.findElement(linkAvgvst).getAttribute("href");
    }

    public Collection clickOnAvgvst() {
        driver.findElement(linkAvgvst).click();
        return this;
    }

    public String getLinkLisaSmith() {
        return driver.findElement(linkLisaSmith).getAttribute("href");
    }

    public Collection clickOnLisaSmith() {
        driver.findElement(linkLisaSmith).click();
        return this;
    }

    //Тесты запросов к базе SQL

    //Вытаскиваем все товары которые входят в коллекции
    public List<String> getNamesItems() {
        DBWorker worker = new DBWorker();

        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0 " +
                "and item_collection_consist.item_collection_id != 0 " +
                "and EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 " +
                "and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0 " +
                "group by item_sku.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);

//                System.out.println(name);
//                System.out.println(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(list);
//        //worker.getSession().disconnect();

        return list;
    }


    //Вытаскиваем коллекции относящиеся к товару  Малое серебряное кольцо-шарик с черным ситаллом, из коллекции Lollipops (18)

    public List<String> getFirstCollection() {
        DBWorker worker = new DBWorker();
        String name;
        String name2;
        String name3;
        String name4;

        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0 and item_sku.name='Малое серебряное кольцо-шарик с черным ситаллом, из коллекции Lollipops (18)'"
                +
                " group by item_collection_consist.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection.url");
                name3 = resultSet.getString("item_collection_characteristic.url");
                name4 = resultSet.getString("item_collection_characteristic_value.url");

                String a = "http://176.53.182.129:8088/catalog/";
                list.add(a + name + "/" + name2 + "?" + name3 + "=" + name4);
//                System.out.println(name + name2 + name3 + name4);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(list);
        //worker.getSession().disconnect();

        return list;
    }


    //Вытаскиваем ссылку
    public String getFirstLink() {
        DBWorker worker = new DBWorker();
        String name;
        String name2;
        String name3;
        String name4;

        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0"
                +
                " group by item_collection_consist.id ";
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

    //Вытаскиваем ссылку относящиюся к товару "Кольцо из серебра с сапфиром, из коллекции Nebula (15) 3 хар-ки
    public String getSecondLink() {
        DBWorker worker = new DBWorker();
        String name;
        String name2;
        String name3;
        String name4;

        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0"
                +
                " group by item_sku.id ";
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
//        second = second.substring(second.indexOf('?'));
//        String replStr1 = second.replace('?', '&');
//        String itog = first + replStr1;

//        System.out.println(itog);
        //worker.getSession().disconnect();
        return list.get(1);
    }

    public static void main(String[] args) {
        DBWorker worker = new DBWorker();

        String name;
        String name2;
        String name3;
        String name4;

        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0"
                +
                " group by item_sku.id ";
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
        System.out.println(first);
        String second = list.get(1);
        System.out.println(second);
        second = second.substring(second.indexOf('?'));
        String replStr1 = second.replace('?', '&');
        String itog = first + replStr1;

    }
}
