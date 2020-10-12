
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogNavigationTest {
    private WebDriver driver;
    private CatalogNavigation navigation;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //Кнопка "Показать ещё":
    //Кол-во единиц на странице после нажатия кнопки "Показать ещё"
    @Test()
    public void numberOfItem() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        navigation = new CatalogNavigation(driver);
        navigation.clickOnShowMoreButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> numbers = driver.findElements(By.xpath("//span[@class='catalog-card__image-hovered']"));
        Assert.assertEquals(96, numbers.size());
    }

    //Проверка отсутствия кнопки "Показать ещё", отображается только если в разделе больше 48 продуктов
    @Test
    public void showMoreNotVisible() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        navigation = new CatalogNavigation(driver);
        int numbers = driver.findElements(By.xpath("//span[text()='Показать ещё']")).size();
        Assert.assertEquals(0, numbers);

    }

    //Проверка отсутствия кнопки "Показать ещё" при переходе на последнюю страницу каталога
    @Test
    public void showMoreLastPage() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        navigation = new CatalogNavigation(driver);
        for (int i = 0; i < 18; i++) {
            navigation.clickOnShowMoreButton();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int numbers = driver.findElements(By.xpath("//span[text()='Показать ещё']")).size();
        Assert.assertEquals(0, numbers);

    }


    //Постраничная навигация:
    //Кол-во страниц в каталоге колец

    @Test
    public void numberOfPages() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        navigation = new CatalogNavigation(driver);
        double count = Math.ceil((double)navigation.countRings()/48);
        int countOfRings = (int) count;
        int numberOfPages = Integer.parseInt(navigation.getNumberOfPages());
        Assert.assertEquals(countOfRings, numberOfPages);
    }

    //Кол-во страниц в каталоге earrings, если страниц всего одна - блок не отображается
    @Test
    public void numberOfPagesNotVisible() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        navigation = new CatalogNavigation(driver);
        int numbers = driver.findElements(By.xpath("//button[@class='pagination__link']")).size();
        Assert.assertEquals(0, numbers);
    }

    //На странице 48 продуктов
    @Test()
    public void pageNumber48() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        navigation = new CatalogNavigation(driver);
        List<WebElement> numbers = driver.findElements(By.xpath("//span[@class='catalog-card__image-hovered']"));
        Assert.assertEquals(48, numbers.size());
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
