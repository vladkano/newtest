package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Wishlist extends Base {

    private final By wishListButton = By.xpath("//a[@href='/wishlist/']");
    private final By wishListInCardListButton = By.xpath("//span[text()='Wish List']");
    private final By addToFavoritesFromCatalogButton = By.xpath("//span[@class='wish-button__icon-block']");
    private final By transferToBasketButton = By.xpath("//span[text()='В корзину']");
    private final By moveToBasketButton = By.xpath("//span[text()='Оформить заказ']");
    private final By wishListHeader = By.xpath("//h2[text()='Избранное']");
    private final By basketProductName = By.xpath("//h4[@class='cart-item__product-name']");
    private final By productNameFromTheCard = By.xpath("//h4[@class='cart-item__product-name']");

    public Wishlist(WebDriver driver) {
        super(driver);
    }

    public String getBasketProductName() {
        return driver.findElement(basketProductName).getText();
    }

    public Wishlist clickToMoveToBasketButton() {
        driver.findElement(moveToBasketButton).click();
        return this;
    }

    public Wishlist clickToTransferToBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(transferToBasketButton));
        return this;
    }

    public String getItemName() {
        return driver.findElement(nameLink).getText();
    }

    public Wishlist clickToAddToWishlistFromCatalogButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(addToFavoritesFromCatalogButton));
        return this;
    }

    public Wishlist clickToWishListButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(wishListButton));
        return this;
    }

    public Wishlist clickToWishListInCardListButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(wishListInCardListButton));
        return this;
    }

    public String getWishListHeader() {
        return driver.findElement(wishListHeader).getText();
    }

    public Wishlist clickToItemButton() {
        String firstItem = this.findFirstItem();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")));
        return this;
    }

    //SQL
    public static String findFirstItem() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
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
//        System.out.println(list.get(1));
        return list.get(0);
    }

    public static void main(String[] args) {
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
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
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
//        System.out.println(list.get(1));
        worker.getSession().disconnect();

    }
}
