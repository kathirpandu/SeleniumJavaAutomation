package com.kathir.pages.mmt;

import com.kathir.base.BasePage;
import com.kathir.context.TestContext;
import com.kathir.utils.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HdfcLoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(HdfcLoginPage.class);

    public HdfcLoginPage(TestContext context) {
        super(context);
    }
    private final By custID1 = By.id("liabiltyLoginCustId");
    private final By custPD = By.id("keyboard");
    private final By loginBtn = By.id("loginBtn");

    private final By invalidLoginError = By.xpath("//p[text()='Customer ID/IPIN (Password) is invalid. Please try again.']");

    public void enterCustPD(){
        type(custPD,"Test@123");
    }
    public void clickLogin(){
        click(loginBtn);
    }

    public void checkInvalidLoginError(){
        logger.info(getText(invalidLoginError));
       Assert.assertTrue(getText(invalidLoginError).contains("Customer ID/IPIN (Password) is invalid"),"Expected Error not present");
       logger.info("Validation Passed");

    }

}
