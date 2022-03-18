package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Subscription extends Base {

    private final By subscriptionText = By.xpath("//h2[text()='подписчики нашей рассылки выбирают самые классные украшения.']");
    private final By subscriptionButton = By.xpath("//span[text()='подписаться']");
    private final By subscriptionMail = By.xpath("//input[@type='email']");
    private final By successfulSubscriptionText = By.xpath("//h2[text()='наш почтовый голубь уже вылетел.']");
    private final By failedSubscriptionText = By.xpath("//p[@class='message message_error']");


    public Subscription(WebDriver driver) {
        super(driver);
    }


    public void typeEmail(String email) {
        type(email, subscriptionMail);
    }

    public String getSubscriptionText() {
        return driver.findElement(subscriptionText).getText();
    }

    public String getSubscriptionButtonText() {
        return driver.findElement(subscriptionButton).getText();
    }

    public void clickOnSubscriptionButton() {
        click(subscriptionButton);
    }

    public String getSuccessfulSubscriptionText() {
        return driver.findElement(successfulSubscriptionText).getText();
    }

    public String getFailedSubscriptionText() {
        return driver.findElement(failedSubscriptionText).getText();
    }

    public void subscriptionWithEmail(String email) {
        this.typeEmail(email);
        this.clickOnSubscriptionButton();
    }


}
