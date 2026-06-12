package Hybrid;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Toast extends BaseTest { // объявляет класс Toast
    @Test // помечает метод как TestNG-тест
    public void toastMessageTest() throws InterruptedException { // объявляет метод toastMessageTest
        //driver.hideKeyboard();//скрыть клаву
        driver.findElement(By.id("android:id/text1")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"China\"));")); // скролл до того пока не появиться текст
        driver.findElement(By.xpath("//android.widget.TextView[@text='China']")).click(); // находит элемент и кликает по нему
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click(); // находит элемент и кликает по нему

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name"); // получает атрибут элемента
        Assert.assertEquals(toastMessage,"Please enter your nam"); // проверяет фактическое значение с ожидаемым
        Thread.sleep(3000); // делает паузу для ожидания интерфейса

        //Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
        //проверка того что сообщение не выводиться. Если 1 значит есть, если 0 то нет
    }
}
