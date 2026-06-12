package BaseAction;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Swipe extends BaseTest { // объявляет класс Swipe
    @Test // помечает метод как TestNG-тест
    public void SwipeTest()  { // объявляет метод SwipeTest
        driver.findElement(AppiumBy.accessibilityId("Views")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click(); // находит элемент и кликает по нему
        driver.findElement(By.xpath("//android.widget.TextView[@text='1. Photos']")).click(); // находит элемент и кликает по нему

        WebElement firstImage = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")); // находит первую картинку в галерее, по которой будет выполняться свайп

        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true"); // проверяет фактическое значение с ожидаемым

        SwipeLeft(firstImage, "left"); // свайпает первую картинку влево

        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false"); // проверяет фактическое значение с ожидаемым
    }
}
