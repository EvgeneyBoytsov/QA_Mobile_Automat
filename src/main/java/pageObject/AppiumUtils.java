package pageObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils { // объявляет класс AppiumUtils
    public AppiumDriverLocalService server; // объявляет поле/переменную типа локальный Appium-сервер
    public Double getFormatAmount (String amount) { // объявляет метод получения данных getFormatAmount
        return Double.parseDouble(amount.substring(1)); // возвращает результат метода
    }
    public void waitElement (WebElement element, AppiumDriver driver) { // объявляет метод waitElement
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // создает явное ожидание: Selenium будет ждать условие до 15 секунд
        wait.until(ExpectedConditions.attributeContains(element, "text", "Cart")); // ждет пока атрибут text у элемента начнет содержать Cart
    }
    public List<HashMap<String, String>> getJsonData (String jsonData) throws IOException { // объявляет метод получения данных getJsonData
        String jsonContent = FileUtils.readFileToString(new File(jsonData), StandardCharsets.UTF_8); // читает JSON-файл по переданному пути в одну строку в кодировке UTF-8
        ObjectMapper mapper = new ObjectMapper(); // создает Jackson ObjectMapper, который умеет преобразовывать JSON в Java-объекты
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() { // преобразует JSON-массив в List<HashMap<String, String>>, где каждая HashMap — один набор тестовых данных
        }); // закрывает TypeReference и завершает чтение JSON
    }
    public AppiumDriverLocalService StartServer(String IpAddress, int PortNumber) { // объявляет метод StartServer
        server = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium/build//lib//main.js//")) // указывает путь к установленному Appium main.js для запуска сервера из кода
                .withIPAddress(IpAddress).usingPort(PortNumber).build(); // собирает локальный Appium-сервер с IP и портом из настроек
        server.start(); // запускает Appium-сервер
        return server; // возвращает результат метода
    }

    public String getScreenShotPath(String testCaseName, AppiumDriver driver) throws IOException { // объявляет метод получения данных getScreenShotPath
       File screenshot = driver.getScreenshotAs(OutputType.FILE); // делает скриншот экрана
       String destinationFile = System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png"; // строит путь к скриншоту внутри проекта: user.dir — папка запуска тестов/Maven
       FileUtils.copyFile(screenshot, new File(destinationFile)); // копирует скриншот в файл отчета
       return destinationFile; // возвращает результат метода
    }
}
