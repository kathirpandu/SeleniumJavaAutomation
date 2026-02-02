package com.kathir.stepdefs;

import com.kathir.pages.mmt.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class bankingSteps {

    HomePage homePage = new HomePage();
    @When("user Login HDFC NetBanking")
    public void user_Login() {
        homePage.switchToFrame("login_page");
        homePage.cutIDInpt();
        homePage.clickContinue();
    }


    @When("Entering Credentials")
    public void user_Entering() {

        homePage.clickFlights();

    }

    @Then("Validating Login")
    public void validate() {

    }
}
