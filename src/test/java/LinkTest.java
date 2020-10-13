import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LinkTest {

    private static WebDriver driver;
    private static EarringsPage earrings;
    private static NecklacesPage necklaces;
    private static BraceletsPage bracelets;
    private static RingsPage rings;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    //Поверяем работу 3-х ссылок по каждому типу товаров на переход к товару:
    // при нажатии на картинку, нажатии на дизайнера и нажатии на название изделия

    @Test
    public void imageLinkOfBracelets() {
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);
        bracelets.clickOnBraceletImageLink();
        String heading = bracelets.getBraceletImageHeader();
        Assert.assertEquals("Браслет из белого золота с розовыми сапфирами (17 см, 1,61 ct)", heading);
    }

    @Test
    public void nameLinkOfBracelets() {
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);
        bracelets.clickOnBraceletNameLink();
        String heading = bracelets.getBraceletNameHeader();
        Assert.assertEquals("Браслет «Донатс» (XL)", heading);
    }

    @Test
    public void designerLinkOfBracelets() {
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);
        bracelets.clickOnBraceletDesignerLink();
        String heading = bracelets.getbraceletDesignerHeader();
        Assert.assertEquals("LAV'Z", heading);
    }

    @Test
    public void imageLinkOfEarrings() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);
        earrings.clickOnEarringsImageLink();
        String heading = earrings.getEarringsImageHeader();
        Assert.assertEquals("Незамкнутые серьги-кольца из белого золота с бриллиантами (0,06 ct)", heading);
    }

    @Test
    public void nameLinkOfEarrings() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);
        earrings.clickOnEarringsNameLink();
        String heading = earrings.getEarringsNameHeader();
        Assert.assertEquals("Серьги-скрепки из золота с крупными рубинами, из коллекции Out of office (0,424 ct, 3/3)", heading);
    }

    @Test
    public void designerLinkOfEarrings() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);
        earrings.clickOnEarringsDesignerLink();
        String heading = earrings.getEarringsDesignerHeader();
        Assert.assertEquals("Meadowlark", heading);
    }


    @Test
    public void imageLinkOfNecklaces() {
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);
        necklaces.clickOnNecklacesImageLink();
        String heading = necklaces.getNecklacesImageHeader();
        Assert.assertEquals("Позолоченное кольцо на мизинец, из коллекции Initials (15, Шрифт №2)", heading);
    }

    @Test
    public void nameLinkOfNecklaces() {
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);
        necklaces.clickOnNecklacesNameLink();
        String heading = necklaces.getNecklacesNameHeader();
        Assert.assertEquals("Сотуар из белого золота с лунным камнем (6,24 ct)", heading);
    }

    @Test
    public void designerLinkOfNecklaces() {
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);
        necklaces.clickOnNecklacesDesignerLink();
        String heading = necklaces.getNecklacesDesignerHeader();
        Assert.assertEquals("35.02", heading);
    }


    @Test
    public void imageLinkOfRings() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);
        rings.clickOnRingImageLink();
        String heading = rings.getRingImageHeader();
        Assert.assertEquals("Покрытое серебром кольцо Etty с лазуритом (17,5)", heading);
    }

    @Test
    public void nameLinkOfRings() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);
        rings.clickOnRingNameLink();
        String heading = rings.getRingNameHeader();
        Assert.assertEquals("Кольцо Titia с прямоугольными кристаллами (15)", heading);
    }

    @Test
    public void designerLinkOfRings() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);
        rings.clickOnRingDesignerLink();
        String heading = rings.getRingDesignerHeader();
        Assert.assertEquals("Philippe Audibert", heading);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
