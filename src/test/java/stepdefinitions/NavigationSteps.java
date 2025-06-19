package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utilities.DriverFactory;

public class NavigationSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private HomePage homePage = new HomePage(driver);

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        driver.get("http://127.0.0.1:8000"); // Adjust URL as needed
    }

    @When("I click on the {string} link")
    public void i_click_on_the_link(String linkText) {
        switch (linkText) {
            case "Layanan & Produk Kopi":
                homePage.navigateToServices();
                break;
            case "Katalog":
                homePage.navigateToCatalog();
                break;
            case "Artikel":
                homePage.navigateToArticles();
                break;
            case "Kontak Kerjasama":
                homePage.navigateToContact();
                break;
            case "Komunitas":
                homePage.navigateToCommunity();
                break;
        }
    }



    @Then("I should be redirected to the services page")
    public void i_should_be_redirected_to_the_services_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("layanankebunkopi"));
    }


}