package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Shops {

    private WebDriver driver;

    public Shops(WebDriver driver) {
        this.driver = driver;
    }

    By shopsButton = By.xpath("//span[text()='Магазины']");
    By shopsHeader = By.xpath("//h1[text()='Магазины']");




    public Shops clickToShopsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(shopsButton));
        return this;
    }

    public String getShopsHeader() {
        return driver.findElement(shopsHeader).getText();
    }
}
