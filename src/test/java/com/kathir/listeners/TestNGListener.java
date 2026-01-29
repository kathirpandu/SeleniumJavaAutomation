package com.kathir.listeners;

import org.testng.IExecutionListener;

public class TestNGListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        System.out.println("=== Automation Execution Started ===");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("=== Automation Execution Finished ===");
    }
}
