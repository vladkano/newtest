
import catalog.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkTest {

    private static WebDriver driver;
    private static Earrings earrings;
    private static Necklaces necklaces;
    private static Bracelets bracelets;
    private static Rings rings;
    private static Brooches brooches;
    private static Pirsing pirsing;

    //private String getUrl = "http://176.53.182.129:8088/catalog/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/";
    private String getUrl = "https://poisondrop.ru/catalog/";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
//        driver.manage().window().maximize();
    }

    //Поверяем работу 3-х ссылок по каждому типу товаров на переход к товару:
    // при нажатии на картинку, нажатии на дизайнера и нажатии на название изделия

    @Test
    public void imageLinkOfBracelets() {
        driver.get(getUrl + "braslety");
        bracelets = new Bracelets(driver);
        String header = bracelets.getBraceletImageHeader();
        bracelets.clickOnBraceletImageLink();
        String heading = bracelets.getBraceletHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfBracelets() {
        driver.get(getUrl + "braslety");
        bracelets = new Bracelets(driver);
        String header = bracelets.getBraceletNameHeader();
        bracelets.clickOnBraceletNameLink();
        String heading = bracelets.getBraceletHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfBracelets() {
        driver.get(getUrl + "braslety");
        bracelets = new Bracelets(driver);
        String header = bracelets.getBraceletDesignerHeader();
        bracelets.clickOnBraceletDesignerLink();
        String heading = bracelets.getDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfEarrings() {
        driver.get(getUrl + "sergi");
        earrings = new Earrings(driver);
        String header = earrings.getImageHeader();
        earrings.clickOnImageLink();
        String heading = earrings.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfEarrings() {
        driver.get(getUrl + "sergi");
        earrings = new Earrings(driver);
        String header = earrings.getNameHeader();
        earrings.clickOnNameLink();
        String heading = earrings.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfEarrings() {
        driver.get(getUrl + "sergi");
        earrings = new Earrings(driver);
        String header = earrings.getDesignerHeader();
        earrings.clickOnDesignerLink();
        String heading = earrings.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfNecklaces() {
        driver.get(getUrl + "braslety");
        necklaces = new Necklaces(driver);
        String header = necklaces.getImageHeader();
        necklaces.clickOnImageLink();
        String heading = necklaces.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfNecklaces() {
        driver.get(getUrl + "braslety");
        necklaces = new Necklaces(driver);
        String header = necklaces.getNameHeader();
        necklaces.clickOnNameLink();
        String heading = necklaces.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfNecklaces() {
        driver.get(getUrl + "braslety");
        necklaces = new Necklaces(driver);
        String header = necklaces.getDesignerHeader();
        necklaces.clickOnDesignerLink();
        String heading = necklaces.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfRings() {
        driver.get(getUrl + "koltsa");
        rings = new Rings(driver);
        String header = rings.getImageHeader();
        rings.clickOnImageLink();
        String heading = rings.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfRings() {
        driver.get(getUrl + "koltsa");
        rings = new Rings(driver);
        String header = rings.getNameHeader();
        rings.clickOnNameLink();
        String heading = rings.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfRings() {
        driver.get(getUrl + "koltsa");
        rings = new Rings(driver);
        String header = rings.getDesignerHeader();
        rings.clickOnDesignerLink();
        String heading = rings.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfBrooches() {
        driver.get(getUrl + "broshi");
        brooches = new Brooches(driver);
        String header = brooches.getImageHeader();
        brooches.clickOnImageLink();
        String heading = brooches.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfBrooches() {
        driver.get(getUrl + "broshi");
        brooches = new Brooches(driver);
        String header = brooches.getNameHeader();
        brooches.clickOnNameLink();
        String heading = brooches.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfBrooches() {
        driver.get(getUrl + "broshi");
        brooches = new Brooches(driver);
        String header = brooches.getDesignerHeader();
        brooches.clickOnDesignerLink();
        String heading = brooches.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfPirsing() {
        driver.get(getUrl + "pirsing");
        pirsing = new Pirsing(driver);
        String header = pirsing.getImageHeader();
        pirsing.clickOnImageLink();
        String heading = pirsing.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfPirsing() {
        driver.get(getUrl + "pirsing");
        pirsing = new Pirsing(driver);
        String header = pirsing.getNameHeader();
        pirsing.clickOnNameLink();
        String heading = pirsing.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfPirsing() {
        driver.get(getUrl + "pirsing");
        pirsing = new Pirsing(driver);
        String header = pirsing.getDesignerHeader();
        pirsing.clickOnDesignerLink();
        String heading = pirsing.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
