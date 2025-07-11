package utils

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

object DriverFactory {
    private var _driver: AndroidDriver? = null
    val driver: AndroidDriver
        get() = _driver ?: throw IllegalStateException("Driver not initialized")

    fun startDriver() {
        val caps = DesiredCapabilities()
        caps.setCapability("platformName", "Android")
        caps.setCapability("appium:deviceName", "RFCT608BEYX")
        caps.setCapability("appium:automationName", "UiAutomator2")
        caps.setCapability("appium:app", "/Users/Bende/Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk")
        caps.setCapability("appium:appPackage", "com.swaglabsmobileapp")
        caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity")

        _driver = AndroidDriver(URL("http://127.0.0.1:4723"), caps)
    }

    fun quitDriver() {
        _driver?.quit()
        _driver = null
    }
}
