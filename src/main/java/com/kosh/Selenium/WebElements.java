package com.kosh.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebElements {
    public WebDriver driver;

    public WebElements(WebDriver driver) {
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

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_DDL_Month\"]")
    private WebElement monthOfBirthField;
    public void selectMonthOfBirth(String monthOfBirth) {
        monthOfBirthField.sendKeys(monthOfBirth);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_TextBox_Year\"]")
    private WebElement yearOfBirthField;
    public void inputYearOfBirth(String yearOfBirth) {
        yearOfBirthField.sendKeys(yearOfBirth);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_DDL_Mr\"]")
    private WebElement addressField;
    public void selectAddress(String address) {
        addressField.sendKeys(address);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_txtCode\"]")
    private WebElement captchaField;
    public void inputCaptcha(String captchaResult) {
        captchaField.sendKeys(captchaResult);
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_ButtonA\"]")
    private WebElement nextPageBtn;
    public void nextPage() {
        nextPageBtn.click();
    }

    @FindBy(xpath = "//*[@id=\"reasons\"]/dd[4]/a")
    private WebElement tenYearPassportBtn;
    public void selectApplicationCategory() {
        tenYearPassportBtn.click();
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_RList_0\"]")
    private WebElement adultPassportBtn;
    public void selectAdultPassport() {
        adultPassportBtn.click();
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_ButtonA\"]")
    private WebElement nextPageBtn2;
    public void nextPage2() {
        nextPageBtn2.click();
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_CheckBoxList1_0\"]")
    private WebElement biometricPassport;
    public void selectBiometricPassport () {
        biometricPassport.click();
    }

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_ButtonQueue\"]")
    private WebElement appointmentBtn;
    public void makeAppointment() {
        appointmentBtn.click();
    }

}
