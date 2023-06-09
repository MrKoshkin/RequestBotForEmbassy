package com.kosh;


import com.kosh.Configuration.Configuration;
import com.kosh.Configuration.SettingsWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;

public class Application {
    public static final Logger logger = LogManager.getLogger(Application.class);
    private static String log4jConfigFile = "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "log4j2.xml";

    public static void main(String[] args) {

        System.setProperty("log4j.configurationFile", log4jConfigFile);
        Configuration.loadConfig();     // Загрузка конфигурации, если не первичный запуск

        SwingUtilities.invokeLater(SettingsWindow::new);
    }
}
