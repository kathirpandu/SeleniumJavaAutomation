package com.kathir.base;

import com.kathir.driver.DriverManager;
import com.kathir.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebElement find(By locator){ return WaitUtil.waitForVisible(locator); }
    protected void click(By locator){ find(locator).click(); }
    protected void type(By locator,String value){
        WebElement el=find(locator);
        el.clear();
        el.sendKeys(value);
    }
    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected String getTitle() {
        return  DriverManager.getDriver().getTitle();
    }
}
