package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class ProductManagementPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private int initialProductCount;

    // Locators
    private By addProductBtn = By.id("add-product-btn");
    private By modalTitle = By.cssSelector(".modal-title");
    private By productNameInput = By.id("product-name");
    private By productCategorySelect = By.id("product-category");
    private By productDescriptionInput = By.id("product-description");
    private By productPriceInput = By.id("product-price");
    private By productStockInput = By.id("product-stock");
    private By productImageInput = By.id("product-image");
    private By submitBtn = By.id("submit-product-btn");
    private By closeModalBtn = By.id("close-modal-btn");
    private By tab = By.cssSelector(".tab");
    private By productRows = By.cssSelector(".table-row");
    private By deleteProductBtn = By.id("delete-product-btn");
    private By checkbox = By.cssSelector(".checkbox");
    private By searchInput = By.cssSelector(".search-input");
    private By productNameError = By.id("error-name");

    public ProductManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickAddProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addProductBtn)).click();
    }

    public String getModalTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle)).getText();
    }

    public void fillProductForm(String name, String category, String description, String price, String stock, String imagePath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNameInput)).sendKeys(name);
        WebElement categorySelect = wait.until(ExpectedConditions.elementToBeClickable(productCategorySelect));
        categorySelect.findElement(By.xpath("//option[text()='" + category + "']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productDescriptionInput)).sendKeys(description);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceInput)).sendKeys(price);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productStockInput)).sendKeys(stock);
        String relativeImagePath = "test image/img.png";
        String absoluteImagePath = new File(relativeImagePath).getAbsolutePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            wait.until(ExpectedConditions.presenceOfElementLocated(productImageInput)).sendKeys(absoluteImagePath);
        }
    }

    public void submitProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
//        wait.until(ExpectedConditions.alertIsPresent());
//        Alert alert = driver.switchTo().alert();
//        System.out.println("Alert text: " + alert.getText());
//        alert.accept();
    }

    public String getProductNameErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productNameError)).getText();
    }

    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeModalBtn)).click();
    }

    public void switchTab(String category) {
        List<WebElement> tabs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tab));
        for (WebElement tab : tabs) {
            if (tab.getText().equals(category)) {
                tab.click();
                break;
            }
        }
    }

    public int getProductCount() {
        // Ubah dari wait.until(...) menjadi driver.findElements(...)
        // findElements akan langsung mengembalikan list (bisa kosong) tanpa menunggu.
        return driver.findElements(productRows).size();
    }

    public void selectProduct(int index) {
        List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(checkbox));
        checkboxes.get(index).click();
    }

    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteProductBtn)).click();
    }

    public void confirmAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    public void confirmSecondAlertIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            Thread.sleep(300); // biar alert benar-benar tertutup sebelum lanjut
        } catch (Exception e) {
            System.out.println("No second alert appeared.");
        }
    }

    public void waitUntilNoAlert() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        } catch (Exception ignored) {}
    }

    public void waitUntilProductCountChanges(int oldCount) {
        wait.until(driver -> getProductCount() != oldCount);
    }


    public void searchProduct(String searchTerm) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys(searchTerm);
    }

    public boolean isProductDisplayed(String productName) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows))
                .stream()
                .anyMatch(row -> row.findElement(By.xpath("./div[2]")).getText().equals(productName));
    }
//    public void verifyAlertTextAndAccept(String expectedText) {
//        try {
//            // Tunggu hingga alert muncul
//            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.alertIsPresent());
//
//            // Dapatkan teks aktual dari alert
//            String actualAlertText = alert.getText();
//
//            // Verifikasi teksnya
//            Assert.assertEquals("Teks pada alert tidak sesuai harapan.", expectedText, actualAlertText);
//
//            // Terima alert
//            alert.accept();
//
//        } catch (Exception e) {
//            // Jika alert tidak muncul atau ada masalah lain
//            throw new AssertionError("Gagal memverifikasi alert. Pesan: " + e.getMessage(), e);
//        }
//    }

}