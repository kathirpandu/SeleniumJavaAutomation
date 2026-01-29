package com.kathir.utils;

import com.kathir.driver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;

public final class ScreenshotUtil {
    private ScreenshotUtil(){}

    public static byte[] screenshotBytes(){
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String screenshotFile(String name){
        try{
            File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File dest = new File("target/screenshots/" + name + ".png");
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());
            return dest.getAbsolutePath();
        }catch(Exception e){
            return null;
        }
    }

    public static void attachToAllure(String name){
        Allure.addAttachment(name, new ByteArrayInputStream(screenshotBytes()));
    }
}
