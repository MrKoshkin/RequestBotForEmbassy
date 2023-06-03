package com.kosh.job.gmail.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


public class SettingsWindow extends JFrame {

    public static final Logger logger = LogManager.getLogger(SettingsWindow.class);

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JDatePickerImpl datePicker;

    public SettingsWindow() {
        setTitle("Запись в посольство");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание компонентов
        JLabel lastNameLabel = new JLabel("Фамилия:");
        JLabel firstNameLabel = new JLabel("Имя:");
        JLabel middleNameLabel = new JLabel("Отчество:");
        JLabel phoneLabel = new JLabel("Телефон:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel birthDateLabel = new JLabel("Дата рождения:");
        JLabel addressLabel = new JLabel("Обращение");

        lastNameField = new JTextField(20);
        firstNameField = new JTextField(20);
        middleNameField = new JTextField(20);
        phoneField = new JTextField(20);
        emailField = new JTextField(20);

        UtilDateModel model = new UtilDateModel(new Date());
        Properties p = new Properties();
        JDatePanelImpl datePickerPanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePickerPanel, new DateLabelFormatter());

        JComboBox<String> addressBox = new JComboBox<>(new String[]{"Уважаемый", "Уважаемая"});

        // Установка менеджера компоновки GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets.set(10, 30, 10, 30);

        // Добавление компонентов в столбец 1
        add(lastNameLabel, constraints);
        constraints.gridy = 1;
        add(firstNameLabel, constraints);
        constraints.gridy = 2;
        add(middleNameLabel, constraints);
        constraints.gridy = 3;
        add(phoneLabel, constraints);
        constraints.gridy = 4;
        add(emailLabel, constraints);
        constraints.gridy = 5;
        add(birthDateLabel, constraints);
        constraints.gridy = 6;
        add(addressLabel,constraints);

        // Добавление компонентов в столбец 2
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(lastNameField, constraints);
        constraints.gridy = 1;
        add(firstNameField, constraints);
        constraints.gridy = 2;
        add(middleNameField, constraints);
        constraints.gridy = 3;
        add(phoneField, constraints);
        constraints.gridy = 4;
        add(emailField, constraints);
        constraints.gridy = 5;
        add(datePicker, constraints);
        constraints.gridy = 6;
        add(addressBox,constraints);

        pack(); // Подгон размера окна под размещенные элементы
        setLocationRelativeTo(null);
    }


    class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final String datePattern = "dd.MM.yyyy";
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
}

