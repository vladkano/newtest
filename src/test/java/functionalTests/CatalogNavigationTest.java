package functionalTests;

import baseForTests.TestBase;
import catalog.CatalogNavigation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogNavigationTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        navigation = new CatalogNavigation(driver);
    }

    //Кнопка "Показать ещё":
    //Кол-во единиц на странице после нажатия кнопки "Показать ещё"
    @Test()
    public void numberOfItem() {
        driver.get(getUrl + "catalog/koltsa");
        navigation.clickOnShowMoreButton();
        sleep(2000);
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(96, numbers.size());
    }

    //Проверка отсутствия кнопки "Показать ещё", отображается только если в разделе больше 48 продуктов
    @Test
    public void showMoreNotVisible() {
        driver.get(getUrl + "catalog/?type_product=klipsy");
        int numbers = driver.findElements(showMore).size();
        assertEquals(0, numbers);
    }

    //Проверка отсутствия кнопки "Показать ещё" при переходе на последнюю страницу каталога
    @Test
    public void showMoreLastPage() {
        driver.get(getUrl + "catalog/koltsa/?type_product=pechatki");
        navigation.clickOnShowMoreButton();
        int numbers = driver.findElements(showMore).size();
        assertEquals(0, numbers);
    }

    //Постраничная навигация:
    //Кол-во страниц в каталоге колец
    @Test
    public void numberOfPagesKoltsa() {
        driver.get(getUrl + "catalog/koltsa");
        double count = Math.ceil((double) navigation.countRings() / numberOfFoto);
        int countOfRings = (int) count;
        int numberOfPages = Integer.parseInt(navigation.getNumberOfPages());
        assertEquals(countOfRings, numberOfPages);
    }

    //На странице 48 продуктов
    @Test()
    public void pageNumber48Koltsa() {
        driver.get(getUrl + "catalog/koltsa");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

    @Test()
    public void pageNumber48Sergi() {
        driver.get(getUrl + "catalog/sergi");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

    @Test()
    public void pageNumber48Kole() {
        driver.get(getUrl + "catalog/kole");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

    @Test()
    public void pageNumber48Braslety() {
        driver.get(getUrl + "catalog/braslety");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
