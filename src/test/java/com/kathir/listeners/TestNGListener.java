package com.kathir.listeners;

import com.kathir.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.ITestResult;

public class TestNGListener implements IExecutionListener {
    private static final Logger logger = LogManager.getLogger(TestNGListener.class);
    @Override
    public void onExecutionStart() {
        System.out.println("=== Automation Execution Started ===");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("=== Automation Execution Finished ===");
    }


    public void onTestFailure(ITestResult result) {
        ScreenshotUtil.screenshotFile(result.getName());
        logger.error("Test Failed: " + result.getName(), result.getThrowable());
    }

}
