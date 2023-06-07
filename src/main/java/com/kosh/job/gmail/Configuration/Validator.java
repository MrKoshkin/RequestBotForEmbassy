package com.kosh.job.gmail.Configuration;

import javax.swing.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    protected static  boolean lastNameValidator(String lastName) {
        String lastNameRegex = "^[A-Za-zА-Яа-я]+$";
        Pattern lastNamePattern = Pattern.compile(lastNameRegex);
        if (lastName == null) {
            JOptionPane.showMessageDialog(null, "Не указана фамилия", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Pattern.matches(lastNameRegex, lastName)) {
            JOptionPane.showMessageDialog(null, "Неправильный формат фамилии!" +
                    "\n Допустимо использование букв русского и латинского алфавита", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Matcher matcher = lastNamePattern.matcher(lastName);
        return matcher.matches();
    }
    protected static  boolean firstNameValidator(String firstName) {
        String firstNameRegex = "^[A-Za-zА-Яа-я]+$";
        Pattern firstNamePattern = Pattern.compile(firstNameRegex);
        if (firstName == null) {
            JOptionPane.showMessageDialog(null, "Не указано имя", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Pattern.matches(firstNameRegex, firstName)) {
            JOptionPane.showMessageDialog(null, "Неправильный формат имени!" +
                    "\n Допустимо использование букв русского и латинского алфавита", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Matcher matcher = firstNamePattern.matcher(firstName);
        return matcher.matches();
    }
    protected static  boolean middleNameValidator(String middleName) {
        String middleNameRegex = "^[A-Za-zА-Яа-я]+$";
        Pattern middleNamePattern = Pattern.compile(middleNameRegex);
        if (middleName == null) {
            return true;
        }
        if (!Pattern.matches(middleNameRegex, middleName)) {
            JOptionPane.showMessageDialog(null, "Неправильный формат отчества!" +
                    "\n Допустимо использование букв русского и латинского алфавита", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Matcher matcher = middleNamePattern.matcher(middleName);
        return matcher.matches();
    }
    protected static boolean phoneValidator(String phoneNumber) {
//        String phoneRegex = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";
        String phoneRegex = "^(\\+7)([0-9]{10})$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        if (phoneNumber == null) {
            JOptionPane.showMessageDialog(null, "Не указан номер телефона", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Pattern.matches(phoneRegex, phoneNumber)) {
            JOptionPane.showMessageDialog(null, "Неправильный формат номера телефона!" +
                    "\n Требуется формат: +71234567890", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }
    protected static boolean emailValidator(String email) {
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (email == null) {
            JOptionPane.showMessageDialog(null, "Не указана электронная почта", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Pattern.matches(emailRegex, email)) {
            JOptionPane.showMessageDialog(null, "Неправильный формат электронной почты!" +
                    "\n Требуется формат: example@example.com", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
    protected static boolean birthDateValidator(Date birthDate) {
        if (birthDate==null) {
            JOptionPane.showMessageDialog(null, "Не указана дата рождения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(lastNameValidator("Кошкин"));
        System.out.println(firstNameValidator("Сергей"));
        System.out.println(middleNameValidator("Александрович"));
        System.out.println(phoneValidator("+79037622046"));
        System.out.println(emailValidator("kosh.job@gmail.com"));
    }
}
