package Hybrid;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class mobileBrowser { // объявляет класс mobileBrowser
    public AndroidDriver driver; // объявляет поле/переменную типа Android-драйвер Appium
    public AppiumDriverLocalService server; // объявляет поле/переменную типа локальный Appium-сервер

    @Test // помечает метод как TestNG-тест
    public void MobileBrowserTets () throws URISyntaxException, MalformedURLException, InterruptedException { // объявляет метод MobileBrowserTets
        server = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium/build//lib//main.js//")) // указывает путь к установленному Appium main.js для запуска сервера из кода
                .withIPAddress("127.0.0.1").usingPort(4723).build(); // собирает локальный Appium-сервер на localhost и порту 4723
        server.start(); // запускает Appium-сервер

        UiAutomator2Options options = new UiAutomator2Options(); // создает capabilities/options для запуска Android-браузера через UiAutomator2
        options.setDeviceName("Pixel 9 API 33"); // задает имя устройства для запуска
        options.setChromedriverExecutable("/Users/evgeniy/Downloads/chromedriver-mac-arm64/chromedriver"); // задает путь к ChromeDriver
        options.setCapability("browserName", "Chrome"); // говорит Appium запускать не APK, а мобильный браузер Chrome

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); // подключает AndroidDriver к локальному Appium-серверу и запускает Chrome
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // задает неявное ожидание элементов

        //driver.get("http://google.com");
        //driver.findElement(By.name("q")).sendKeys("Make Vazowski");
        //driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.get("https://rahulshettyacademy.com/angularAppDemo/"); // открывает страницу в мобильном браузере
        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click(); // находит элемент и кликает по нему
        Thread.sleep(3000); // делает паузу для ожидания интерфейса
        driver.executeScript("window.scrollBy(0,1000)",""); // выполняет JavaScript в Chrome и прокручивает веб-страницу вниз на 1000 пикселей
        String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText(); // получает текст элемента
        Assert.assertEquals(text,"Devops"); // проверяет фактическое значение с ожидаемым
        Thread.sleep(3000); // делает паузу для ожидания интерфейса

    }
}
