package com.kathir.pages.mmt;

import com.kathir.base.BasePage;
import com.kathir.driver.DriverManager;
import org.openqa.selenium.By;

import java.util.Set;



public class HomePage extends BasePage {
    private final By flightsTab = By.xpath("//li[contains(@class,'menu_Flights')]");
    private final By custID = By.xpath("//input[@name='fldLoginUserId']");
    private final By custCont = By.xpath("//a[text()='CONTINUE']");

    public void clickFlights(){
        click(flightsTab);
    }

    public void cutIDInpt(){
        type(custID,"Test123");
    }
    public void clickContinue(){
        click(custCont);
    }

    public void switchToFrame(String frame){
        DriverManager.getDriver().switchTo().defaultContent();
        DriverManager.getDriver().switchTo().frame(frame);
    }

    public void switchToWindow(String title){

       String parentWindow=  DriverManager.getDriver().getWindowHandle();
       Set<String> Windows=  DriverManager.getDriver().getWindowHandles();
        for (String handle : Windows) {
            if (!handle.equals(parentWindow)) {
                // Switch to the child window
                DriverManager.getDriver().switchTo().window(handle);
                System.out.println("Switched to Child Window with Handle: " + handle);
            }

       }



    }

