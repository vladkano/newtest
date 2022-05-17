package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Footer extends Base {

    private final By aboutButton = By.xpath("//a[text()='о нас']");
    private final By contactsButton = By.xpath("//a[text()='контакты']");
    private final By shopsButton = By.xpath("//a[text()='адреса магазинов']");
    private final By vacancyButton = By.xpath("//a[text()='вакансии']");
    private final By soglashenieButton = By.xpath("//a[text()='пользовательское соглашение']");
    private final By dostavkaButton = By.xpath("//a[text()='доставка и оплата']");
    private final By obmenButton = By.xpath("//a[text()='обмен и возврат']");
    private final By garantiiButton = By.xpath("//a[text()='гарантии']");
    private final By ofertaButton = By.xpath("//a[text()='оферта']");
    private final By personalnyeDannyeButton = By.xpath("//a[text()='персональные данные']");
    private final By vkButton = By.xpath("//a[@aria-label='vkontakte']");
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
    private final By telegaHeader = By.xpath("//span[text()='Poison Drop']");
    private final By youtubeHeader = By.xpath("//yt-formatted-string[text()='Poison Drop']");
    private final By whatsAppHeader = By.xpath("//h3[text()='Poison Drop by ООО \"ПойзонДроп\"']");

    public Footer(WebDriver driver) {
        super(driver);
    }


    public void clickToAboutButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(aboutButton));
    }

    public String getAboutHeader() {
        return driver.findElement(aboutHeader).getText();
    }

    public void clickToContactsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(contactsButton));
    }

    public String getContactsHeader() {
        return driver.findElement(contactsHeader).getText();
    }

    public void clickToShopsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(shopsButton));
    }

    public String getShopsHeader() {
        return driver.findElement(shopsHeader).getText();
    }

    public void clickToVacancyButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(vacancyButton));
    }

    public String getVacancyHeader() {
        return driver.findElement(vacancyHeader).getText();
    }

    public void clickToSoglashenieButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(soglashenieButton));
    }

    public String getSoglashenieHeader() {
        return driver.findElement(soglashenieHeader).getText();
    }

    public void clickToDostavkaButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(dostavkaButton));
    }

    public String getDostavkaHeader() {
        return driver.findElement(dostavkaHeader).getText();
    }

    public void clickToObmenButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(obmenButton));
    }

    public String getObmenHeader() {
        return driver.findElement(obmenHeader).getText();
    }

    public void clickToGarantiiButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(garantiiButton));
    }

    public String getGarantiiHeader() {
        return driver.findElement(garantiiHeader).getText();
    }

    public void clickToOfertaButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ofertaButton));
    }

    public String getOfertaHeader() {
        return driver.findElement(ofertaHeader).getText();
    }

    public void clickToPersonalnyeDannyeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(personalnyeDannyeButton));
    }

    public String getPersonalnyeDannyeHeader() {
        return driver.findElement(personalnyeDannyeHeader).getText();
    }

    public void clickToVkButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(vkButton));
    }

    public void clickToTelegaButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(telegaButton));
    }

    public void clickToTikTokButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(tikTokButton));
    }

    public void clickToYoutubeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(youtubeButton));
    }

    public String getYoutubeHeader() {
        return driver.findElement(youtubeHeader).getText();
    }

    public String getTelegaHeader() {
        return driver.findElement(telegaHeader).getText();
    }

    public void clickToWhatsAppButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whatsAppButton));
    }

    public String getWhatsAppHeader() {
        return driver.findElement(whatsAppHeader).getText();
    }
}
