import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Collection {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;

    public Collection(WebDriver driver) {
        this.driver = driver;
    }

    By firstHref = By.xpath("//a[text()='Малое серебряное кольцо-шарик с черным ситаллом, из коллекции…']");
    By secondHref = By.xpath("//a[text()='Кольцо из серебра с сапфиром, из коллекции Nebula (15)']");
    By linkPhilippeAudibert = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=a']");
    By linkLAV = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=b&cvet=krasnyi&pokrytie=serebro']");
    By linkAvgvst = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=a&pokrytie=zoloto']");
    By linkLisaSmith = By.xpath("//li/a[@href='/catalog/rings/poisontoday?bukva=b&pokrytie=serebro']");

    By linkHeader = By.xpath("//h1[text()='Малое серебряное кольцо-шарик с черным ситаллом, из коллекции Lollipops (18)']");
    By secondLinkHeader = By.xpath("//h1[text()='Кольцо из серебра с сапфиром, из коллекции Nebula (15)']");




    public Collection clickOnHref() {
        List<WebElement> castButtons = driver.findElements(By.xpath("//a[text()='Покрытое серебром кольцо Etty с лазуритом (17,5)']/following::div//li[1]/span[text()='A']"));
        castButtons.get(1).click();
        return this;
    }

    public String getFirstHref() {
        return driver.findElement(firstHref).getAttribute("href");
    }

    public Collection clickOnFirstHref() {
        driver.findElement(firstHref).click();
        return this;
    }

    public String getLinkHeader() {
        return driver.findElement(linkHeader).getAttribute("textContent");
    }


    public String getSecondHref() {
        return driver.findElement(secondHref).getAttribute("href");
    }

    public String getSecondHrefHeader() {
        return driver.findElement(secondHref).getAttribute("textContent");
    }

    public Collection clickOnSecondHref() {
        driver.findElement(secondHref).click();
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
                "where item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_sku.item_id ";
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
        worker.getSession().disconnect();

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
        worker.getSession().disconnect();

        return list;
    }


    //Вытаскиваем ссылку относящиюся к товару "Малое серебряное кольцо-шарик с черным ситаллом, из коллекции Lollipops (18) 2 хар-ки
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
        String first = list.get(0);
        String second = list.get(1);
        second = second.substring(second.indexOf('?'));
        String replStr1 = second.replace('?', '&');
        String itog = first + replStr1;

//        System.out.println(itog);
        worker.getSession().disconnect();
        return itog;
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
                " and item_collection_consist.item_collection_id != 0 and item_sku.name='Кольцо из серебра с сапфиром, из коллекции Nebula (15)'"
                + " group by item_collection_consist.id ";
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
        String first = list.get(0);
        String second = list.get(1);
        String third = list.get(2);
        second = second.substring(second.indexOf('?'));
        String replStr1 = second.replace('?', '&');
        third = third.substring(third.indexOf('?'));
        String replStr12 = third.replace('?', '&');
        String itog = first + replStr12 + replStr1;

//        System.out.println(itog);
        worker.getSession().disconnect();
        return itog;
    }

    public static void main(String[] args) {
        String name;
        String name2;
        String name3;
        String name4;
        String name5 = null;
//        Integer price;

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
                name5 = resultSet.getString("item_sku.name");
//                price = resultSet.getInt("price");


                String a = "http://176.53.182.129:8088/catalog/";
                list.add(a + name + "/" + name2 + "?" + name3 + "=" + name4);
//                list.add(name2);
//                list.add(name3);
//                list.add(name4);
//                System.out.println(name5+": " + name + name2 + name3 + name4);
//                System.out.println(name5);
//                System.out.println(name2);
//                System.out.println(name3);
//                System.out.println(name4);
//                System.out.println(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(list);
        String first = list.get(0);
        String second = list.get(1);
        String third = list.get(2);
        second = second.substring(second.indexOf('?'));
        String replStr1 = second.replace('?', '&');
        third = third.substring(third.indexOf('?'));
        String replStr12 = third.replace('?', '&');
        String itog = first + replStr12 + replStr1;

        System.out.println(itog);
        worker.getSession().disconnect();
    }
}
