package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(linkText = "Home")
    private WebElement homeLink;
    @FindBy(css = "a.login-btn[href='/manajemen-produk']")
    public WebElement manageButton;

    @FindBy(linkText = "Layanan & Produk Kopi")
    private WebElement servicesLink;

    @FindBy(linkText = "Katalog")
    private WebElement catalogLink;

    @FindBy(linkText = "Artikel")
    private WebElement articlesLink;

    @FindBy(linkText = "Kontak Kerjasama")
    private WebElement contactLink;

    @FindBy(linkText = "Komunitas")
    private WebElement communityLink;

    @FindBy(css = ".input[placeholder='Search...']")
    private WebElement searchInput;

    @FindBy(css = ".btn img[src*='Search-vector']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToServices() {
        servicesLink.click();
    }

    public void navigateToCatalog() {
        catalogLink.click();
    }

    public void navigateToArticles() {
        articlesLink.click();
    }

    public void navigateToContact() {
        contactLink.click();
    }

    public void navigateToCommunity() {
        communityLink.click();
    }

    public void searchFor(String query) {
        searchInput.sendKeys(query);
        searchButton.click();
    }

    public boolean isHomePageLoaded() {
        return driver.getCurrentUrl().contains("/home") &&
                homeLink.isDisplayed();
    }
}