package pageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends AndroidAction { // объявляет класс CartPage
    AndroidDriver driver; // объявляет поле/переменную типа Android-драйвер Appium
    public CartPage (AndroidDriver driver) { // объявляет конструктор CartPage
        super(driver); // вызывает конструктор родительского класса
        this.driver = driver; // сохраняет переданный драйвер в поле класса
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // связывает поля с @AndroidFindBy с реальными элементами экрана через Appium PageFactory
    }
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice") // описывает список цен товаров, которые PageFactory найдет по Android id
    private List<WebElement> productPrice; // объявляет поле/переменную типа элемент интерфейса
    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl") // описывает итоговую сумму корзины, которую PageFactory найдет по Android id
    private WebElement  totalAmount; // объявляет поле/переменную типа элемент интерфейса
    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton") // описывает кнопку Terms, по которой будет выполняться long press
    private WebElement  terms; // объявляет поле/переменную типа элемент интерфейса
    @AndroidFindBy (id = "android:id/button1") // ищет элемент по id через PageFactory
    private WebElement acceptBtn; // объявляет поле/переменную типа элемент интерфейса
    @AndroidFindBy (className = "android.widget.CheckBox") // ищет элемент по className через PageFactory
    private WebElement  checkBox; // объявляет поле/переменную типа элемент интерфейса
    @AndroidFindBy (id = "com.androidsample.generalstore:id/btnProceed") // ищет элемент по id через PageFactory
    private WebElement  btnProceed; // объявляет поле/переменную типа элемент интерфейса

    public List<WebElement> getProductPrice() { // объявляет метод получения данных getProductPrice
        return productPrice; // возвращает результат метода
    }
    public double getSum() { // объявляет метод получения данных getSum
        int count = productPrice.size(); // получает количество найденных элементов
        double sum = 0; // хранит общую сумму цен товаров из корзины
        for (int i = 0; i < count; i++ ) { // запускает цикл по данным
            String amount = productPrice.get(i).getText(); // получает текст элемента
            double price = Double.parseDouble(amount.substring(1)); // убираем 1 символ и превращаем в строку
            sum = price + sum; // добавляет цену товара к общей сумме
        }
        return sum; // возвращает сумму всех цен, посчитанную по товарам в корзине
    }
    public double getTotalSum() { // объявляет метод получения данных getTotalSum
       return getFormatAmount(totalAmount.getText()); // возвращает итоговую сумму из UI корзины уже в формате double
    }
    public void clickProceed() { // объявляет метод клика clickProceed
        LongPress(terms); // открывает условия долгим нажатием
        acceptBtn.click(); // кликает по элементу
    }
    public void getCheckBox() { // объявляет метод получения данных getCheckBox
        checkBox.click(); // кликает по элементу
    }
    public void clickBtnProceed() throws InterruptedException { // объявляет метод клика clickBtnProceed
        btnProceed.click(); // кликает по элементу
        Thread.sleep(5000); // делает паузу для ожидания интерфейса
    }

}
