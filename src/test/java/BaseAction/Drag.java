package BaseAction;

import BaseUtils.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Drag extends BaseTest { // объявляет класс Drag
    @Test // помечает метод как TestNG-тест

    public void DragDropTest() throws InterruptedException { // объявляет метод DragDropTest
        driver.findElement(AppiumBy.accessibilityId("Views")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click(); // находит элемент и кликает по нему

        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1")); // находит первую точку, которую тест будет перетаскивать

        Assert.assertNotNull(((RemoteWebElement) source).getId());
        driver.executeScript("mobile: dragGesture", ImmutableMap.of( // вызывает Appium mobile-команду перетаскивания элемента
                "elementId", ((RemoteWebElement) source).getId(), // передает внутренний id точки, которую нужно перетащить
                "endX", 620, // задает конечную X-координату на экране
                "endY", 560 )); // задает конечную Y-координату на экране
        Thread.sleep(3000); // делает паузу для ожидания интерфейса

        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText(); // получает текст элемента
        Assert.assertEquals(result, "Dropped!"); // проверяет фактическое значение с ожидаемым
    }
}
