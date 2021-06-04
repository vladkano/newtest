import catalog.Bracelets;
import catalog.Earrings;
import catalog.Necklaces;
import catalog.Rings;
import filters.Filters;
import filters.Size;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sections.Designers;
import sections.ShopTheLook;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductCardsTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        filters = new Filters(driver);
        size = new Size(driver);
        basket = new Basket(driver);
        picture = new Picture(driver);
    }

    /*
    Переход в раздел колец, в фильтре выбираем кольцо, далее переходим в карточку товара и переключаемся между размерами
    смотрим чтобы менялся размер, кладем в корзину и проверяем что верный размер попал в корзину
    */

    //с размером 14,5
    @Test
    public void changeSize145() {
        driver.get(getUrl + "catalog/koltsa/");
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToFirstSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    //с размером 15,5
    @Test
    public void changeSize155() {
        driver.get(getUrl + "catalog/koltsa/");
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToSecondSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }


    //с размером 16,5
    @Test
    public void changeSize165() {
        driver.get(getUrl + "catalog/koltsa/");
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToThirdSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    /*
    Если товар только на виртуальном складе(3-5 дней) или только на складе в Питере(7 дней),
    то должна быть плашка "Доставка от 3-5 дней" (storage id = 1 или 5)
     */

    //Плашка "Доставка от 3-5 дней"
    @Test
    public void firstCheckPlate() {
        search = new Search(driver);
        driver.get(getUrl + "catalog");
        basket.clickToOkButton();
        String firstItem = size.findItem35days();
        search.getSearch(firstItem + "\n");
        size.clickOnImageLink();
        String plateHeader = size.getPlateHeader();
        assertEquals("Доставка от 3-5 дней", plateHeader);
    }

    //Плашка "Доставка от 7 дней"
    @Test
    public void secondCheckPlate() {
        search = new Search(driver);
        driver.get(getUrl + "catalog");
        basket.clickToOkButton();
        String secondItem = size.findItem7days();
        search.getSearch(secondItem + "\n");
        size.clickOnImageLink();
        String plateHeader = size.getPlateHeader();
        assertEquals("Доставка от 7 дней", plateHeader);
    }

    //Отображение картинок в карточке товара
    @Test
    public void checkPictureListSergi() {
        driver.get(getUrl + "catalog/sergi");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    public void checkPictureListBraslety() {
        driver.get(getUrl + "catalog/braslety");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    public void checkPictureListKoltsa() {
        driver.get(getUrl + "catalog/koltsa");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    public void checkPictureListKole() {
        driver.get(getUrl + "catalog/kole");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        System.out.println(size);
        assertNotEquals(0, size);
    }


    //Если товара нет в наличии, то кнопки "в корзину" быть не должно
    @Test
    public void checkCartButtonSergi() {
        earrings = new Earrings(driver);
        String s = earrings.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/sergi/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    public void checkCartButtonBraslety() {
        bracelets = new Bracelets(driver);
        String s = bracelets.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/braslety/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    public void checkCartButtonKole() {
        necklaces = new Necklaces(driver);
        String s = necklaces.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/kole/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    public void checkCartButtonKoltsa() {
        rings = new Rings(driver);
        String s = rings.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/koltsa/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }


    /*
    Если товар входит в коллекцию, то должен отображаться блок "Украшения из образа"
    Также кликаем на первый товар из блока и смотрим правильно ли совершается переход на страницу товара
     */
    @Test
    public void checkSetWindowSergi() {
        set = new Set(driver);
        earrings = new Earrings(driver);
        String s = earrings.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/sergi/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Украшения из образа", setHeader);
        assertEquals(href, currentUrl);
    }

    @Test
    public void checkSetWindowBraslety() {
        set = new Set(driver);
        bracelets = new Bracelets(driver);
        String s = bracelets.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/braslety/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Украшения из образа", setHeader);
        assertEquals(href, currentUrl);
    }

    @Test
    public void checkSetWindowKole() {
        set = new Set(driver);
        necklaces = new Necklaces(driver);
        String s = necklaces.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/kole/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Украшения из образа", setHeader);
        assertEquals(href, currentUrl);
    }

    @Test
    public void checkSetWindowKoltsa() {
        set = new Set(driver);
        rings = new Rings(driver);
        String s = rings.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/koltsa/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Украшения из образа", setHeader);
        assertEquals(href, currentUrl);
    }


    //Проверяем что товары из блока "Украшения из образа" можно перенести в корзину
    @Test
    public void checkBasketSergi() {
        set = new Set(driver);
        earrings = new Earrings(driver);
        String s = earrings.getItemsFromSet().get(1);
        driver.get(getUrl + "catalog/sergi/" + s);
        basket.clickToOkButton();
        basket.clickToSetItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void checkBasketBraslety() {
        set = new Set(driver);
        bracelets = new Bracelets(driver);
        String s = bracelets.getItemsFromSet().get(1);
        driver.get(getUrl + "catalog/braslety/" + s);
        basket.clickToOkButton();
        basket.clickToSetItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void checkBasketKole() {
        set = new Set(driver);
        necklaces = new Necklaces(driver);
        String s = necklaces.getItemsFromSet().get(1);
        driver.get(getUrl + "catalog/kole/" + s);
        basket.clickToOkButton();
        basket.clickToSetItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void checkBasketKoltsa() {
        set = new Set(driver);
        rings = new Rings(driver);
        String s = rings.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/koltsa/" + s);
        basket.clickToOkButton();
        basket.clickToSizeItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    //Отображение блока дизайнеры(фото, название, описание)
    @Test
    public void checkingDesignersBlockSergi() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/sergi/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        assertEquals(text, photoAlt);
        assertEquals(text, designerName);
        assertEquals(description, designerText);
    }

    @Test
    public void checkingDesignersBlockBraslety() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/braslety/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        assertEquals(text, photoAlt);
        assertEquals(text, designerName);
        assertEquals(description.substring(0, 100), designerText.substring(0, 100));
    }

    @Test
    public void checkingDesignersBlockKole() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/kole/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        assertEquals(text, photoAlt);
        assertEquals(text, designerName);
        assertEquals(description, designerText);
    }

    @Test
    public void checkingDesignersBlockKoltsa() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/koltsa/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        assertEquals(text, photoAlt);
        assertEquals(text, designerName);
        assertEquals(description, designerText);
    }

    //Кнопка перехода к товарам дизайнера
    @Test
    public void buttonForNavigatingToDesignerProductsSergi() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/sergi/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    @Test
    public void buttonForNavigatingToDesignerProductsBraslety() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/braslety/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    @Test
    public void buttonForNavigatingToDesignerProductsKole() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/kole/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    @Test
    public void buttonForNavigatingToDesignerProductsKoltsa() {
        designers = new Designers(driver);
        driver.get(getUrl + "catalog/koltsa/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    //Отображение блока SHOP THE LOOK(надпись, фото и клик по нему)
    @Test
    public void checkingShopTheLookBlockSergi() {
        shopTheLook = new ShopTheLook(driver);
        driver.get(getUrl + "catalog/sergi/pozolocennye_sergi_tapeo_shrimp");
        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
        shopTheLook.clickOnShopTheLookPhoto();
        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
        assertEquals("Shop the look", shopTheLookHeader);
        assertEquals("1", frisbuyMarker);
    }

    @Test
    public void checkingShopTheLookBlockBraslety() {
        shopTheLook = new ShopTheLook(driver);
        driver.get(getUrl + "catalog/braslety/braslet_flow_black_iz_tonkix_cepocek");
        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
        shopTheLook.clickOnShopTheLookPhoto();
        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
        assertEquals("Shop the look", shopTheLookHeader);
        assertEquals("1", frisbuyMarker);
    }

    @Test
    public void checkingShopTheLookBlockKole() {
        shopTheLook = new ShopTheLook(driver);
        driver.get(getUrl + "catalog/kole/crystalline_pozolocennoe_kole_cep_s_kamnyami/?stone=zelyonyi-zad");
        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
        shopTheLook.clickOnShopTheLookPhoto();
        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
        assertEquals("Shop the look", shopTheLookHeader);
        assertEquals("1", frisbuyMarker);
    }

    @Test
    public void checkingShopTheLookBlockKoltsa() {
        shopTheLook = new ShopTheLook(driver);
        driver.get(getUrl + "catalog/koltsa/kolco_s_lunnym_kamnem_ogranki_baget_16");
        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
        shopTheLook.clickOnShopTheLookPhoto();
        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
        assertEquals("Shop the look", shopTheLookHeader);
        assertEquals("1", frisbuyMarker);
    }


    /* Отображение блоков:
    -СОСТАВ И ХАРАКТЕРИСТИКИ
    -УХОД ЗА УКРАШЕНИЯМИ
    -ДОСТАВКА, ОПЛАТА, ВОЗВРАТ
    -ГАРАНТИЯ 6 МЕСЯЦЕВ
    -Код
    */

    //Отображение 4 блоков(СОСТАВ И ХАРАКТЕРИСТИКИ, УХОД ЗА УКРАШЕНИЯМИ, "ДОСТАВКА, ОПЛАТА, ВОЗВРАТ", ГАРАНТИЯ 6 МЕСЯЦЕВ)
    @Test
    public void checkingBlocksSergi() {
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/sergi/");
        size.clickOnImageLink();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();

        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();

        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();

        assertEquals("Состав:", specification.substring(0, 7));
        assertEquals("Цветной", tsvetnoy);
        assertEquals("Метрополис", metropolis);
        assertEquals("Афимолл", afimoll);
        assertEquals("Атриум", atrium);
        assertEquals("APR Санкт-Петербург", redBridge);
        assertEquals("Ювелирные украшения", jewelryCareHeader);
        assertEquals("Носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося\n" +
                "в них спирта)", jewelryCareText);
        assertEquals("Бижутерия", bijouterieCareHeader);
        assertEquals("Украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте\n" +
                "их перед душем и нанесением косметики.\n" +
                "Подробнее о хранении и уходе.", bijouterieCareText);
        assertEquals("Доставка по Москве на следующий день, кроме воскресенья.", deliveryText.substring(0, 56));
        assertEquals("На все украшения в Poison Drop мы даём гарантию 6 месяцев с момента покупки. Если\n" +
                "вещь сломается в течение гарантийного срока, мы отремонтируем её или вернём деньги.\n" +
                " Подробнее о гарантии.", guaranteeText);
    }

    @Test
    public void checkingBlocksBraslety() {
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/braslety/");
        size.clickOnImageLink();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();

        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();

        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();

        assertEquals("Состав:", specification.substring(0, 7));
        assertEquals("Цветной", tsvetnoy);
        assertEquals("Метрополис", metropolis);
        assertEquals("Афимолл", afimoll);
        assertEquals("Атриум", atrium);
        assertEquals("APR Санкт-Петербург", redBridge);
        assertEquals("Ювелирные украшения", jewelryCareHeader);
        assertEquals("Носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося\n" +
                "в них спирта)", jewelryCareText);
        assertEquals("Бижутерия", bijouterieCareHeader);
        assertEquals("Украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте\n" +
                "их перед душем и нанесением косметики.\n" +
                "Подробнее о хранении и уходе.", bijouterieCareText);
        assertEquals("Доставка по Москве на следующий день, кроме воскресенья.", deliveryText.substring(0, 56));
        assertEquals("На все украшения в Poison Drop мы даём гарантию 6 месяцев с момента покупки. Если\n" +
                "вещь сломается в течение гарантийного срока, мы отремонтируем её или вернём деньги.\n" +
                " Подробнее о гарантии.", guaranteeText);
    }

    @Test
    public void checkingBlocksKole() {
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/kole/");
        size.clickOnImageLink();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();

        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();

        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();

        assertEquals("Состав:", specification.substring(0, 7));
        assertEquals("Цветной", tsvetnoy);
        assertEquals("Метрополис", metropolis);
        assertEquals("Афимолл", afimoll);
        assertEquals("Атриум", atrium);
        assertEquals("APR Санкт-Петербург", redBridge);
        assertEquals("Ювелирные украшения", jewelryCareHeader);
        assertEquals("Носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося\n" +
                "в них спирта)", jewelryCareText);
        assertEquals("Бижутерия", bijouterieCareHeader);
        assertEquals("Украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте\n" +
                "их перед душем и нанесением косметики.\n" +
                "Подробнее о хранении и уходе.", bijouterieCareText);
        assertEquals("Доставка по Москве на следующий день, кроме воскресенья.", deliveryText.substring(0, 56));
        assertEquals("На все украшения в Poison Drop мы даём гарантию 6 месяцев с момента покупки. Если\n" +
                "вещь сломается в течение гарантийного срока, мы отремонтируем её или вернём деньги.\n" +
                " Подробнее о гарантии.", guaranteeText);
    }

    @Test
    public void checkingBlocksKoltsa() {
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/koltsa/");
        size.clickOnImageLink();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();

        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();

        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();

        assertEquals("Состав:", specification.substring(0, 7));
        assertEquals("Цветной", tsvetnoy);
        assertEquals("Метрополис", metropolis);
        assertEquals("Афимолл", afimoll);
        assertEquals("Атриум", atrium);
        assertEquals("APR Санкт-Петербург", redBridge);
        assertEquals("Ювелирные украшения", jewelryCareHeader);
        assertEquals("Носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося\n" +
                "в них спирта)", jewelryCareText);
        assertEquals("Бижутерия", bijouterieCareHeader);
        assertEquals("Украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте\n" +
                "их перед душем и нанесением косметики.\n" +
                "Подробнее о хранении и уходе.", bijouterieCareText);
        assertEquals("Доставка по Москве на следующий день, кроме воскресенья.", deliveryText.substring(0, 56));
        assertEquals("На все украшения в Poison Drop мы даём гарантию 6 месяцев с момента покупки. Если\n" +
                "вещь сломается в течение гарантийного срока, мы отремонтируем её или вернём деньги.\n" +
                " Подробнее о гарантии.", guaranteeText);
    }

    //Отображение верного Кода изделия в карточке товара
    @Test
    public void checkingCodeSergi() {
        earrings = new Earrings(driver);
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/sergi/");
        earrings.clickOnNameLink();
        String codeFromBase = earrings.getCodes().get(1);
        String codeFromSite = productCard.getItemCode();
        assertEquals("Код: " + codeFromBase, codeFromSite);
    }

    @Test
    public void checkingCodeBraslety() {
        bracelets = new Bracelets(driver);
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/braslety/");
        bracelets.clickOnNameLink();
        String codeFromBase = bracelets.getCodes().get(1);
        String codeFromSite = productCard.getItemCode();
        assertEquals("Код: " + codeFromBase, codeFromSite);
    }

    @Test
    public void checkingCodeKole() {
        necklaces = new Necklaces(driver);
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/kole/");
        necklaces.clickOnNameLink();
        String codeFromBase = necklaces.getCodes().get(1);
        String codeFromSite = productCard.getItemCode();
        assertEquals("Код: " + codeFromBase, codeFromSite);
    }

    @Test
    public void checkingCodeKoltsa() {
        rings = new Rings(driver);
        productCard = new ProductCard(driver);
        driver.get(getUrl + "catalog/koltsa/");
        rings.clickOnNameLink();
        String codeFromBase = rings.getCodes().get(1);
        String codeFromSite = productCard.getItemCode();
        assertEquals("Код: " + codeFromBase, codeFromSite);
    }


    /*
    Проверяем переключение между элементами конструктора(золото/серебро и т.п.)
    Меняется имя по 4 типам товаров
     */
    @Test
    public void constructorSergi() {
        earrings = new Earrings(driver);
        collection = new Collection(driver);
        driver.get(getUrl + "catalog/sergi/");
        collection.clickOnFirstHref();
        String firstName = earrings.getHeader();
        collection.clickOnFirstItem();
        String secondName = earrings.getHeader();
        assertNotEquals(firstName, secondName);
    }

    @Test
    public void constructorBraslety() {
        bracelets = new Bracelets(driver);
        collection = new Collection(driver);
        driver.get(getUrl + "catalog/braslety/");
        collection.clickOnFirstHref();
        String firstName = bracelets.getHeader();
        collection.clickOnFirstItem();
        String secondName = bracelets.getHeader();
        assertNotEquals(firstName, secondName);
    }

    @Test
    public void constructorKole() {
        necklaces = new Necklaces(driver);
        collection = new Collection(driver);
        driver.get(getUrl + "catalog/kole/");
        collection.clickOnFirstHref();
        String firstName = necklaces.getHeader();
        collection.clickOnFirstItem();
        String secondName = necklaces.getHeader();
        assertNotEquals(firstName, secondName);
    }

    @Test
    public void constructorKoltsa() {
        rings = new Rings(driver);
        collection = new Collection(driver);
        driver.get(getUrl + "catalog/koltsa/");
        collection.clickOnSecondHref();
        String firstName = rings.getHeader();
        collection.clickOnFirstItem();
        String secondName = rings.getHeader();
        System.out.println(firstName);
        System.out.println(secondName);
        assertNotEquals(firstName, secondName);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
