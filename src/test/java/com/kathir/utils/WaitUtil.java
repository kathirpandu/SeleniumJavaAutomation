package com.kathir.utils;

import com.kathir.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtil {
    private WaitUtil(){}

    public static WebElement waitForVisible(By locator){
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(PropertyUtil.getInt("timeout"))
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
