package pageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidAction { // объявляет класс FormPage
    AndroidDriver driver; // объявляет поле/переменную типа Android-драйвер Appium
    private static final String APP_PACKAGE = "com.androidsample.generalstore"; // объявляет константу
    public FormPage (AndroidDriver driver) { // объявляет конструктор FormPage
        super(driver); // вызывает конструктор родительского класса
        this.driver = driver; // сохраняет переданный драйвер в поле класса
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // связывает поля с @AndroidFindBy с реальными элементами экрана через Appium PageFactory
    }
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField") // описывает поле ввода имени на стартовой форме
    private WebElement nameField; // объявляет поле/переменную типа элемент интерфейса
    @AndroidFindBy(id = "android:id/text1") // описывает выпадающий список выбора страны
    private WebElement countryField; // объявляет поле/переменную типа элемент интерфейса
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop") // описывает кнопку перехода из формы в каталог товаров
    private WebElement btnLetsShop; // объявляет поле/переменную типа элемент интерфейса


    public void setNameField (String name) { // объявляет метод настройки setNameField
        nameField.sendKeys(name); // вводит текст в поле
        driver.hideKeyboard(); // скрывает экранную клавиатуру
    }

    public void setCountryField (String country) { // объявляет метод настройки setCountryField
        countryField.click(); // кликает по элементу
        ScrollToText(country); // скроллит список до нужной страны
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + country + "']")).click(); // находит элемент и кликает по нему
    }

    public ProductCatalog clickBtnLetsShop () { // объявляет метод клика clickBtnLetsShop
        btnLetsShop.click(); // кликает по элементу
        return new ProductCatalog(driver); // возвращает Page Object каталога, потому что после клика открывается экран товаров
    }
    public void setActivity() { // объявляет метод настройки setActivity
        driver.terminateApp(APP_PACKAGE); // закрывает приложение
        driver.activateApp(APP_PACKAGE); // запускает приложение заново
    }
}
