package Hybrid;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase2 extends BaseTest { // объявляет класс TestCase2
    @Test // помечает метод как TestNG-тест
    public void AddCart () throws InterruptedException { // объявляет метод AddCart
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Make"); // вводит текст в поле
        driver.hideKeyboard();//скрыть клаву
        driver.findElement(By.id("android:id/text1")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"China\"));")); // скролл до того пока не появиться текст
        driver.findElement(By.xpath("//android.widget.TextView[@text='China']")).click(); // находит элемент и кликает по нему
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click(); // находит элемент и кликает по нему

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));")); // скроллит до товара Jordan 6 Rings

        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size(); // получает количество найденных элементов

        for (int i = 0; i < productCount; i++) { // запускает цикл по данным
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText(); // получает текст элемента
                if (productName.equalsIgnoreCase("Jordan 6 Rings")) { // проверяет условие перед выполнением блока
                    driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click(); // находит элемент и кликает по нему
                }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click(); // находит элемент и кликает по нему

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // создает явное ожидание: Selenium будет ждать экран корзины до 5 секунд
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart")); // ждет пока toolbar_title получит текст Cart, то есть корзина загрузилась
        String productName = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText(); // получает текст элемента
        Assert.assertEquals(productName, "Jordan 6 Ring"); // проверяет фактическое значение с ожидаемым
    }
}
