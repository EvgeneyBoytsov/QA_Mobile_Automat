package pageObject;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidAction extends AppiumUtils { // объявляет класс AndroidAction
    AndroidDriver driver; // объявляет поле/переменную типа Android-драйвер Appium
    public AndroidAction(AndroidDriver driver) { // объявляет конструктор AndroidAction

        this.driver = driver; // сохраняет переданный драйвер в поле класса
    }
    public void LongPress (WebElement field) { // объявляет метод LongPress
        assert ((RemoteWebElement) field).getId() != null; // проверяет, что у элемента есть Appium id перед выполнением long press
        driver.executeScript("mobile: longClickGesture", // вызывает Appium mobile-команду долгого нажатия, которой нет в обычном Selenium click
                ImmutableMap.of("elementId", ((RemoteWebElement)field).getId(), "duration", 2000)); // передает id элемента и длительность нажатия 2000 мс
    }
    // скролл
    public void ScrollToEnd() { // helper Page Object: скроллит текущий экран вниз до самого конца
        boolean canScrollMore; // хранит ответ Appium: можно ли продолжать скролл
        do { // начинает цикл с выполнением хотя бы один раз
            Object result = driver.executeScript("mobile: scrollGesture", // запускает Appium scrollGesture и получает ответ, остался ли скролл дальше
                    ImmutableMap.of( // собирает неизменяемую Map с параметрами жеста для Appium
                            "left",100, "top", 100, "width", 200, "height", 200, // задает область экрана, внутри которой выполняется скролл
                            "direction", "down", // задает направление скролла вниз
                            "percent", 3.0)); // задает силу скролла относительно выбранной области
            canScrollMore = Boolean.TRUE.equals(result); // безопасно проверяет ответ Appium: true значит страницу можно скроллить дальше
        }
        while(canScrollMore) ; // продолжает скроллить, пока Appium возвращает true
    }
    public void ScrollToText (String text) { // объявляет метод ScrollToText
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));")); // использует Android UiScrollable, чтобы найти элемент по видимому тексту даже если он ниже экрана
    }
    // свайп
    public void SwipeLeft(WebElement firstImage, String Direction) { // объявляет метод SwipeLeft
        assert ((RemoteWebElement) firstImage).getId() != null; // проверяет, что у картинки есть Appium id перед выполнением свайпа
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of( // вызывает Appium mobile-команду свайпа по конкретному элементу
                "elementId", ((RemoteWebElement)firstImage).getId(), // передает внутренний id картинки, по которой будет выполняться жест
                "direction", Direction, // задает направление свайпа, например left
                "percent", 0.75)); // задает длину свайпа как 75% от размера элемента
    }
}
