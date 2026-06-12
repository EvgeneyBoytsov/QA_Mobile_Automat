package pageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AndroidAction { // объявляет класс ProductCatalog
    AndroidDriver driver; // объявляет поле/переменную типа Android-драйвер Appium
    public ProductCatalog (AndroidDriver driver) { // объявляет конструктор ProductCatalog
        super(driver); // вызывает конструктор родительского класса
        this.driver = driver; // сохраняет переданный драйвер в поле класса
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // связывает поля с @AndroidFindBy с реальными элементами каталога через Appium PageFactory
    }
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']") // описывает все кнопки ADD TO CART; индекс выбирает конкретный товар
    private List<WebElement> addToCartBtn; // объявляет поле/переменную типа элемент интерфейса

    @AndroidFindBy (id = "com.androidsample.generalstore:id/appbar_btn_cart") // описывает иконку корзины в верхней панели приложения
    private WebElement cartBtn; // объявляет поле/переменную типа элемент интерфейса

    public void getAddToCartBtn(int index) { // объявляет метод получения данных getAddToCartBtn
        addToCartBtn.get(index).click(); // кликает по элементу
    }

    public CartPage clickCartBtn() throws InterruptedException { // объявляет метод клика clickCartBtn
        cartBtn.click(); // кликает по элементу
        Thread.sleep(2000); // делает паузу для ожидания интерфейса
        return new CartPage(driver); // возвращает Page Object корзины, потому что после клика открывается экран Cart
    }
}
