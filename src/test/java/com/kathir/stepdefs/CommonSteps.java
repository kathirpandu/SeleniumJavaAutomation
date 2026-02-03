package com.kathir.stepdefs;

import com.kathir.pages.mmt.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {

    private final HomePage homePage;   // ✅ Declare

    // PicoContainer will inject HomePage automatically
    public CommonSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    @When("user selects flights option")
    public void user_selects_flights_option() {
         homePage.clickFlights(); }

    @Then("framework should execute successfully")
    public void framework_should_execute_successfully() { }
}
