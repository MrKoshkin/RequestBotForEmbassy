package com.kosh.Selenium;

import com.kosh.Configuration.Configuration;
import com.kosh.captcha.CaptchaManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class SeleniumAlgorithm {
    public static final Logger logger = LogManager.getLogger(SeleniumAlgorithm.class);
    protected static WebDriver webDriver;
    public static RegistrationPage registrationPage;
    private final String apiKey = "c8357131c9dee0802124c380f205d263";

    public SeleniumAlgorithm() {

        while (true) {

            try {

                setup();

                webDriver.get(Configuration.LOGIN_PAGE);

                registrationPage.inputLastName(Configuration.getLastName());

                registrationPage.inputFirstName(Configuration.getFirstName());

                registrationPage.inputMiddleName(Configuration.getMiddleName());

                registrationPage.inputPhone(Configuration.getPhone());

                registrationPage.inputEmail(Configuration.getEmail());

                registrationPage.selectDayOfBirth(Configuration.getDayOfBirthday());

                registrationPage.selectMonthOfBirth(Configuration.getMonthOfBirthday());

                registrationPage.inputYearOfBirth(Configuration.getYearOfBirthday());

                registrationPage.selectAddress(Configuration.getAddress());

                // Читаем капчу
                String base64Image = getCaptchaScreenshot();
                if (base64Image == null) {
                    closeWebDriver();
                    continue;
                }

                // Решаем капчу
                CaptchaManager captchaManager = new CaptchaManager(apiKey);
                String captchaId = captchaManager.sendCaptcha(base64Image);
                if (captchaId == null) {
                    closeWebDriver();
                    continue;
                }

                String captchaResult = captchaManager.getCaptcha(captchaId);


                registrationPage.inputCaptcha(captchaResult);

                registrationPage.nextPage();

                delay(10000);


            } catch (Exception e) {
                logger.fatal("Ошибка работы алгоритма: "+ e.getMessage());
            } finally {
                closeWebDriver();
            }
        }

    }

    private static String getCaptchaScreenshot() {
        // Получение элемента, содержащий капчу
        WebElement captchaElement = webDriver.findElement(By.id("ctl00_MainContent_imgSecNum"));

        // Получение скриншота всей страницы
        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        String base64Image = null;

        try {
            // Загрузка скриншота в объект BufferedImage
            BufferedImage fullScreenImage = ImageIO.read(screenshotFile);

            // Получение координат и размеров элемента с капчей
//            Point elementLocation = captchaElement.getLocation();     // Не правильно определяются координаты
//            int elementWidth = captchaElement.getSize().getWidth();
//            int elementHeight = captchaElement.getSize().getHeight();
            Point elementLocation = new Point(780, 740);
            int elementWidth = 160;
            int elementHeight = 43;

            // Обрезка изображения, чтобы получить только капчу
            BufferedImage captchaImage = fullScreenImage.getSubimage(
                    (int) elementLocation.getX(), (int) elementLocation.getY(), elementWidth, elementHeight);

            // Сохраните изображение капчи в файл
            File captchaFile = new File("src/main/java/com/kosh/captcha/captcha.png");
            ImageIO.write(captchaImage, "png", captchaFile);

            // Преобразование в base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(captchaImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            base64Image = Base64.getEncoder().encodeToString(imageBytes);

        } catch (IOException e) {
            logger.error("Ошибка сохранения скриншота капчи " + e.getMessage());
        }
        return base64Image;
    }

    // (НЕ РАБОТАЕТ. Капча обновление при переходе по url картинки -_-)
    private static String getCaptchaImage() {
        WebElement captchaElement = webDriver.findElement(By.id("ctl00_MainContent_imgSecNum"));
        String captchaImageUrl = captchaElement.getAttribute("src");

        String base64Image = null;

        try {
            // Загрузка изображения в объект BufferedImage
            URL url = new URL(captchaImageUrl);
            BufferedImage captchaImage = ImageIO.read(url);

            // Сохраните изображение капчи в файл
            File captchaFile = new File("captcha.png");
            ImageIO.write(captchaImage, "png", captchaFile);

            // Преобразование в base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(captchaImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            base64Image = Base64.getEncoder().encodeToString(imageBytes);

        } catch (IOException e) {
            logger.error("Ошибка сохранения капчи" + e.getMessage());
        }

        return base64Image;
    }


    private static void setup() {
        webDriver = WebDriverManager.chromedriver().create();

        webDriver.manage().window().maximize();    // Окно разворачивается на полный экран
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // Задержка на выполнение

        registrationPage = new RegistrationPage(webDriver);

        logger.debug("Запуск вебдрайвера");

    }

    private static void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Ошибка при засыпании треда: " + e.getMessage());
        }
    }

    private static void closeWebDriver() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
                webDriver = null;
            }
            logger.debug("Вебдрайвер успешно закрыт");
        } catch (Exception e) {
            logger.error("Ошибка при закрытии вебдрайвера: " + e.getMessage());
        }
    }
}
