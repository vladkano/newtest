package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Footer extends Base {

    private final By aboutButton = By.xpath("//a[text()='О нас']");
    private final By contactsButton = By.xpath("//a[text()='Контакты']");
    private final By shopsButton = By.xpath("//a[text()='Адреса магазинов']");
    private final By vacancyButton = By.xpath("//a[text()='Вакансии']");
    private final By soglashenieButton = By.xpath("//a[text()='Пользовательское соглашение']");
    private final By dostavkaButton = By.xpath("//a[text()='Доставка и оплата']");
    private final By obmenButton = By.xpath("//a[text()='Обмен и возврат']");
    private final By garantiiButton = By.xpath("//a[text()='Гарантии']");
    private final By ofertaButton = By.xpath("//a[text()='Оферта']");
    private final By personalnyeDannyeButton = By.xpath("//a[text()='Персональные данные']");
    private final By instaButton = By.xpath("//a[@aria-label='instagram']");
    private final By telegaButton = By.xpath("//a[@aria-label='telegram']");
    private final By tikTokButton = By.xpath("//a[@aria-label='tik-tok']");
    private final By youtubeButton = By.xpath("//a[@aria-label='youtube']");
    private final By whatsAppButton = By.xpath("//a[@aria-label='whatsApp']");


    //headers
    private final By aboutHeader = By.xpath("//h1[text()='Привет']");
    private final By contactsHeader = By.xpath("//h1[text()='Контакты']");
    private final By shopsHeader = By.xpath("//h1[text()='Магазины']");
    private final By vacancyHeader = By.xpath("//h1[text()='Доступные вакансии:']");
    private final By soglashenieHeader = By.xpath("//h1[text()='Соглашение на использование пользовательских материалов']");
    private final By dostavkaHeader = By.xpath("//h1[text()='Доставка и оплата']");
    private final By obmenHeader = By.xpath("//h1[text()='Обмен и возврат']");
    private final By garantiiHeader = By.xpath("//h1[text()='Гарантийный сервис']");
    private final By ofertaHeader = By.xpath("//h1[text()='Оферта']");
    private final By personalnyeDannyeHeader = By.xpath("//h1[@class='service-page__title']");
    private final By telegaHeader = By.xpath("//span[text()='I am Poisoned']");
    private final By tikTokHeader = By.xpath("//h2[text()='poisondropru']");
    private final By youtubeHeader = By.xpath("//yt-formatted-string[text()='Poison Drop']");
    private final By whatsAppHeader = By.xpath("//h1[text()='Poison Drop by ООО \"ПойзонДроп\"']");

    public Footer(WebDriver driver) {
        super(driver);
    }


    public Footer clickToAboutButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(aboutButton));
        return this;
    }

    public String getAboutHeader() {
        return driver.findElement(aboutHeader).getText();
    }

    public Footer clickToContactsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(contactsButton));
        return this;
    }

    public String getContactsHeader() {
        return driver.findElement(contactsHeader).getText();
    }

    public Footer clickToShopsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(shopsButton));
        return this;
    }

    public String getShopsHeader() {
        return driver.findElement(shopsHeader).getText();
    }

    public Footer clickToVacancyButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(vacancyButton));
        return this;
    }

    public String getVacancyHeader() {
        return driver.findElement(vacancyHeader).getText();
    }

    public Footer clickToSoglashenieButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(soglashenieButton));
        return this;
    }

    public String getSoglashenieHeader() {
        return driver.findElement(soglashenieHeader).getText();
    }

    public Footer clickToDostavkaButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(dostavkaButton));
        return this;
    }

    public String getDostavkaHeader() {
        return driver.findElement(dostavkaHeader).getText();
    }

    public Footer clickToObmenButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(obmenButton));
        return this;
    }

    public String getObmenHeader() {
        return driver.findElement(obmenHeader).getText();
    }

    public Footer clickToGarantiiButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(garantiiButton));
        return this;
    }

    public String getGarantiiHeader() {
        return driver.findElement(garantiiHeader).getText();
    }

    public Footer clickToOfertaButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ofertaButton));
        return this;
    }

    public String getOfertaHeader() {
        return driver.findElement(ofertaHeader).getText();
    }

    public Footer clickToPersonalnyeDannyeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(personalnyeDannyeButton));
        return this;
    }

    public String getPersonalnyeDannyeHeader() {
        return driver.findElement(personalnyeDannyeHeader).getText();
    }

    public Footer clickToInstaButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(instaButton));
        return this;
    }

    public Footer clickToTelegaButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(telegaButton));
        return this;
    }

    public Footer clickToTikTokButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(tikTokButton));
        return this;
    }

    public Footer clickToYoutubeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(youtubeButton));
        return this;
    }

    public String getYoutubeHeader() {
        return driver.findElement(youtubeHeader).getText();
    }

    public String getTelegaHeader() {
        return driver.findElement(telegaHeader).getText();
    }

    public Footer clickToWhatsAppButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whatsAppButton));
        return this;
    }

    public String getWhatsAppHeader() {
        return driver.findElement(whatsAppHeader).getText();
    }
}
