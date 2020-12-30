package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Sale {

    private WebDriver driver;

    public Sale(WebDriver driver) {
        this.driver = driver;
    }

    By saleButton = By.xpath("//a[text()='Sale']");

    public Sale clickToSaleButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(saleButton));
        return this;
    }
}
