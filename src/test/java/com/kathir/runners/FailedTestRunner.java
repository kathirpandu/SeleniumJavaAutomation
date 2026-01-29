package com.kathir.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun.txt",
        glue = {"com.kathir.stepdefs","com.kathir.hooks"},
        plugin = {
                "pretty",
                "html:target/failed-cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class FailedTestRunner extends AbstractTestNGCucumberTests {}
