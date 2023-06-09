package com.kosh.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    public WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_txtFam\"]")
    private WebElement lastNameField;
    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_txtIm\"]")
    private WebElement firstNameField;
    public void inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_txtOt\"]")
    private WebElement middleNameField;
    public void inputMiddleName(String middleName) {
        middleNameField.sendKeys(middleName);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_txtTel\"]")
    private WebElement phoneField;
    public void inputPhone(String phone) {
        phoneField.sendKeys(phone);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_txtEmail\"]")
    private WebElement emailField;
    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_DDL_Day\"]")
    private WebElement dayOfBirthField;
    public void selectDayOfBirth(String dayOfBirth) {
        dayOfBirthField.sendKeys(dayOfBirth);
    }



    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_txtFam\"]")
    private WebElement addressField;

}
