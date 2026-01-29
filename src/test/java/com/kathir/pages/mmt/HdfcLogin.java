package com.kathir.pages.mmt;

import com.kathir.base.BasePage;
import org.openqa.selenium.By;

public class HdfcLogin extends BasePage {
    private final By flightsTab = By.xpath("//li[contains(@class,'menu_Flights')]");
    public void clickFlights(){ click(flightsTab); }
}
