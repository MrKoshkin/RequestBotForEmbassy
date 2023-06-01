package com.kosh.job.gmail.Configuration;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


public class SettingsWindow extends JFrame {

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JDatePickerImpl birthdayPicker;

    public SettingsWindow() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));

        // Фамилия
        JLabel lastNameLabel = new JLabel("Фамилия:");
        lastNameField = new JTextField();
        lastNameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        add(lastNameLabel);
        add(lastNameField);

        // Имя
        JLabel firstNameLabel = new JLabel("Имя:");
        firstNameField = new JTextField("Сергей");
        add(firstNameLabel);
        add(firstNameField);

        // Отчество
        JLabel middleNameLabel = new JLabel("Отчество:");
        middleNameField = new JTextField();
        add(middleNameLabel);
        add(middleNameField);

        // Телефон
        JLabel phoneLabel = new JLabel("Телефон:");
        phoneField = new JTextField();
        add(phoneLabel);
        add(phoneField);

        // Электронная почта
        JLabel emailLabel = new JLabel("Электронная почта:");
        emailField = new JTextField();
        add(emailLabel);
        add(emailField);

        // Дата рождения
        JLabel birthdayLabel = new JLabel("Дата рождения:");
        UtilDateModel dateModel = new UtilDateModel();
        Properties properties = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, properties);
        birthdayPicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        add(birthdayLabel);
        add(birthdayPicker);

        // Кнопка "Зарегистрироваться"
        JButton registerButton = new JButton("Зарегистрироваться");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получение значений из полей
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                Date birthday = (Date) birthdayPicker.getModel().getValue();

                // Вывод информации
                System.out.println("Фамилия: " + lastName);
                System.out.println("Имя: " + firstName);
                System.out.println("Отчество: " + middleName);
                System.out.println("Телефон: " + phone);
                System.out.println("Электронная почта: " + email);
                System.out.println("Дата рождения: " + birthday);
            }
        });
        add(registerButton);

        setVisible(true);
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private final String datePattern = "dd.MM.yyyy";
    private final java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws java.text.ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws java.text.ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }
}