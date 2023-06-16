package com.kosh.Configuration;

import com.kosh.Application;
import com.kosh.Selenium.SeleniumAlgorithm;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


public class SettingsWindow extends JFrame {

    public static final Logger logger = LoggerFactory.getLogger(Application.class);

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JDatePickerImpl datePicker;
    private JDatePanelImpl datePickerPanel;
    private JButton startButton;
    private JButton finishButton;
    private JComboBox<String> addressBox;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private Thread seleniumThread;

    public SettingsWindow() {
        setTitle("Запись в посольство");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание компонентов
        JLabel lastNameLabel = new JLabel("Фамилия: *");
        JLabel firstNameLabel = new JLabel("Имя: *");
        JLabel middleNameLabel = new JLabel("Отчество:");
        JLabel phoneLabel = new JLabel("Телефон: *");
        JLabel emailLabel = new JLabel("Email: *");
        JLabel birthDateLabel = new JLabel("Дата рождения: *");
        JLabel addressLabel = new JLabel("Обращение *");

        lastNameField = new JTextField(Configuration.getLastName(), 20);
        firstNameField = new JTextField(Configuration.getFirstName(), 20);
        middleNameField = new JTextField(Configuration.getMiddleName(), 20);
        phoneField = new JTextField(Configuration.getPhone(), 20);
        phoneField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneField.getText().equals("+71234567890")) {
                    phoneField.setText("");
                    phoneField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneField.getText().isEmpty()) {
                    phoneField.setForeground(Color.GRAY);
                    phoneField.setText("+71234567890");
                }
            }
        });

        emailField = new JTextField(Configuration.getEmail(), 20);
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("example@example.com")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.GRAY);
                    emailField.setText("example@example.com");
                }
            }
        });

        UtilDateModel model = new UtilDateModel(parseDate(Configuration.getBirthday()));
        Properties p = new Properties();
        datePickerPanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePickerPanel, new DateLabelFormatter());

        addressBox = new JComboBox<>(new String[]{"Уважаемый", "Уважаемая"});
        addressBox.getModel().setSelectedItem(Configuration.getAddress());

        startButton = new JButton("Запуск");
        startButton.addActionListener(new StartButtonListener());
        startButton.setHorizontalAlignment(SwingConstants.CENTER);

        finishButton = new JButton("Завершить");
        finishButton.addActionListener(e -> {
            seleniumThread.interrupt();
            dispose();
            System.exit(0); // Завершение программы
        });

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
        add(addressLabel, constraints);
        constraints.gridy = 7;
        add(startButton, constraints);

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
        add(addressBox, constraints);
        constraints.gridy = 7;
        add(finishButton, constraints);

        pack(); // Подгон размера окна под размещенные элементы
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Date parseDate(String stringDate) {
        if (stringDate == "") {
            return null;
        }
        try {
            Date date = dateFormatter.parse(stringDate);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }

    class StartButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Проверка правильности заполнения регистрационной формы
            Validator validator = new Validator();
            if (validator.lastNameValidator(lastNameField.getText())
                    && validator.firstNameValidator(firstNameField.getText())
                    && validator.middleNameValidator(middleNameField.getText())
                    && validator.phoneValidator(phoneField.getText())
                    && validator.emailValidator(emailField.getText())
                    && validator.birthDateValidator((Date) datePicker.getModel().getValue())
                    && validator.addressValidator((String) addressBox.getModel().getSelectedItem())
            ) {
                Configuration.setLastName(lastNameField.getText());
                Configuration.setFirstName(firstNameField.getText());
                Configuration.setMiddleName(middleNameField.getText());
                Configuration.setPhone(phoneField.getText());
                Configuration.setEmail(emailField.getText());
                Configuration.setBirthday(dateFormatter.format(datePicker.getModel().getValue()));
                Configuration.setAddress((String) addressBox.getModel().getSelectedItem());

                Configuration.saveConfig();

//                dispose();
                seleniumThread = new Thread(new SeleniumAlgorithm());
                seleniumThread.start();
//                seleniumAlgorithm = new SeleniumAlgorithm();
            }

        }
    }
}

