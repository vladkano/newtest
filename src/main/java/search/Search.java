package search;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Search extends Base {

    By searchButton = By.xpath("//span[@aria-label='Поиск']");
    By searchText = By.xpath("//input[@aria-label='Поиск']");
    By goSearchButton = By.xpath("//button[@class='search-form__submit button-border hidden_mobile-tablet']");

    public Search(WebDriver driver) {
        super(driver);
    }


    public Search clickOnSearchButton() {
        driver.findElement(searchButton).click();
        return this;
    }

    public Search typeText(String text) {
        driver.findElement(searchText).sendKeys(text);
        return this;
    }

    public Search getSearch(String text) {
        this.clickOnSearchButton();
        this.typeText(text);
        return new Search(driver);
    }
}
