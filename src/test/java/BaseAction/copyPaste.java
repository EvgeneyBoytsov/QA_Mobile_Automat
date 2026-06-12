package BaseAction;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class copyPaste extends BaseTest { // объявляет класс copyPaste
    @Test // помечает метод как TestNG-тест
    public void CopyPasteTest() { // объявляет метод CopyPasteTest
        driver.findElement(AppiumBy.accessibilityId("Preference")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click(); // находит элемент и кликает по нему
        driver.findElement(By.id("android:id/checkbox")).click(); // находит элемент и кликает по нему

        DeviceRotation landScape = new DeviceRotation(0, 0, 90); // поворот на 90 градусов
        driver.rotate(landScape); // поворачивает экран устройства

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click(); // находит элемент и кликает по нему

        String alert = driver.findElement(By.id("android:id/alertTitle")).getText(); // получает текст элемента
        Assert.assertEquals(alert, "WiFi settings"); // проверка названия попапа

        driver.setClipboardText("Evgeneye Boytsov"); // копирование текста

        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText()); // вставка текста
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); // клик на 1 элемент списка
    }
}
