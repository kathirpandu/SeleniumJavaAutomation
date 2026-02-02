package com.kathir.driver;

import com.kathir.exceptions.FrameworkException;
import com.kathir.utils.PropertyUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public final class DriverManager {

    private DriverManager() {}

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        String browser = PropertyUtil.get("browser").toLowerCase();
        boolean headless = PropertyUtil.getBoolean("headless");
        boolean remote = PropertyUtil.getBoolean("remote");
        String gridUrl = PropertyUtil.get("grid.url");

        try {
            WebDriver webDriver;

            switch (browser) {
                case "chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications", "--start-maximized");
                    if (headless) options.addArguments("--headless=new");

                    if (remote) {
                        webDriver = new RemoteWebDriver(new URL(gridUrl), options);
                    } else {
                        WebDriverManager.chromedriver().setup();
                        webDriver = new ChromeDriver(options);
                    }
                }

                case "edge" -> {
                    EdgeOptions options = new EdgeOptions();
                    if (headless) options.addArguments("--headless=new");

                    if (remote) {
                        webDriver = new RemoteWebDriver(new URL(gridUrl), options);
                    } else {
                        WebDriverManager.edgedriver().setup();
                        webDriver = new EdgeDriver(options);
                    }
                }

                case "firefox" -> {
                    FirefoxOptions options = new FirefoxOptions();
                    if (headless) options.addArguments("-headless");

                    if (remote) {
                        webDriver = new RemoteWebDriver(new URL(gridUrl), options);
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        webDriver = new FirefoxDriver(options);
                    }
                }

                default -> throw new FrameworkException("Unsupported browser: " + browser);
            }

            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            webDriver.manage().window().maximize();

            setDriver(webDriver);

        } catch (Exception e) {
            throw new FrameworkException("Driver initialization failed", e);
        }
    }

    public static WebDriver getDriver() {
        WebDriver drv = driver.get();
        if (drv == null) {
            throw new FrameworkException("Driver not initialized. Call initDriver() first.");
        }
        return drv;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            unload();
        }
    }

    private static void setDriver(WebDriver drv) {
        driver.set(drv);
    }

    private static void unload() {
        driver.remove();
    }
}
