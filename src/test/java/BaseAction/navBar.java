package BaseAction;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class navBar extends BaseTest { // объявляет класс navBar
    @Test // помечает метод как TestNG-тест
    public void NavBarClickTest() { // объявляет метод NavBarClickTest
        driver.findElement(AppiumBy.accessibilityId("Preference")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click(); // находит элемент и кликает по нему
        driver.findElement(By.id("android:id/checkbox")).click(); // находит элемент и кликает по нему

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click(); // находит элемент и кликает по нему

        String alert = driver.findElement(By.id("android:id/alertTitle")).getText(); // получает текст элемента
        Assert.assertEquals(alert, "WiFi settings"); // проверка названия попапа

        driver.setClipboardText("Evgeneye Boytsov"); // копирует текст в буфер обмена устройства

        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText()); // вводит текст в поле
        driver.pressKey(new KeyEvent(AndroidKey.ENTER)); // нажимает Enter на Android-клавиатуре
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); // находит элемент и кликает по нему

        driver.pressKey(new KeyEvent(AndroidKey.BACK)); // нажимает системную кнопку Back
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // нажимает системную кнопку Home
    }
}
