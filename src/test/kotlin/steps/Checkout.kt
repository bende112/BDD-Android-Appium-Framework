package steps

//import io.cucumber.messages.types.Duration
import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert.assertTrue
import utils.CheckoutHelper.fillCheckoutForm
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
        val usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-Username")))
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
    fun addProduct(product: String) {
        try {
            println("Looking for product: $product")

            // Locate ADD TO CART button below the product name
            val addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text='ADD TO CART']")
                )
            )
            addToCartButton.click()


            addToCartButton.click()
            println("✅ Added to cart: $product")

        } catch (e: Exception) {
            println("❌ Failed to add product: $product")
            throw e
        }
    }



    @When("I navigates to the checkout screen")
    fun goToCheckout() {
        val cartIcon = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("test-Cart")))
        cartIcon.click()

        val checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("test-CHECKOUT")))
        checkoutButton.click()
    }

    @When("I enter checkout details {string}, {string}, and {string}")
    fun enterCheckoutDetails(first: String, last: String, zip: String) {
        fillCheckoutForm(first, last, zip)
    }

    @Then("{string} should be displayed in the checkout overview")
    fun productDisplayedInOverview(product: String) {
        val productElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='$product']")
            )
        )
        assertTrue(productElement.isDisplayed)
    }


//    @Then("the product {string} with price {string} should be displayed in the checkout overview")
//    fun verifyProductAndPrice(product: String, price: String) {
//        // scroll to product
//        (driver as AndroidDriver).findElement(
//            AppiumBy.androidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"$product\"))"
//            )
//        )
//
//        val productItem = wait.until(
//            ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='$product']"))
//        )
//        val priceItem = wait.until(
//            ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='$price']"))
//        )
//
//        assertTrue(productItem.isDisplayed)
//        assertTrue(priceItem.isDisplayed)
//    }

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



    @Then("{string} should be displayed in the checkout list")
    fun productInCheckout(product: String) {
        val productItem = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='$product']")
            )
        )
        assertTrue(productItem.isDisplayed)
    }

    @Then("the total price should be greater than 0")
    fun totalPriceCheck() {
        val totalText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-Total"))
        ).text

        val total = totalText.replace(Regex("[^0-9.]"), "").toDouble()
        assertTrue(total > 0)
    }

//    @Then("{string} and {string} should be displayed in the checkout list")
//    fun multipleProductsDisplayed(p1: String, p2: String) {
//        productInCheckout(p1)
//        productInCheckout(p2)
//    }
//
//    @Then("the total price should be the sum of the products' prices")
//    fun priceSumCheck() {
//        // You could extract individual product prices and sum them
//        // For now, just log for verification
//        println("TODO: Implement price summing validation")
//    }
//
//    @Given("I have added {string} and {string} to the cart")
//    fun addMultipleProducts(p1: String, p2: String) {
//        addProduct(p1)
//        addProduct(p2)
//        goToCheckout()
//    }
//
//    @When("I deletes {string} from the checkout")
//    fun deleteProduct(p: String) {
//        val removeBtn = wait.until(
//            ExpectedConditions.elementToBeClickable(
//                By.xpath("//android.widget.TextView[@text='$p']/../../..//android.widget.Button[@text='REMOVE']")
//            )
//        )
//        removeBtn.click()
//    }
//
//    @Then("{string} should not be displayed in the checkout list")
//    fun productNotDisplayed(p: String) {
//        val elements = driver.findElements(By.xpath("//android.widget.TextView[@text='$p']"))
//        assertEquals(0, elements.size)
//    }
//
//    @Then("{string} should still be displayed in the checkout list")
//    fun productStillDisplayed(p: String) {
//        val element = wait.until(
//            ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//android.widget.TextView[@text='$p']")
//            )
//        )
//        assertTrue(element.isDisplayed)
//    }
//
//    @When("I navigates to the checkout screen without adding products")
//    fun emptyCheckout() {
//        goToCheckout()
//    }
//
//    @Then("an error message {string} should be displayed")
//    fun errorMessage(msg: String) {
//        val errorText = wait.until(
//            ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-Error message"))
//        ).text
//
//        assertEquals(msg, errorText)
//    }
//
//    @When("I try to add {string} to the cart")
//    fun tryAddUnavailableProduct(p: String) {
//        addProduct(p)
//    }
//
//    @Then("the product should not be added to the cart")
//    fun productNotAdded() {
//        // Optionally check cart count or confirmation label
//        println("TODO: Implement cart check for unavailable product")
//    }
}
