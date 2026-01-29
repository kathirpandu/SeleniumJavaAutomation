package com.kathir.hooks;

import com.kathir.driver.DriverFactory;
import com.kathir.driver.DriverManager;
import com.kathir.utils.PropertyUtil;
import com.kathir.utils.ScreenshotUtil;
import io.cucumber.java.*;

public class Hooks {

    @Before
    public void beforeScenario(){
        DriverFactory.initDriver();
        DriverManager.getDriver().get(PropertyUtil.get("base.url"));
    }

    @After
    public void afterScenario(Scenario scenario){
        if(scenario.isFailed()){
            ScreenshotUtil.attachToAllure("Failure Screenshot");
        }
        DriverFactory.quitDriver();
    }
}
