package basket;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket extends Base {

    private final By itemInBasketButton = By.xpath("//button[@class='button-fill product-actions__add-to-cart']");
    private final By plusBasketButton = By.xpath("//button[@class='counter__button counter__button_plus']");
    private final By minusBasketButton = By.xpath("//button[@class='counter__button counter__button_minus']");
    private final By basketButton = By.xpath("//span[text()='Перейти в корзину']");
    private final By catalogButton = By.xpath("//a[@href='/catalog/']");
    private final By newCatalogButton = By.xpath("//a[@href='/catalog/new/']");
    private final By cartCountButton = By.xpath("//a[@href='/cart/']");
    private final By setItemInBasketButton = By.xpath("(//span[text()='В корзину'])[2]");

    private final By plus2 = By.xpath("//input[@name='quantity']");
    private final By max = By.xpath("//div[@class='counter']");
//    private final By cartCount = By.xpath("//a[@href='/cart/']/span[@class='icon-with-counter__counter']");
    private final By cartCount = By.xpath("//a[@href='/cart/']/span[@class='icon-with-counter__counter _with-offset']");

    private final By inBasket = By.xpath("//span[text()='В корзине']");
    private final By noBasketHeader = By.xpath("//p[text()='Этого украшения сейчас нет в наличии']");

    public Basket(WebDriver driver) {
        super(driver);
    }


    public String getNoBasketHeader() {
        return driver.findElement(noBasketHeader).getText();
    }

    public void clickToItemButton() {
        String firstItem = findFirstItemMoreThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")));
    }

    public void clickToRingButton() {
        String firstRing = findFirstRing();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstRing + "']")));
    }

    public void clickToAnotherItemButton() {
        String anotherItem = findFirstItemLessThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + anotherItem + "']")));
    }

    public void clickToItemInBasketButton() {
        click(itemInBasketButton);
    }

    public void clickToSetItemInBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(setItemInBasketButton));
    }

    public void clickToPlusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(plusBasketButton));
//        click(plusBasketButton);
    }

    public String getBasketNumber() {
        return driver.findElement(plus2).getAttribute("value");
    }

    public void clickToMinusBasketButton() {
        click(minusBasketButton);
    }

    public Integer getDataMax() {
        return Integer.valueOf(driver.findElement(max).getAttribute("data-max"));
    }

    public void clickToBasketButton() {
        driver.findElement(basketButton).click();
    }

    public void clickToCatalogButton() {
        click(catalogButton);
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getAttribute("textContent");
    }

    public void clickToCart() {
        click(cartCountButton);
    }

    public void clickToCartFromNew() {
        click(newCatalogButton);
    }

    public String getInBasketHeader() {
        return driver.findElement(inBasket).getAttribute("textContent");
    }


    //SQL
    public static String findFirstItemMoreThan5000() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1";
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
//        System.out.println(list.get(0));
        return list.get(0);
    }

    public static String findFirstRing() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=5 and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and item_sku.url is not null and sku_characteristic_value.characteristic_value = 'Универсальный'" +
                "group by item_catalog_position.position having SUM(balance) > 1";
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
        return list.get(1);
    }

    public static String findFirstItemLessThan5000() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price < 5000 and price > 0 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1";
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
        return list.get(0);
    }


    public static Integer findFirstItemIdMoreThan5000() {
        int id;
        List<Integer> list = new ArrayList<>();
        String query = "SELECT item.id, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                list.add(id);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list.get(0));
        return list.get(0);
    }

    public Integer getBalance() {
        int id;
        Map<Integer, Integer> hashMap = new HashMap<>();
        String query = "SELECT i.id, i.name,sum(ss.balance) AS count FROM item AS i " +
                "JOIN item_sku AS si ON i.id=si.item_id " +
                "JOIN storage_stock AS ss ON ss.sku_id=si.id " +
                "GROUP BY i.id, i.name, si.id " +
                "HAVING count>1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                int summa = resultSet.getInt("count");
                hashMap.put(id, summa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer firstItem = findFirstItemIdMoreThan5000();
//        System.out.println(firstItem);
        return hashMap.get(firstItem);
    }

    //Вытаскиваем ссылки на кольца, которые входят в коллекции
    public String getFirstLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url, SUM(balance) from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                " and item_sku.url is not null and filter_id = 149" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_catalog_position.position having SUM(balance) > 1";
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
        return list.get(0);
    }

    //Вытаскиваем ссылки на браслеты, которые входят в коллекции
    public String getSecondLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url, SUM(balance) from catalog " +
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
                " and item_sku.url is not null and filter_id = 148" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_catalog_position.position having SUM(balance) > 1";
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
        return list.get(1);
    }


    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price < 5000 and price > 0 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1 ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
    }


}
