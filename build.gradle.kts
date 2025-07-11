plugins {
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.cucumber:cucumber-testng:7.14.0")
    testImplementation("org.testng:testng:7.8.0")
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    implementation("io.appium:java-client:8.3.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.12.1")
}

tasks.withType<Test> {
    useTestNG()
}

tasks.test {
    useTestNG()
    include("**/runner/**TestRunner*")
}

tasks.register("runWithAppium") {
    group = "verification"
    description = "Start Appium, run tests, stop Appium"

    doLast {
        println("🚀 Starting Appium...")
        val process = ProcessBuilder("appium")
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        Thread.sleep(5000) // Wait for Appium to fully start

        println("🧪 Running tests...")
        exec {
            commandLine("gradle", "test")
        }

        println("🛑 Stopping Appium...")
        process.destroy()
    }
}

