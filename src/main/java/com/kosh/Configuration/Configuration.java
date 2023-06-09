package com.kosh.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class Configuration {
    public static final Logger logger = LogManager.getLogger(Configuration.class);
    private static final String CONFIG_FILE = "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "kosh" + File.separator + "config.properties";
    public static final String LOGIN_PAGE = "https://alma-ata.kdmid.ru/queue/visitor.aspx";
    public static final String DOWNLOAD_CHROME_DRIVER_URL = "https://chromedriver.chromium.org/downloads";
    private static String lastName;
    private static String firstName;
    private static String middleName;
    private static String phone;
    private static String email;
    private static String birthday;
    private static String address;

    protected static void saveConfig() {

        // Проверяем, существует ли файл
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    logger.info("Не удалось создать файл конфигурации");
                }
            } catch (IOException e) {
                logger.error("Ошибка при создании файла конфигурации: " + e.getMessage());
            }
        }

        // Сохраняем конфигурацию
        Properties properties = new Properties();
        properties.setProperty("lastName", lastName);
        properties.setProperty("firstName", firstName);
        properties.setProperty("middleName", middleName);
        properties.setProperty("phone", phone);
        properties.setProperty("email", email);
        properties.setProperty("dateOfBirth", birthday);
        properties.setProperty("address", address);

        try (OutputStream outputStream = new FileOutputStream(CONFIG_FILE)) {
            properties.store(outputStream, "Configuration");
            logger.info("Конфигурация успешно сохранена");
        } catch (IOException e) {
            logger.info("Ошибка сохранения конфигурации: " + e.getMessage());
        }
    }

    public static void loadConfig() {
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(inputStream);
            lastName = properties.getProperty("lastName");
            firstName = properties.getProperty("firstName");
            middleName = properties.getProperty("middleName");
            phone = properties.getProperty("phone");
            email = properties.getProperty("email");
            birthday = properties.getProperty("dateOfBirth");
            address = properties.getProperty("address");

            logger.info("Конфигурация успешно загружена");
        } catch (IOException e) {
            logger.info("Ошибка загрузки конфигурации: " + e.getMessage());
        }
    }

    public static String getLastName() {
        return lastName != null ? lastName : "";
    }

    public static void setLastName(String lastName) {
        Configuration.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName != null ? firstName : "";
    }

    public static void setFirstName(String firstName) {
        Configuration.firstName = firstName;
    }

    public static String getMiddleName() {
        return middleName != null ? middleName : "";
    }

    public static void setMiddleName(String middleName) {
        Configuration.middleName = middleName;
    }

    public static String getPhone() {
        return phone != null ? phone : "";
    }

    public static void setPhone(String phone) {
        Configuration.phone = phone;
    }

    public static String getEmail() {
        return email != null ? email : "";
    }

    public static void setEmail(String email) {
        Configuration.email = email;
    }

    public static String getBirthday() {
        return birthday != null ? birthday : "";
    }

    public static void setBirthday(String birthday) {
        Configuration.birthday = birthday;
    }

    public static String getAddress() {
        return address != null ? address : "";
    }

    public static void setAddress(String address) {
        Configuration.address = address;
    }


}
