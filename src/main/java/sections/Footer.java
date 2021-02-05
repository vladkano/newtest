package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Footer {

    private WebDriver driver;

    public Footer(WebDriver driver) {
        this.driver = driver;
    }

    By aboutButton = By.xpath("//a[text()='О нас']");
    By contactsButton = By.xpath("//a[text()='Контакты']");
    By shopsButton = By.xpath("//a[text()='Адреса магазинов']");
    By vacancyButton = By.xpath("//a[text()='Вакансии']");
    By soglashenieButton = By.xpath("//a[text()='Пользовательское соглашение']");
    By dostavkaButton = By.xpath("//a[text()='Доставка и оплата']");
    By obmenButton = By.xpath("//a[text()='Обмен и возврат']");
    By garantiiButton = By.xpath("//a[text()='Гарантии']");
    By ofertaButton = By.xpath("//a[text()='Оферта']");
    By personalnyeDannyeButton = By.xpath("//a[text()='Персональные данные']");
    By instaButton = By.xpath("//a[@aria-label='instagram']");
    By facebookButton = By.xpath("//a[@aria-label='facebook']");
    By youtubeButton = By.xpath("//a[@aria-label='youtube']");
    By whatsAppButton = By.xpath("//a[@aria-label='whatsApp']");


    //headers
    By aboutHeader = By.xpath("//h1[text()='Привет']");
    By contactsHeader = By.xpath("//h1[text()='Контакты']");
    By shopsHeader = By.xpath("//h1[text()='Магазины']");
    By vacancyHeader = By.xpath("//h1[text()='Доступные вакансии:']");
    By soglashenieHeader = By.xpath("//h1[text()='Соглашение на использование пользовательских материалов']");
    By dostavkaHeader = By.xpath("//h1[text()='Доставка и оплата']");
    By obmenHeader = By.xpath("//h1[text()='Обмен и возврат']");
    By garantiiHeader = By.xpath("//h1[text()='Гарантийный сервис']");
    By ofertaHeader = By.xpath("//h1[text()='Оферта']");
    By personalnyeDannyeHeader = By.xpath("//h1[@class='service-page__title']");
    By instaHeader = By.xpath("//h2[text()='poisondropru']");
    By facebookHeader = By.xpath("//span[text()='Poison Drop']");
    By youtubeHeader = By.xpath("//yt-formatted-string[text()='Poison Drop']");
    By whatsAppHeader = By.xpath("//span[text()='+7 495 255-15-33']");



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

    public String getInstaHeader() {
        return driver.findElement(instaHeader).getText();
    }

    public Footer clickToFacebookButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(facebookButton));
        return this;
    }

    public String getFacebookHeader() {
        return driver.findElement(facebookHeader).getText();
    }

    public Footer clickToYoutubeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(youtubeButton));
        return this;
    }

    public String getYoutubeHeader() {
        return driver.findElement(youtubeHeader).getText();
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
