package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sql.DBWorker;

import java.util.List;

public class Base {

    protected static DBWorker worker = new DBWorker();
    protected WebDriver driver;
    protected static String getUrl = "https://poisondrop.ru/catalog/";
//    protected static String getUrl = "https://qa.poisondrop.org.ru/catalog/";
//    protected static String getUrl = "https://stalingrad.poisondrop.org.ru/catalog/";

    protected By imageLink = By.xpath("//picture/img");
    protected By secondImageLink = By.xpath("(//picture/img)[3]");
    protected By nameLink = By.xpath("//h3[@class='catalog-card__name']/a");
    protected By designerLink = By.xpath("//div[@class='catalog-card__designer']/a");

    protected By nameHeader = By.xpath("//h1[@class='product-main-info__product-name']");
    protected By designerHeader = By.xpath("//b[@class='product-main-info__designer-name']");

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public Base clickOnImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(imageLink));
        return this;
    }

    public Base clickOnSecondImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondImageLink));
        return this;
    }

    public Base clickOnNameLink() {
        List<WebElement> elements = driver.findElements(nameLink);
        elements.get(1).click();
        return this;
    }

    public Base clickOnDesignerLink() {
        List<WebElement> elements = driver.findElements(designerLink);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designerLink));
//        elements.get(2).click();
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

    public String getDesignerLinkHeader() {
        List<WebElement> elements = driver.findElements(designerLink);
        return elements.get(0).getAttribute("textContent");
    }

    public String getHeader() {
        return driver.findElement(nameHeader).getText();
    }


    public String getNextDesignerHeader() {
        return driver.findElement(designerHeader).getText();
    }


}
