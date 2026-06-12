package BaseAction;

import BaseUtils.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class jumpActivity extends BaseTest { // объявляет класс jumpActivity
    @Test // помечает метод как TestNG-тест
    public void goToActivity() { // объявляет метод goToActivity

        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies")); // запускает конкретную Android Activity напрямую через intent, без кликов по предыдущим экранам
        // запуск с определенного экрана
        //Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
        //driver.currentActivity();


        driver.findElement(By.id("android:id/checkbox")).click(); // находит элемент и кликает по нему
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click(); // находит элемент и кликает по нему

        String alert = driver.findElement(By.id("android:id/alertTitle")).getText(); // получает текст элемента
        Assert.assertEquals(alert, "WiFi settings"); // проверка названия попапа

        driver.setClipboardText("Evgeneye Boytsov"); // копирует текст в буфер обмена устройства

        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText()); // вводит текст в поле
        driver.pressKey(new KeyEvent(AndroidKey.ENTER)); // клик на кнопку энтер на клаве
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); // находит элемент и кликает по нему

        driver.pressKey(new KeyEvent(AndroidKey.BACK)); // клик систменой кнопки назад
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // клик систменой кнопки хоум
    }

}
