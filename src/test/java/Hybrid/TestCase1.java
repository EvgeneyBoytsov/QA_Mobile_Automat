package Hybrid;

import BaseUtils.BaseTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.FormPage;

import java.awt.*;
import java.io.IOException;


public class TestCase1 extends BaseTest { // объявляет класс TestCase1


    @Parameters ({"Name", "country"})
    @Test(groups = {"Smoke"}) // помечает метод как TestNG-тест
    public void FillForm (String name, String country) throws InterruptedException, IOException { // объявляет метод FillForm



        FormPage formPage = new FormPage(driver); // создает Page Object формы

        formPage.setNameField(name); // вводит имя в форму
        //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Make");
        //driver.hideKeyboard();//скрыть клаву

        formPage.setCountryField(country); // выбирает страну из параметра
        //driver.findElement(By.id("android:id/text1")).click();
        //driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Russian Federation\"));")); // скролл до того пока не появиться текст
        //driver.findElement(By.xpath("//android.widget.TextView[@text='Russian Federation']")).click();

        formPage.clickBtnLetsShop(); // нажимает кнопку перехода в каталог
        //driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        //test.addScreenCaptureFromBase64String(reportFile.toString());
    }
}
