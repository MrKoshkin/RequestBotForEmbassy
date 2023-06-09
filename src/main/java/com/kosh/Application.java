package com.kosh;


import com.kosh.Configuration.Configuration;
import com.kosh.Configuration.SettingsWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Application {
    public static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        Configuration.loadConfig();     // Загрузка конфигурации, если не первичный запуск

        SwingUtilities.invokeLater(SettingsWindow::new);
    }
}
