package com.kosh.job.gmail;

import com.kosh.job.gmail.Configuration.SettingsWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(SettingsWindow::new);

    }
}
