package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.HomePage;
import utilities.DriverFactory;
import utilities.ExtentReportUtil;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private LoginPage loginPage = new LoginPage(driver);

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        String baseUrl = System.getProperty("base.url", "http://127.0.0.1:8000");
        driver.get(baseUrl + "/login");
        ExtentReportUtil.createTest("Login Test - Navigate to Login Page");
        ExtentReportUtil.logPass("Navigated to login page: " + baseUrl + "/login");
    }

    @When("I enter valid email {string} and password {string}")
    public void i_enter_valid_email_and_password(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        ExtentReportUtil.logPass("Entered valid email: " + email + " and password");
    }

    @When("I enter invalid email {string} and password {string}")
    public void i_enter_invalid_email_and_password(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        ExtentReportUtil.logPass("Entered invalid email: " + email + " and password");
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLogin();
        ExtentReportUtil.logPass("Clicked login button");
    }

    @Then("I should be redirected to the home page")
    public void i_should_be_redirected_to_the_home_page() {
        HomePage homePage = new HomePage(driver);
        wait.until(ExpectedConditions.urlContains("/home"));
        Assert.assertTrue("Home page should be loaded", homePage.isHomePageLoaded());
        ExtentReportUtil.logPass("Successfully redirected to home page");
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedError) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getErrorMessageLocator()));
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals("Error message should match", expectedError, actualError);
        ExtentReportUtil.logPass("Error message displayed: " + actualError);
    }
}