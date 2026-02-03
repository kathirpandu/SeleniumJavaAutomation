package com.kathir.stepdefs;

import com.kathir.base.BasePage;
import com.kathir.pages.mmt.HdfcLoginPage;
import com.kathir.pages.mmt.HomePage;
import com.kathir.utils.CommonUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class bankingSteps {

    private HomePage homePage;
    private BasePage basePage;

    private HdfcLoginPage hdfcLoginPage;
    public bankingSteps(HomePage homePage, BasePage basePage, HdfcLoginPage hdfcLoginPage) {   // Injected automatically
        this.homePage = homePage;
        this.basePage = basePage;

        this.hdfcLoginPage = hdfcLoginPage;
    }

    @When("user Login HDFC NetBanking")
    public void user_Login() {
        homePage.switchToFrame("login_page");
        homePage.cutIDInpt();
        homePage.clickContinue();
    }


    @When("Entering Credentials")
    public void user_Entering() {
        CommonUtil.switchToWindowByTitle("Nb Login");
        hdfcLoginPage.enterCustPD();
        hdfcLoginPage.clickLogin();
        hdfcLoginPage.checkInvalidLoginError();


    }

    @Then("Validating Login")
    public void validate() {

    }
}
