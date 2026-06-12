package BaseUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentReporterNG { // объявляет класс ExtentReporterNG
    static ExtentReports extent; // объявляет поле/переменную типа объект отчета ExtentReports
    static Path reportFile; // объявляет поле/переменную типа путь к файлу
    public static ExtentReports ConfigReport () throws IOException { // объявляет метод ConfigReport

        Path reportDir = Paths.get(System.getProperty("user.dir"), "Reports"); // собирает путь к папке Reports внутри проекта: user.dir — папка запуска тестов/Maven
        Files.createDirectories(reportDir); // создает папку Reports, если ее еще нет

        reportFile = reportDir.resolve("index.html"); // собирает путь к файлу отчета

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportFile.toString()); // создает HTML-репортер ExtentReports
        reporter.config().setReportName("Result Test_1"); // задает название отчета
        reporter.config().setDocumentTitle("Test Results"); // задает заголовок HTML-отчета

        extent = new ExtentReports(); // создает объект отчета ExtentReports
        extent.attachReporter(reporter); // подключает репортер к ExtentReports
        extent.setSystemInfo("Tester", "Evgeniy"); // добавляет информацию о тестировщике
        return  extent; // возвращает результат метода
    }
}
