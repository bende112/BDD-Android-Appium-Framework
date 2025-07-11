package steps

import io.cucumber.java.en.*
import org.openqa.selenium.By
import org.testng.Assert.*
import utils.DriverFactory
import io.appium.java_client.AppiumBy
//import io.cucumber.messages.types.Duration
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import utils.DriverFactory.driver
import java.time.Duration

class Login {

    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    @Given("I open the app")
    fun iOpenApp() {
        // Already launched by Appium
    }

    @When("I login with username {string} and password {string}")
    fun iLogin(username: String, password: String) {
        val wait = WebDriverWait(DriverFactory.driver, Duration.ofSeconds(10))

        val usernameField =
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("test-Username")))
        val passwordField =
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("test-Password")))
        val loginButton =
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='LOGIN']")))

        usernameField.sendKeys(username)
        passwordField.sendKeys(password)
        loginButton.click()
    }


    @Then("I should see the products page")
    fun iSeeProductsPage() {
        val wait = WebDriverWait(driver, Duration.ofSeconds(20))
        val productsTitle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("test-PRODUCTS")
            )
        )
        assertTrue(productsTitle.isDisplayed)
    }
}