package com.kosh.Selenium;

import com.kosh.Application;
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

        webDriver = WebDriverManager.chromedriver().create();
        webDriver.get(Configuration.LOGIN_PAGE);
        registrationPage = new RegistrationPage(webDriver);

        webDriver.manage().window().maximize();    // Окно разворачивается на полный экран
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // Задержка на выполнение

        delay(500);
        registrationPage.inputLastName(Configuration.getLastName());

        delay(500);
        registrationPage.inputFirstName(Configuration.getFirstName());

        delay(500);
        registrationPage.inputMiddleName(Configuration.getMiddleName());

        delay(500);
        registrationPage.inputPhone(Configuration.getPhone());

        delay(500);
        registrationPage.inputEmail(Configuration.getEmail());


        closeWebDriver();

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
