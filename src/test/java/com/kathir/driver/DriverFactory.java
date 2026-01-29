package com.kathir.driver;

import com.kathir.exceptions.FrameworkException;
import com.kathir.utils.PropertyUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
    private DriverFactory(){}

    public static void initDriver(){
        String browser = PropertyUtil.get("browser");
        boolean headless = PropertyUtil.getBoolean("headless");

        if(!"chrome".equalsIgnoreCase(browser)){
            throw new FrameworkException("Unsupported browser: " + browser);
        }
        WebDriverManager.chromedriver().clearDriverCache().setup();
//        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications","--disable-popup-blocking","--start-maximized");
        if(headless) options.addArguments("--headless=new");

        DriverManager.setDriver(new ChromeDriver(options));
    }

    public static void quitDriver(){
        if(DriverManager.getDriver()!=null){
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
