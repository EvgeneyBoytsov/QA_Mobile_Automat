package pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class IosAction extends AppiumUtils { // объявляет класс IosAction
    public IOSDriver driver; // объявляет поле/переменную типа iOS-драйвер Appium

    public IosAction(IOSDriver driver) { // объявляет конструктор IosAction
        this.driver = driver; // сохраняет переданный драйвер в поле класса
    }
    public AppiumDriverLocalService server; // объявляет поле/переменную типа локальный Appium-сервер

    @BeforeClass // запускает метод перед классом тестов
    //запуск сервера
    public void ConfigServer() throws URISyntaxException, MalformedURLException { // объявляет метод ConfigServer
        server = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium/build//lib//main.js//")) // указывает путь к установленному Appium main.js для запуска сервера из кода
                .withIPAddress("127.0.0.1").usingPort(4723).build(); // собирает локальный Appium-сервер на localhost и порту 4723
        server.start(); // запускает Appium-сервер

        XCUITestOptions options = new XCUITestOptions(); // создает capabilities/options для запуска iOS-симулятора через XCUITest
        options.setDeviceName("iPhone 12 Pro"); // задает имя устройства для запуска
        options.setApp("/Users/evgeniy/Library/Developer/Xcode/DerivedData/UIKitCatalog-edpbduylvfsifsapgylqetxjxnii/Build/Products/Debug-iphonesimulator/UIKitCatalog.app"); // задает путь к приложению
        options.setPlatformVersion("15.2"); // задает версию iOS
        options.setWdaLaunchTimeout(Duration.ofSeconds(20)); // задает таймаут запуска WebDriverAgent

        driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options); // подключает IOSDriver к локальному Appium-серверу и передает настройки симулятора
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // задает неявное ожидание элементов
    }
    @AfterClass // запускает метод после класса тестов
    // оставновка сервера и драйвера
    public void TearDown() { // объявляет метод TearDown
        driver.quit(); // закрывает драйвер устройства
        server.stop(); // останавливает Appium-сервер
    }
    public void LongPress(WebElement element) { // объявляет метод LongPress
        //WebElement element = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == 'Increment'`][3]"));
        Map<String,Object> params = new HashMap<>(); // создает Map параметров для iOS-команды touchAndHold
        params.put("element", ((RemoteWebElement)element).getId()); // получает id элемента для мобильного жеста
        params.put("duration", 5); // добавляет параметр в словарь
        driver.executeScript("mobile:touchAndHold", params ); // вызывает iOS mobile-команду долгого нажатия с параметрами element и duration
    }

    public void Scroll(WebElement element) { // объявляет метод Scroll
        //WebElement element = driver.findElement(AppiumBy.accessibilityId("Web View"));
        Map<String,Object> paramsScroll = new HashMap<>(); // создает Map параметров для iOS-команды scroll
        paramsScroll.put("direction", "down"); // добавляет параметр в словарь
        paramsScroll.put("element", ((RemoteWebElement)element).getId()); // получает id элемента для мобильного жеста
        driver.executeScript("mobile:scroll", paramsScroll); // вызывает iOS mobile-команду скролла внутри указанного элемента
    }

    public void Swipe(WebElement element) { // объявляет метод Swipe
        Map<String, Object> paramsSwipe = new HashMap<String, Object>(); // создает Map параметров для iOS-команды swipe
        paramsSwipe.put("direction", "left"); // добавляет параметр в словарь
        driver.executeScript("mobile:swipe", paramsSwipe); // вызывает iOS mobile-команду свайпа в указанном направлении
    }
}
