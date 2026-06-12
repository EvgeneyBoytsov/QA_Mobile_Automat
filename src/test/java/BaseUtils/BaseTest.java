package BaseUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageObject.AppiumUtils;
import pageObject.FormPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

@Listeners(BaseUtils.Listeners.class) // подключает TestNG listener к тестам
public class BaseTest extends AppiumUtils { // объявляет класс BaseTest

    public AndroidDriver driver; // объявляет поле/переменную типа Android-драйвер Appium
    public FormPage formPage; // объявляет поле объекта страницы

    @BeforeClass(alwaysRun = true) // запускает метод перед классом тестов
    // запуск сервера
    public void ConfigServer() throws IOException { // объявляет метод ConfigServer

        Properties properties = new Properties(); // создает объект для хранения настроек из data.properties
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resourse//data.properties"); // открывает data.properties от корня проекта: user.dir — папка запуска тестов/Maven
        properties.load(fis); // загружает пары ключ=значение из data.properties в объект properties
        String IpAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") :  properties.getProperty("ipAddress"); // берет ipAddress из параметра запуска -DipAddress, а если его нет — из data.properties
        // это для того чтобы передать ipAddress из терминала. Если из терминала приходит то берем оттуда (после знака вопроса) Если не приходит то берем после :
        //String IpAddress = properties.getProperty("ipAddress");
        int PortNumber = Integer.parseInt(properties.getProperty("PortNumber")); // читает PortNumber из properties и переводит строку в int

        UiAutomator2Options options = new UiAutomator2Options(); // создает объект capabilities/options для запуска Android через UiAutomator2
        options.setPlatformName("Android"); // задает платформу Android
        options.setAutomationName("UiAutomator2"); // задает движок автоматизации
        String browserStackHub = "https://hub.browserstack.com/wd/hub"; // адрес удаленного Selenium/Appium hub в BrowserStack

        if (System.getProperty("browserstack.agent.path") != null) { // проверяет запущены ли тесты через BrowserStack SDK по системному свойству browserstack.agent.path
            DesiredCapabilities browserStackCapabilities = new DesiredCapabilities(); // создает набор capabilities для удаленного запуска в BrowserStack
            browserStackCapabilities.setCapability("platformName", "Android"); // задает платформу BrowserStack
            browserStackCapabilities.setCapability("appium:automationName", "UiAutomator2"); // задает автоматизацию BrowserStack
            driver = new AndroidDriver(new URL(browserStackHub), browserStackCapabilities); // подключает AndroidDriver к удаленному BrowserStack hub
        } else { // выполняет альтернативную ветку условия
            server = StartServer(IpAddress, PortNumber); // запускает локальный Appium-сервер
            options.setDeviceName(properties.getProperty("AndroidDeviceName")); // задает имя устройства для запуска
            options.setChromedriverExecutable("/Users/evgeniy/Downloads/chromedriver-mac-arm64/chromedriver"); // задает путь к ChromeDriver
            //options.setApp("/Users/evgeniy/IdeaProjects/untitled/src/test/java/res/ApiDemos-debug.apk");
            options.setApp(System.getProperty("user.dir") + "/src/test/java/res/General-Store.apk"); // указывает APK относительно корня проекта: user.dir — папка запуска тестов/Maven
            driver = new AndroidDriver(server.getUrl(), options); // создает Android-драйвер Appium
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // ожидание запуска сервера
        formPage = new FormPage(driver); // создание объекта класса форм пейдж
    }
    @AfterClass (alwaysRun = true) // запускает метод после класса тестов
    // оставновка сервера и драйвера
    public void TearDown() { // объявляет метод TearDown
        if (driver != null) { // проверяет условие перед выполнением блока
            driver.quit(); // закрывает драйвер устройства
        }
        if (server != null) { // проверяет условие перед выполнением блока
            server.stop(); // останавливает Appium-сервер
        }
    }
    // лонг клик
    public void LongPress (WebElement field) { // объявляет метод LongPress
        driver.executeScript("mobile: longClickGesture", // вызывает Appium mobile-команду долгого нажатия, которой нет в обычном Selenium click
                ImmutableMap.of("elementId", ((RemoteWebElement)field).getId(), "duration", 2000)); // передает id элемента и длительность нажатия 2000 мс
    }
    // скролл
    public void ScrollToEnd() { // общий helper: скроллит текущий экран вниз до самого конца
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
    // свайп
    public void SwipeLeft(WebElement firstImage, String Direction) { // объявляет метод SwipeLeft
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of( // вызывает Appium mobile-команду свайпа по конкретному элементу
                "elementId", ((RemoteWebElement)firstImage).getId(), // передает внутренний id элемента, по которому будет выполняться жест
                "direction", Direction, // задает направление свайпа
                "percent", 0.75)); // задает длину свайпа как 75% от размера элемента
    }
}
