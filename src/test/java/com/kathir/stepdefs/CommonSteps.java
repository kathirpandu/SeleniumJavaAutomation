package com.kathir.stepdefs;

import com.kathir.pages.mmt.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {

//    @When("user selects flights option")
//    public void user_is_on_makemytrip() { new HomePage().clickFlights(); }
//
    @When("user selects flights option")
    public void user_selects_flights_option() { new HomePage().clickFlights(); }

    @Then("framework should execute successfully")
    public void framework_should_execute_successfully() { }
}
