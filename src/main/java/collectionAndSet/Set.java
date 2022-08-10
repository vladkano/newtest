package collectionAndSet;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Set extends Base {

    private final By setWindow = By.id("tns1-ow");
    private final By setHeader = By.xpath("//section[@class='products-slider product-set product-card__products-slider']/h2");
    private final By firstItemFromSet = By.xpath("//h3[@class='catalog-card__name']/a");

    public Set(WebDriver driver) {
        super(driver);
    }

    public void getSetWindow() {
        driver.findElement(setWindow).isDisplayed();
    }

    public String getSetHeader() {
        return driver.findElement(setHeader).getText();
    }

    public void clickOnFirstItemFromSet() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstItemFromSet));
//        driver.findElement(firstItemFromSet).click();
    }

    public String getHrefFirstItemFromSet() {
        return driver.findElement(firstItemFromSet).getAttribute("href");
    }

}
