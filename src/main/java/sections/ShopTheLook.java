package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ShopTheLook extends Base {

    By shopTheLookButton = By.xpath("//a[text()='Shop the look']");
    By shopTheLookHeader = By.xpath("//div[@class='frisbuy-title']");
    By shopTheLookPhoto = By.xpath("//li/div[@class='frisbuy-post-image']");
    By frisbuyMarker = By.xpath("//div[@class='frisbuy-marker']/span");

    public ShopTheLook(WebDriver driver) {
        super(driver);
    }

    public ShopTheLook clickToShopTheLookButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(shopTheLookButton));
        return this;
    }

    public String getShopTheLookHeader() {
        return driver.findElement(shopTheLookHeader).getText();
    }

    public ShopTheLook clickOnShopTheLookPhoto() {
        driver.findElement(shopTheLookPhoto).click();
        return this;
    }

    public String getFrisbuyMarker() {
        return driver.findElement(frisbuyMarker).getText();
    }
}
