package com.kathir.utils;

import org.testng.asserts.SoftAssert;

public class AssertUtil {
    private static final SoftAssert softAssert = new SoftAssert();

    public static void assertTrue(boolean condition, String message) {
        softAssert.assertTrue(condition, message);
    }

    public static void assertAll() {
        softAssert.assertAll();
    }
}
