package BaseAction;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;


    public class Scroll extends BaseTest { // объявляет класс Scroll
        @Test // помечает метод как TestNG-тест
        public void ScrollTest() throws InterruptedException { // объявляет метод ScrollTest
            driver.findElement(AppiumBy.accessibilityId("Views")).click(); // находит элемент и кликает по нему
            ScrollToEnd(); // вызывает helper, который скроллит список Views до конца
        }
    }
