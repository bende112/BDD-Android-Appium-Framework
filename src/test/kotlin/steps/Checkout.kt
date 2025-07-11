package steps

import io.appium.java_client.AppiumBy
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert.assertTrue
import utils.CheckoutHelper.fillCheckoutForm
import utils.CheckoutHelper.waitUntilVisible
import utils.DriverFactory.driver
import java.time.Duration


class Checkout {

    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    @Given("I launched app and I am on the product list screen")
    fun onProductScreen() {
        loginWithCredentials("standard_user", "secret_sauce")
        assertOnProductsPage()
    }

    fun loginWithCredentials(username: String, password: String) {
        val usernameField =
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-Username")))
        val passwordField = driver.findElement(AppiumBy.accessibilityId("test-Password"))
        val loginButton = driver.findElement(By.xpath("//android.widget.TextView[@text='LOGIN']"))

        usernameField.sendKeys(username)
        passwordField.sendKeys(password)
        loginButton.click()
    }

    fun assertOnProductsPage() {
        val title = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-PRODUCTS")))
        assertTrue(title.isDisplayed)
    }

    @When("I add {string} to the cart")
    fun addProductFromDetails(product: String) {
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='$product']")
            )
        )

        val addToCartButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='ADD TO CART']")
            )
        )
        addToCartButton.click()
        println("✅ Added product to cart from details: $product")
    }

    @When("I navigates to the checkout screen")
    fun goToCheckout() {
        // Tap the cart icon in top right
        val cartIcon = wait.until(
            ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("test-Cart")
            )
        )
        cartIcon.click()
    }

    @When("I enter checkout details {string}, {string}, and {string}")
    fun enterCheckoutDetails(first: String, last: String, zip: String) {
        fillCheckoutForm(first, last, zip)
    }

    @When("{string} and its price {string} should be displayed in the checkout overview")
    fun verifyProductAndPrice(product: String, price: String) {
        val productItem = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='$product']")
            )
        )

        val priceItem = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='$price']")
            )
        )

        assertTrue(productItem.isDisplayed)
        assertTrue(priceItem.isDisplayed)
    }

    @And("I click the checkout button")
    fun clickCheckoutButton() {
        val checkoutButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("test-CHECKOUT")
            )
        )
        checkoutButton.click()
    }

    @Then("the total price should be greater than 0")
    fun totalPriceCheck() {
        val totalText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-Total"))
        ).text

        val total = totalText.replace(Regex("[^0-9.]"), "").toDouble()
        assertTrue(total > 0)
    }

    @Then("{string} and {string} should be displayed in the checkout list")
    fun multipleProductsDisplayed(p1: String, p2: String) {
        waitUntilVisible(By.xpath("//android.widget.TextView[@text='$p1']"))
        waitUntilVisible(By.xpath("//android.widget.TextView[@text='$p2']"))
        assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='$p1']")).isDisplayed)
        assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='$p2']")).isDisplayed)
    }

    @And("I remove {string} from the checkout list")
    fun removeProductFromCheckout(productName: String) {
        val removeButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='$productName']/../../..//android.widget.TextView[@text='REMOVE']")
            )
        )
        removeButton.click()
        println("🗑️ Removed product: $productName")
    }

    @Then("{string} should not be displayed in the checkout list")
    fun productShouldNotBeDisplayed(productName: String) {
        val elements = driver.findElements(By.xpath("//android.widget.TextView[@text='$productName']"))
        assertTrue(elements.isEmpty(), "❌ Product '$productName' is still visible in checkout list.")
        println("✅ Product '$productName' is not displayed in the checkout list")
    }
}

