package com.kosh.job.gmail.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class ConfigManager {
    public static final Logger logger = LogManager.getLogger(ConfigManager.class);
    public static final String CONFIG_FILE = "src/main/java/com.kosh.job.gmail/config.properties";
    public static final String LOGIN_PAGE = "https://alma-ata.kdmid.ru/queue/Default.aspx";
    public static final String DOWNLOAD_CHROME_DRIVER_URL = "https://chromedriver.chromium.org/downloads";
    private static String firstName;
    private static String lastName;
    private static String middleName;
    private static String phone;
    private static String email;
    private static String birthday;
    private static String address;

    protected static void saveConfig() {
        Properties properties = new Properties();

        properties.setProperty("firstName", firstName);
        properties.setProperty("lastName", lastName);
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
            firstName = properties.getProperty("firstName");
            lastName = properties.getProperty("lastName");
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

    public static String getFirstName() {
        return firstName != null ? firstName : "";
    }

    public static void setFirstName(String firstName) {
        ConfigManager.firstName = firstName;
    }

    public static String getLastName() {
        return lastName != null ? lastName : "";
    }

    public static void setLastName(String lastName) {
        ConfigManager.lastName = lastName;
    }

    public static String getMiddleName() {
        return middleName != null ? middleName : "";
    }

    public static void setMiddleName(String middleName) {
        ConfigManager.middleName = middleName;
    }

    public static String getPhone() {
        return phone != null ? phone : "";
    }

    public static void setPhone(String phone) {
        ConfigManager.phone = phone;
    }

    public static String getEmail() {
        return email != null ? email : "";
    }

    public static void setEmail(String email) {
        ConfigManager.email = email;
    }

    public static String getBirthday() {
        return birthday != null ? birthday : "";
    }

    public static void setBirthday(String birthday) {
        ConfigManager.birthday = birthday;
    }

    public static String getAddress() {
        return birthday != null ? birthday : "";
    }

    public static void setAddress(String address) {
        ConfigManager.address = address;
    }


}
