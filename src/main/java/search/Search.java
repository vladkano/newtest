package search;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Search extends Base {

    private final By searchButton = By.xpath("//span[@aria-label='Поиск']");
    private final By searchText = By.xpath("//input[@aria-label='Поиск']");

    public Search(WebDriver driver) {
        super(driver);
    }


    public void clickOnSearchButton() {
        click(searchButton);
    }

    public void typeText(String text) {
        type(text, searchText);
    }

    public void getSearch(String text) {
        this.clickOnSearchButton();
        this.typeText(text);
    }
}
