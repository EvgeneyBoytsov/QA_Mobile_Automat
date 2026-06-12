package BaseAction;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongClick extends BaseTest { // объявляет класс LongClick
    @Test // помечает метод как TestNG-тест
    public void LongClickTest() { // объявляет метод LongClickTest
        driver.findElement(AppiumBy.accessibilityId("Views")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click(); // находит элемент и кликает по нему

       WebElement field =  driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']")); // находит пункт People Names, по которому нужно сделать long press
       LongPress(field); // выполняет долгое нажатие по элементу

      String text =  driver.findElement(By.id("android:id/title")).getText(); // получает текст элемента
      Assert.assertEquals(text,"Sample menu"); // проверяет фактическое значение с ожидаемым
      Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed()); // проверяет что условие истинно
    }
}
