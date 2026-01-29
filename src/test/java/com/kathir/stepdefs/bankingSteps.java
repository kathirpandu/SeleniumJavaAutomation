package com.kathir.stepdefs;

import com.kathir.pages.mmt.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class bankingSteps {

    @When("user Login HDFC NetBanking")
    public void user_Login() {
        new HomePage().switchToFrame("login_page");
        new HomePage().cutIDInpt();
        new HomePage().clickContinue();
    }
    
    @When("Entering Credentials")
    public void user_Entering() {
        new HomePage().clickFlights();

    }

    @Then("Validating Login")
    public void validate() {

    }
}
