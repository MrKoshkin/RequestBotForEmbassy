package com.kosh.job.gmail;


import com.kosh.job.gmail.Configuration.SettingsWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

//        GUI gui = new GUI();
//        gui.start();
//
//        try {
//            gui.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        SettingsWindow form = new SettingsWindow();
        form.setVisible(true);

        System.out.println("test");
        System.out.println("text");

    }
}

class GUI extends Thread {
    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            SettingsWindow form = new SettingsWindow();
            form.setVisible(true);
        });
    }
}
