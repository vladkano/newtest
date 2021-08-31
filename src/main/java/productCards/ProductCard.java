package productCards;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCard extends Base {

    By structureButton = By.xpath("//div[@class='accordion']");
    By specification = By.xpath("//p[@class='product-info__specification']");

    By availabilityButton = By.xpath("//h3[text()='Наличие в магазинах']");
    By tsvetnoy = By.xpath("//span[text()='Цветной']");
    By metropolis = By.xpath("//span[text()='Метрополис']");
    By afimoll = By.xpath("//span[text()='Афимолл']");
    By atrium = By.xpath("//span[text()='Атриум']");
    By redBridge = By.xpath("//span[text()='APR Санкт-Петербург']");

    By jewelryCareButton = By.xpath("//h3[text()='Состав и характеристики']");
    By jewelryCareHeader = By.xpath("//h4[text()='Ювелирные украшения']");
    By jewelryCareText = By.xpath("//p[@class='product-info__text']");
    By bijouterieCareHeader = By.xpath("//h4[text()='Бижутерия']");
    By bijouterieCareText = By.xpath("//p[@class='product-info__text'][2]");

    By deliveryButton = By.xpath("//h3[text()='Доставка, оплата, возврат']");
    By deliveryText = By.xpath("//p[text()='Доставка по Москве на следующий день, кроме воскресенья. ']");

    By guaranteeButton = By.xpath("//h3[text()='Гарантия 6 месяцев']");
    By guaranteeText = By.xpath("//div[@id='warranty-accordion']/p[@class='product-info__text']");
    By code = By.xpath("//div[@class='product-info__code']");

    By recentlyViewedProductsHeader = By.xpath("//h2[text()='Вы смотрели']");
    By designerNameFromRecentlyViewedProducts = By.xpath("//div[@class='catalog-card__designer']/a");
    By priceFromRecentlyViewedProducts = By.xpath("(//b[@class='price-block__price'])[2]");


    public ProductCard(WebDriver driver) {
        super(driver);
    }


    public String getPriceFromRecentlyViewedProducts() {
        return driver.findElement(priceFromRecentlyViewedProducts).getText();
    }

    public String getDesignerNameFromRecentlyViewedProducts() {
        return driver.findElement(designerNameFromRecentlyViewedProducts).getText();
    }

    public String getRecentlyViewedProductsHeader() {
        return driver.findElement(recentlyViewedProductsHeader).getAttribute("textContent");
    }

    public ProductCard clickToStructureButton() {
        driver.findElement(structureButton).click();
        return this;
    }

    public String getSpecification() {
        return driver.findElement(specification).getAttribute("textContent");
    }

    public ProductCard clickToAvailabilityButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(availabilityButton));
        return this;
    }

    public String getTsvetnoy() {
        return driver.findElement(tsvetnoy).getAttribute("textContent");
    }

    public String getMetropolis() {
        return driver.findElement(metropolis).getAttribute("textContent");
    }

    public String getAfimoll() {
        return driver.findElement(afimoll).getAttribute("textContent");
    }

    public String getAtrium() {
        return driver.findElement(atrium).getAttribute("textContent");
    }

    public String getRedBridge() {
        return driver.findElement(redBridge).getAttribute("textContent");
    }

    public ProductCard clickToJewelryCareButton() {
        driver.findElement(jewelryCareButton).click();
        return this;
    }

    public String getJewelryCareHeader() {
        return driver.findElement(jewelryCareHeader).getAttribute("textContent");
    }

    public String getJewelryCareText() {
        return driver.findElement(jewelryCareText).getAttribute("textContent");
    }

    public String getBijouterieCareHeader() {
        return driver.findElement(bijouterieCareHeader).getAttribute("textContent");
    }

    public String getBijouterieCareText() {
        return driver.findElement(bijouterieCareText).getAttribute("textContent");
    }

    public ProductCard clickToDeliveryButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(deliveryButton));
        return this;
    }

    public String getDeliveryText() {
        return driver.findElement(deliveryText).getAttribute("textContent");
    }

    public ProductCard clickToGuaranteeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(guaranteeButton));
        return this;
    }

    public String getGuaranteeText() {
        return driver.findElement(guaranteeText).getAttribute("textContent");
    }

    public String getItemCode() {
        return driver.findElement(code).getText();
    }


    public static void main(String[] args) {
        String code;
        List<String> text = new ArrayList<>();
        String query = "SELECT code from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'sergi' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
                System.out.println(code);
                text.add(code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

    }


}
