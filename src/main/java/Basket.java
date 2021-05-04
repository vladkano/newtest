import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private WebDriver driver;

    public Basket(WebDriver driver) {
        this.driver = driver;
    }

    By itemInBasketButton = By.xpath("//button[@class='button-fill product-actions__add-to-cart']");
    By plusBasketButton = By.xpath("//button[@class='counter__button counter__button_plus']");
    By minusBasketButton = By.xpath("//button[@class='counter__button counter__button_minus']");
    By basketButton = By.xpath("//span[text()='Перейти в корзину']");
    By catalogButton = By.xpath("//li/a[text()='Все украшения']");
    By newCatalogButton = By.xpath("//a[@href='/catalog/new/']");
    By cartCountButton = By.xpath("//a[@href='/cart']");
    By collectionItems = By.xpath("//li[@class='product-variant']/a");
    By okButton = By.xpath("//span[text()='Да']");

    By plus2 = By.xpath("//input[@name='quantity']");
    By max = By.xpath("//div[@class='counter']");
    By cartCount = By.xpath("//div[@class='text-on-icon page-header__cart']/span");
    By inBasket = By.xpath("//span[text()='В корзине']");


    public Basket clickToOkButton() {
        driver.findElement(okButton).click();
        return this;
    }

    public Basket clickOnFirstCollection() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(collectionItems));
        List<WebElement> elements = driver.findElements(collectionItems);
        WebElement webElement = elements.get(0);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", webElement);
        return this;
    }


    public Basket clickToItemButton() {
        String firstItem = this.findFirstItemMoreThan5000();
        driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")).click();
        return this;
    }

    public Basket clickToRingButton() {
        String firstItem = this.findFirstRing();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")));
        return this;
    }

    public Basket clickToAnotherItemButton() {
        String anotherItem = this.findFirstItemLasseThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + anotherItem + "']")));
        return this;
    }

    public Basket clickToItemInBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(itemInBasketButton));
        return this;
    }

    public Basket clickToPlusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(plusBasketButton));
        return this;
    }

    public String getBasketNumber() {
        return driver.findElement(plus2).getAttribute("value");
    }

    public Basket clickToMinusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(minusBasketButton));
        return this;
    }

    public Integer getDataMax() {
        return Integer.valueOf(driver.findElement(max).getAttribute("data-max"));
    }

    public Basket clickToBasketButton() {
        driver.findElement(basketButton).click();
        return this;
    }

    public Basket clickToCatalogButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(catalogButton));
        return this;
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getAttribute("textContent");
    }

    public Basket clickToCart() {
        driver.findElement(cartCountButton).click();
        return this;
    }

    public Basket clickToCartFromNew() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(newCatalogButton));
        return this;
    }

    public String getInBasketHeader() {
        return driver.findElement(inBasket).getAttribute("textContent");
    }


    //SQL
    public static String findFirstItemMoreThan5000() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
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

    public static String findFirstRing() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null and catalog_id=5 " +
                "and item_sku.url is not null and balance > 1 " +
                "group by item_catalog_position.position" ;
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
        return list.get(3);
    }

    public static String findFirstItemLasseThan5000() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price < 5000 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
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


    public Integer getBalance() {
        DBWorker worker = new DBWorker();
        String name;
        Map<String, Integer> hashMap = new HashMap<>();
        String query = "SELECT item_sku.name, balance, reserve, sum(balance) - sum(reserve) as sum  from storage_stock " +
                "JOIN item_sku ON storage_stock.sku_id = item_sku.id " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "where balance - reserve > 0 and price > 5000 " +
                "group by item_catalog_position.position, item_sku.id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                int summa = resultSet.getInt("sum");
                hashMap.put(name, summa);
//                System.out.println(name);
//                System.out.println(itog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String firstItem = this.findFirstItemMoreThan5000();
        Integer i = hashMap.get(firstItem);
        return i;
    }

    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price < 5000 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
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

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        worker.getSession().disconnect();

    }
}
