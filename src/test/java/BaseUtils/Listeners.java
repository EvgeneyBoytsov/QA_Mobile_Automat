package BaseUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestListener;
import pageObject.AppiumUtils;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener { // объявляет класс Listeners
    ExtentTest test; // объявляет поле/переменную типа текущий тест ExtentReports
    ExtentReports extent; // объявляет поле/переменную типа объект отчета ExtentReports
    AppiumDriver driver; // объявляет поле объекта страницы

    public Listeners() { // объявляет конструктор Listeners
        try { // начинает блок обработки исключения
            extent = ExtentReporterNG.ConfigReport(); // инициализирует ExtentReports
        } catch (IOException e) { // обрабатывает пойманное исключение
            throw new RuntimeException("Failed to initialize ExtentReports", e); // пробрасывает ошибку выполнения
        }
    }

    @Override // переопределяет метод интерфейса
    public void onTestStart(org.testng.ITestResult result) { // объявляет метод onTestStart
        test = extent.createTest(result.getMethod().getMethodName()); // создает запись теста в отчете
    }

    @Override // переопределяет метод интерфейса
    public void onTestSuccess(org.testng.ITestResult result) { // объявляет метод onTestSuccess
        test.log(Status.PASS, "Test Passed"); // логирует успешный тест в отчет
    }

    @Override // переопределяет метод интерфейса
    public void onTestFailure(org.testng.ITestResult result) { // объявляет метод onTestFailure
        test.fail(result.getThrowable()); // записывает ошибку теста в отчет

        try { // начинает блок обработки исключения
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver") // через reflection находит public-поле driver в классе упавшего теста
                    .get(result.getInstance()); // достает конкретный driver из объекта теста, чтобы сделать скриншот
        } catch (Exception e1) { // обрабатывает пойманное исключение
            e1.printStackTrace(); // печатает стек ошибки в консоль
        }
        try { // начинает блок обработки исключения
            String path = getScreenShotPath(result.getMethod().getMethodName(), driver); // делает скриншот упавшего теста и получает путь к PNG-файлу
            String title = result.getMethod().getMethodName(); // использует имя метода теста как подпись вложения в отчете
            test.addVideoFromPath(path, title); // прикрепляет файл к ExtentReports по пути; здесь используется метод video, хотя файл создается как PNG
        } catch (IOException e) { // обрабатывает пойманное исключение
            e.printStackTrace(); // печатает стек ошибки в консоль
        }
    }

    @Override // переопределяет метод интерфейса
    public void onTestSkipped(org.testng.ITestResult result) { // объявляет метод onTestSkipped
        test.log(Status.SKIP, "Test Skipped"); // логирует пропущенный тест в отчет
    }

    @Override // переопределяет метод интерфейса
    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result) { // объявляет метод onTestFailedButWithinSuccessPercentage
        // Not implemented for this example
    }

    @Override // переопределяет метод интерфейса
    public void onStart(org.testng.ITestContext context) { // объявляет метод onStart
        // Not implemented for this example
    }

    @Override // переопределяет метод интерфейса
    public void onFinish(org.testng.ITestContext context) { // объявляет метод onFinish
        extent.flush(); // сохраняет итоговый отчет
    }

}
