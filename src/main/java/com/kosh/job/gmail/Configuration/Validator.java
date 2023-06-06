package com.kosh.job.gmail.Configuration;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    protected static boolean phoneValidator(String phoneNumber) {
//        String phoneRegex = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";
        String phoneRegex = "^(\\\\+7)([0-9]{10})$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        if (!Pattern.matches(phoneRegex, phoneNumber)) {
            JOptionPane.showMessageDialog(null, "Неправильный формат номера телефона!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }
    protected static boolean emailValidator(String email) {
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(phoneValidator("+79037622046"));
        System.out.println(emailValidator("kosh.job@gmail.com"));
    }
}
