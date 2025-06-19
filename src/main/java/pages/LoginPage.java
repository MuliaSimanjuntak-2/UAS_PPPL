package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "errorMessage")
    private WebElement errorMessage;

    @FindBy(linkText = "Lupa Kata Sandi?")
    private WebElement forgotPasswordLink;

    @FindBy(linkText = "Buat akun")
    private WebElement createAccountLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.isDisplayed() ? errorMessage.getText() : "";
    }

    public By getErrorMessageLocator() {
        return By.id("errorMessage");
    }

    public By getEmailFieldLocator() {
        return By.id("email");
    }

    public By getPasswordFieldLocator() {
        return By.id("password");
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }

    public void clickCreateAccount() {
        createAccountLink.click();
    }
}