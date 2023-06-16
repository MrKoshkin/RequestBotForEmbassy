package com.kosh.Selenium;

import com.kosh.Configuration.Configuration;
import com.kosh.captcha.CaptchaManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class SeleniumAlgorithm extends Thread {
    public static final Logger logger = LogManager.getLogger(SeleniumAlgorithm.class);
    protected static WebDriver webDriver;
    public static WebElements webElements;
    private final String apiKey = "c8357131c9dee0802124c380f205d263";

    @Override
    public void run() {
        Thread current = Thread.currentThread();

        while (!current.isInterrupted()) {

            try {

                setup();

                webDriver.get(Configuration.LOGIN_PAGE);

                // Заполнение регистрационной формы
                webElements.inputLastName(Configuration.getLastName());
                webElements.inputFirstName(Configuration.getFirstName());
                webElements.inputMiddleName(Configuration.getMiddleName());
                webElements.inputPhone(Configuration.getPhone());
                webElements.inputEmail(Configuration.getEmail());
                webElements.selectDayOfBirth(Configuration.getDayOfBirthday());
                webElements.selectMonthOfBirth(Configuration.getMonthOfBirthday());
                webElements.inputYearOfBirth(Configuration.getYearOfBirthday());
                webElements.selectAddress(Configuration.getAddress());

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
                if (captchaResult == null) {
                    closeWebDriver();
                    continue;
                }
                webElements.inputCaptcha(captchaResult);

                // Переходим на следующую страницу
                webElements.nextPage();
                delay(1000);

                // Выбираем тип паспорта
                webElements.selectApplicationCategory();
                delay(1000);

                // Выбирает категорию и переходим на следующую страницу
                webElements.selectAdultPassport();
                webElements.nextPage2();
                delay(1000);

                // Выбираем запись на прием
                webElements.selectBiometricPassport();
                webElements.makeAppointment();
                delay(10000);

            } catch (Exception e) {
                logger.fatal("Ошибка работы алгоритма: " + e.getMessage());
                current.interrupt();
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

            // Скрин полный
            File fullCaptchaFile = new File("src/main/java/com/kosh/captcha/captchaFULL.png");
            ImageIO.write(fullScreenImage, "png", fullCaptchaFile);

            // Получение координат и размеров элемента с капчей
//            Point elementLocation = captchaElement.getLocation();     // Неправильно определяются координаты
//            int elementWidth = captchaElement.getSize().getWidth();
//            int elementHeight = captchaElement.getSize().getHeight();
            Point elementLocation = new Point(780, 740);    // 780, 740
            int elementWidth = 160;     // 160
            int elementHeight = 43;     // 43


//             Обрезка изображения, чтобы получить только капчу
            BufferedImage captchaImage = fullScreenImage.getSubimage(
                    elementLocation.getX(), elementLocation.getY(), elementWidth, elementHeight);


//            // Получение размеров экрана
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            int screenWidth = screenSize.width;
//            int screenHeight = screenSize.height;
//
//            // Получение размеров элемента с капчей
//            int elementWidth = captchaElement.getSize().getWidth();
//            int elementHeight = captchaElement.getSize().getHeight();
//
//            // Вычисление процентного положения капчи относительно максимальной ширины и высоты экрана
//            double elementXPercentage = (double) captchaElement.getLocation().getX() / screenWidth * 100;
//            double elementYPercentage = (double) captchaElement.getLocation().getY() / screenHeight * 100;
//            double elementWidthPercentage = (double) elementWidth / screenWidth * 100;
//            double elementHeightPercentage = (double) elementHeight / screenHeight * 100;
//
//            // Вычисление координат для обрезки скриншота на основе процентного положения капчи
//            int captchaX = (int) (fullScreenImage.getWidth() * elementXPercentage / 100);
//            int captchaY = (int) (fullScreenImage.getHeight() * elementYPercentage / 100);
//            int captchaWidth = (int) (fullScreenImage.getWidth() * elementWidthPercentage / 100);
//            int captchaHeight = (int) (fullScreenImage.getHeight() * elementHeightPercentage / 100);
//
//            // Обрезка изображения, чтобы получить только капчу
//            BufferedImage captchaImage = fullScreenImage.getSubimage(captchaX, captchaY, captchaWidth, captchaHeight);


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
//        closeWebDriver();
        return base64Image;
    }

    // (НЕ РАБОТАЕТ. Капча обновляется при переходе по url картинки -_-)
//    private static String getCaptchaImage() {
//        WebElement captchaElement = webDriver.findElement(By.id("ctl00_MainContent_imgSecNum"));
//        String captchaImageUrl = captchaElement.getAttribute("src");
//
//        String base64Image = null;
//
//        try {
//            // Загрузка изображения в объект BufferedImage
//            URL url = new URL(captchaImageUrl);
//            BufferedImage captchaImage = ImageIO.read(url);
//
//            // Сохраните изображение капчи в файл
//            File captchaFile = new File("captcha.png");
//            ImageIO.write(captchaImage, "png", captchaFile);
//
//            // Преобразование в base64
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(captchaImage, "png", baos);
//            byte[] imageBytes = baos.toByteArray();
//
//            base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//        } catch (IOException e) {
//            logger.error("Ошибка сохранения капчи" + e.getMessage());
//        }
//
//        return base64Image;
//    }


    private static void setup() {
        webDriver = WebDriverManager.chromedriver().create();

        webDriver.manage().window().maximize();    // Окно разворачивается на полный экран
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // Задержка на выполнение

        webElements = new WebElements(webDriver);

        logger.debug("Запуск вебдрайвера");

    }

    private static void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Ошибка при засыпании треда: " + e.getMessage());
        }
    }

    public static void closeWebDriver() {
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
