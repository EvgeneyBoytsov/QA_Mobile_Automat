package Hybrid;

import BaseUtils.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.ProductCatalog;



public class TestCase3 extends BaseTest { // объявляет класс TestCase3
    @Test // помечает метод как TestNG-тест
    public void CheckSum () throws InterruptedException { // объявляет метод CheckSum
        //FormPage formPage = new FormPage(driver);
        //ProductCatalog productCatalog = new ProductCatalog(driver);

        formPage.setNameField("Make"); // вводит имя в форму
        formPage.setCountryField("China"); // выбирает страну China
        ProductCatalog productCatalog = formPage.clickBtnLetsShop(); // переходит в каталог товаров

        productCatalog.getAddToCartBtn(0); // добавляет товар в корзину
        productCatalog.getAddToCartBtn(0); // добавляет товар в корзину

        CartPage cartPage = productCatalog.clickCartBtn(); // открывает корзину и создает CartPage
        double sum = cartPage.getSum(); // получает сумму товаров, посчитанную тестом по отдельным ценам
        double totalSum = cartPage.getTotalSum(); // получает итоговую сумму, которую показывает приложение в корзине
        Assert.assertEquals(sum,totalSum); // проверяет фактическое значение с ожидаемым
        cartPage.clickProceed(); // открывает и подтверждает условия
        cartPage.getCheckBox(); // отмечает чекбокс согласия
        cartPage.clickBtnProceed(); // переходит к следующему шагу покупки




        //String totalSum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        //Double formatTotalSum = getFormatAmount (totalSum);


        //WebElement field =  driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        //LongPress(field);
        //driver.findElement(By.id("android:id/button1")).click();

        //driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        //driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

    }
}
