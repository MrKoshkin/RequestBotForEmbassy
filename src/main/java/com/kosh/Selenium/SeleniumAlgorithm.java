package com.kosh.Selenium;

import com.kosh.Configuration.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumAlgorithm {
    public static final Logger logger = LogManager.getLogger(SeleniumAlgorithm.class);
    protected static WebDriver webDriver;
    public static RegistrationPage registrationPage;

    public SeleniumAlgorithm() {

        try {

            setup();

            webDriver.get(Configuration.LOGIN_PAGE);

            registrationPage.inputLastName(Configuration.getLastName());

            registrationPage.inputFirstName(Configuration.getFirstName());

            registrationPage.inputMiddleName(Configuration.getMiddleName());

            registrationPage.inputPhone(Configuration.getPhone());

            registrationPage.inputEmail(Configuration.getEmail());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeWebDriver();
        }

    }


    private static void setup() {
        webDriver = WebDriverManager.chromedriver().create();

        webDriver.manage().window().maximize();    // Окно разворачивается на полный экран
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // Задержка на выполнение

        registrationPage = new RegistrationPage(webDriver);

        logger.info("Запуск вебдрайвера");

    }
    private static void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
//            logger.error(e.getMessage(), e);
        }
    }

    private static void closeWebDriver() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
                webDriver = null;
            }
        } catch (Exception e) {
        }
    }
}
