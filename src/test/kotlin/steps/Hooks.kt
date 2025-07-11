package steps

import io.cucumber.java.After
import io.cucumber.java.Before
import utils.DriverFactory

class Hooks {

    @Before
    fun setUp() {
        DriverFactory.startDriver()
    }

    @After
    fun tearDown() {
        DriverFactory.quitDriver()
    }
}
