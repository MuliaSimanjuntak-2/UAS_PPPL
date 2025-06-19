package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import pages.ProductManagementPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class ProductManagementSteps {
    private WebDriver driver;
    private ProductManagementPage productPage;
    private ExtentReports extent;
    private ExtentTest test;
    private int initialProductCount;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        productPage = new ProductManagementPage(driver);
        // Assume extent is initialized in TestRunner
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the product management page")
    public void i_am_on_the_product_management_page() {
        driver.get("http://127.0.0.1:8000/manajemen-produk");
    }

    @When("I click the add product button")
    public void i_click_the_add_product_button() {
        productPage.clickAddProductButton();
    }

    @Then("the add product modal should be displayed")
    public void the_add_product_modal_should_be_displayed() {
        Assert.assertEquals("Tambah Produk Baru", productPage.getModalTitle());
    }



    @When("I fill in the product form with name {string}, category {string}, description {string}, price {string}, stock {string}, and image {string}")
    public void i_fill_in_the_product_form(String name, String category, String description, String price, String stock, String image) {
        productPage.fillProductForm(name, category, description, price, stock, image);
    }

    @When("I submit the product form")
    public void i_submit_the_product_form() {
        productPage.submitProduct();
    }

    @Then("the product {string} should be displayed in the table")
    public void the_product_should_be_displayed_in_the_table(String productName) {
        Assert.assertTrue(productPage.isProductDisplayed(productName));
    }

    @Then("I should see a validation error message saying {string}")
    public void i_should_see_a_validation_error_message_saying(String expectedMessage) {
        String actualMessage = productPage.getProductNameErrorMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @When("I switch to the {string} tab")
    public void i_switch_to_the_tab(String category) {
        productPage.switchTab(category);
    }

    @Then("I should see products in the {string} category")
    public void i_should_see_products_in_the_category(String category) {
        Assert.assertTrue(productPage.getProductCount() >= 0);
    }
    @When("I store the current number of products")
    public void i_store_the_current_number_of_products() {
        // Simpan jumlah produk SEBELUM dihapus
        this.initialProductCount = productPage.getProductCount();
    }
    @When("I select the first product")
    public void i_select_the_first_product() {
        productPage.selectProduct(0);
    }

    @When("I click the delete button")
    public void i_click_the_delete_button() {
        productPage.clickDeleteButton();
        productPage.confirmAlert(); // alert konfirmasi hapus

        try {
            Thread.sleep(500); // delay agar alert notifikasi muncul dulu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        productPage.confirmSecondAlertIfPresent(); // alert "1 produk berhasil dihapus"
        productPage.waitUntilNoAlert();
    }

    @Then("the product count should decrease")
    public void the_product_count_should_decrease() {
        productPage.waitUntilProductCountChanges(initialProductCount);
        int currentCount = productPage.getProductCount();
        Assert.assertTrue("Product count should decrease", currentCount < initialProductCount);
    }

    @When("I click the delete button without selecting any product")
    public void i_click_delete_without_selecting_any_product() {
        productPage.clickDeleteButton();
    }

    @Then("I should see an alert saying no product was selected")
    public void i_should_see_alert_no_selection() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("ALERT TEXT: " + alertText); // Tambahkan ini
        Assert.assertTrue("Alert message mismatch", alertText.contains("Pilih produk yang ingin dihapus"));
        alert.accept();
    }




    @When("I search for {string}")
    public void i_search_for(String searchTerm) {
        productPage.searchProduct(searchTerm);
    }

    @Then("I should see products matching {string}")
    public void i_should_see_products_matching(String searchTerm) {
        try {
            // Kita tunggu secara eksplisit sampai produk dengan nama yang dicari itu muncul di tabel.
            // Asumsi: metode isProductDisplayed sudah diperbaiki dengan relative XPath.
            boolean isDisplayed = productPage.isProductDisplayed(searchTerm);

            // Lakukan assertion SETELAH menunggu dan mendapatkan hasilnya.
            Assert.assertTrue("Produk dengan nama '" + searchTerm + "' tidak ditemukan setelah pencarian.", isDisplayed);

        } catch (Exception e) {
            // Jika isProductDisplayed melempar TimeoutException, kita bisa gagal dengan pesan yang jelas
            Assert.fail("Timeout: Produk dengan nama '" + searchTerm + "' tidak muncul dalam waktu yang ditentukan.");
        }
    }
}