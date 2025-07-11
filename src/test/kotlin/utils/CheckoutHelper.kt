package utils


import io.appium.java_client.AppiumBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import utils.DriverFactory.driver

object CheckoutHelper {
    private val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    fun fillCheckoutForm(first: String, last: String, zip: String) {
        val firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-First Name")))
        val lastName = driver.findElement(AppiumBy.accessibilityId("test-Last Name"))
        val postalCode = driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code"))
        val continueBtn = driver.findElement(AppiumBy.accessibilityId("test-CONTINUE"))

        firstName.sendKeys(first)
        lastName.sendKeys(last)
        postalCode.sendKeys(zip)
        continueBtn.click()
    }
}
