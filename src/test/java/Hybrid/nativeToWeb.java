package Hybrid;

import BaseUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class nativeToWeb extends BaseTest { // объявляет класс nativeToWeb
    @Test // помечает метод как TestNG-тест
    public void WebViewTest() throws InterruptedException { // объявляет метод WebViewTest
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Make"); // вводит текст в поле
        driver.hideKeyboard();//скрыть клаву
        driver.findElement(By.id("android:id/text1")).click(); // находит элемент и кликает по нему
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"China\"));")); // скролл до того пока не появиться текст
        driver.findElement(By.xpath("//android.widget.TextView[@text='China']")).click(); // находит элемент и кликает по нему
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click(); // находит элемент и кликает по нему

        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click(); // находит элемент и кликает по нему
        //driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART']) [1]")).click();
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click(); // находит элемент и кликает по нему

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click(); // находит элемент и кликает по нему
        Thread.sleep(2000); // делает паузу для ожидания интерфейса
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // создает явное ожидание: Selenium будет ждать экран корзины до 15 секунд
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart")); // ждет пока toolbar_title получит текст Cart, то есть корзина загрузилась

        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")); // собирает все элементы с ценами товаров в корзине
        int count = productPrices.size(); // получает количество найденных элементов
        double sum = 0; // хранит общую сумму цен товаров из корзины
        for (WebElement productPrice : productPrices) { // запускает цикл по данным
            String amount = productPrice.getText(); // получает текст элемента
            double price = Double.parseDouble(amount.substring(1)); // преобразует строку цены в число
            sum = price + sum; // добавляет цену товара к общей сумме
        }
        String totalSum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText(); // получает текст элемента
        Double formatTotalSum = getFormatAmount(totalSum); // убирает знак валюты из итоговой суммы и переводит ее в число
        Assert.assertEquals(sum, formatTotalSum); // проверяет фактическое значение с ожидаемым

        WebElement field = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")); // находит кнопку Terms, которую нужно открыть долгим нажатием
        LongPress(field); // выполняет долгое нажатие по элементу
        driver.findElement(By.id("android:id/button1")).click(); // находит элемент и кликает по нему

        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click(); // находит элемент и кликает по нему
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click(); // находит элемент и кликает по нему
        Thread.sleep(5000); // делает паузу для ожидания интерфейса

        Set<String> contexts = driver.getContextHandles(); // получает контексты приложения: NATIVE_APP для Android-экрана и WEBVIEW для встроенного браузера
        for (String contextsName: contexts) { // проходит по всем доступным контекстам
            System.out.println(contextsName); // печатает имя контекста, чтобы увидеть точное название WEBVIEW
        }

        driver.context("WEBVIEW_com.androidsample.generalstore"); // переключает драйвер из native-экрана в WebView, чтобы искать HTML-элементы как в браузере
        driver.findElement(By.name("q")).sendKeys("Make Vazowski"); // вводит текст в поле
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER); // вводит текст в поле
        Thread.sleep(5000); // делает паузу для ожидания интерфейса
        driver.pressKey(new KeyEvent((AndroidKey.BACK))); // нажимает системную кнопку Back
        driver.context("NATIVE_APP"); // возвращает драйвер обратно в native-контекст Android-приложения
    }
}
