package com.kosh.job.gmail;


import com.kosh.job.gmail.Configuration.SettingsWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            SettingsWindow form = new SettingsWindow();
//            form.setVisible(true);
        });
    }
}
