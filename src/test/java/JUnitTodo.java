import mainPage.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitTodo {

    private MainPage mainPage;
    private String getUrl = "https://poisondrop.ru/";

    public String username = "ran.owen13";
    public String accesskey = "BsPSdFBX3MaN1mkPjzLdXlXqWnmwHfYTvVo32wW44RdiHMgy30";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "your build name");
        capabilities.setCapability("name", "your test name");
        capabilities.setCapability("platform", "MacOS Big sur");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("version","14.0");
        capabilities.setCapability("visual",true);
        capabilities.setCapability("geoLocation","RU");

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSimple() throws Exception {
        try {
            //Change it to production page
            driver.get("https://lambdatest.github.io/sample-todo-app/");

            //Let's mark done first two items in the list.
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();

            // Let's add an item in the list.
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();

            // Let's check that the item we added is added in the list.
            String enteredText = driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
            if (enteredText.equals("Yey, Let's add it to list")) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void sigInWithPhoneNumber() {
        driver.get(getUrl);
        mainPage = new MainPage(driver);
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
        String heading = mainPage.getSigInHeader();
        assertEquals("Мария", heading);
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
