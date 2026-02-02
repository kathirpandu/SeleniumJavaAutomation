package com.kathir.context;

import com.kathir.driver.DriverManager;   // 🔹 MISSING IMPORT
import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;

    public TestContext() {
        DriverManager.initDriver();
        this.driver = DriverManager.getDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
