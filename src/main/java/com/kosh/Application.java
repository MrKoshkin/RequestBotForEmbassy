package com.kosh;


import com.kosh.Configuration.Configuration;
import com.kosh.Configuration.SettingsWindow;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;

public class Application {
    public static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static String log4jConfigFile = "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "kosh" + File.separator + "log4j.properties";

    public static void main(String[] args) {

//        System.setProperty("log4j.configurationFile", log4jConfigFile);
        PropertyConfigurator.configure(log4jConfigFile);

        Configuration.loadConfig();     // Загрузка конфигурации, если не первичный запуск

        SwingUtilities.invokeLater(SettingsWindow::new);
    }
}
