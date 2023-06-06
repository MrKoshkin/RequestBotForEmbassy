package com.kosh.job.gmail;


import com.kosh.job.gmail.Configuration.SettingsWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Application {
    public static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SettingsWindow window = new SettingsWindow();
        });
    }
}
