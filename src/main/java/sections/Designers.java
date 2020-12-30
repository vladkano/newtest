package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Designers {

    private WebDriver driver;

    public Designers(WebDriver driver) {
        this.driver = driver;
    }

    By designersButton = By.xpath("//a[text()='Все дизайнеры']");

    public Designers clickToDesignersButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designersButton));
        return this;
    }
}
