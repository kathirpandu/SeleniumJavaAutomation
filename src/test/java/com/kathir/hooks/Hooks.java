package com.kathir.hooks;

import com.kathir.context.TestContext;
import com.kathir.driver.DriverManager;
import com.kathir.utils.PropertyUtil;
import com.kathir.utils.ScreenshotUtil;
import io.cucumber.java.*;

public class Hooks {

    private TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario(){
        String url = PropertyUtil.get("base.url");
        DriverManager.getDriver().get(url);
    }

    @After
    public void afterScenario(Scenario scenario){
        if(scenario.isFailed()){
            ScreenshotUtil.attachToAllure("Failure Screenshot");
        }
        DriverManager.quitDriver();
    }
}
