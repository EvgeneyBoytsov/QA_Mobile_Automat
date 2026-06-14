package Hybrid;

import BaseUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.FormPage;
import pageObject.ProductCatalog;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TestCase13 extends BaseTest { // объявляет класс TestCase13

    @BeforeMethod (alwaysRun = true)
    // запускает метод перед каждым тестом, запусти этот configuration method даже если есть зависимости/группы, из-за которых он обычно мог бы быть пропущен.
    public void preCondition() { // объявляет метод preCondition
        formPage.setActivity(); // перезапускает приложение перед каждым тестом
    }

    @Test(dataProvider = "getData") // помечает метод как TestNG-тест
    public void FillForm (HashMap<String, String> input) throws InterruptedException { // объявляет метод FillForm
        FormPage formPage = new FormPage(driver); // создает Page Object формы

        formPage.setNameField(input.get("name")); // вводит имя из тестовых данных
        formPage.setCountryField(input.get("country")); // выбирает страну из тестовых данных

        formPage.clickBtnLetsShop(); // нажимает кнопку перехода в каталог
        Thread.sleep(3000); // делает паузу для ожидания интерфейса
    }
    @Test(dataProvider = "getData", groups = {"Smoke"}) // помечает метод как TestNG-тест
    public void CheckSum (HashMap<String, String> input) throws InterruptedException { // объявляет метод CheckSum
        formPage.setNameField(input.get("name")); // вводит имя из тестовых данных
        formPage.setCountryField(input.get("country")); // выбирает страну из тестовых данных
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
    }

    @DataProvider // помечает метод как источник тестовых данных
    public Object[][] getData() throws IOException { // объявляет метод получения данных getData
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//testData//data.json");
        // читает data.json из корня проекта: user.dir — это папка, из которой запущены тесты/Maven
        return new Object[][] { // превращает список HashMap в массив данных, который TestNG передаст в тесты
                {data.get(0)}, // передает первый набор name/country в параметр input
                {data.get(1)} // передает второй набор name/country в параметр input
        };
    }
}
