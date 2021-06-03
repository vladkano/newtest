import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import sql.DBWorker;

public class Search {

    private WebDriver driver;

    public Search(WebDriver driver) {
        this.driver = driver;
    }

    By searchButton = By.xpath("//span[@aria-label='Поиск']");
    By searchText = By.xpath("//input[@aria-label='Поиск']");
    By goSearchButton = By.xpath("//button[@class='search-form__submit button-border hidden_mobile-tablet']");


    public Search clickOnSearchButton() {
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", driver.findElement(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }

    public Search typeText(String text) {
        driver.findElement(searchText).sendKeys(text);
        return this;
    }

    public Search clickOnGoSearchButton() {
        driver.findElement(goSearchButton).click();
        return this;
    }

    public Search getSearch(String text) {
        this.clickOnSearchButton();
        this.typeText(text);
        return new Search(driver);
    }
}
