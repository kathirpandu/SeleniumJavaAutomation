package com.kathir.utils;

import com.kathir.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public final class CommonUtil {
    private static final Logger logger = LogManager.getLogger(CommonUtil.class);
    private CommonUtil() {
    }

    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(),
                Duration.ofSeconds(PropertyUtil.getInt("timeout")));
    }

    /* ========================= WAITS ========================= */

    public static WebElement waitForVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static  WebElement waitForClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForInvisibility(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForTitleContains(String title) {
        getWait().until(ExpectedConditions.titleContains(title));
    }

    public static void waitForUrlContains(String urlPart) {
        getWait().until(ExpectedConditions.urlContains(urlPart));
    }

    /* ========================= WINDOWS ========================= */

    public static void switchToNewWindow() {
        String current = getDriver().getWindowHandle();
        Set<String> handles = getDriver().getWindowHandles();

        for (String handle : handles) {
            if (!handle.equals(current)) {
                getDriver().switchTo().window(handle);
                return;
            }
        }
        throw new RuntimeException("No new window found to switch");
    }

    public static void switchToWindowByTitle(String title) {
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);

            if (title.contains(getDriver().getTitle())) return;
        }
        throw new RuntimeException("Window with title not found: " + title);
    }

    public static void closeCurrentWindowAndSwitchBack() {
        String parent = getDriver().getWindowHandles().iterator().next();
        getDriver().close();
        getDriver().switchTo().window(parent);
    }

    /* ========================= FRAMES ========================= */

    public static void switchToFrame(By frameLocator) {
        getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public static void switchToFrame(int index) {
        getDriver().switchTo().frame(index);
    }

    public static void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    /* ========================= ALERTS ========================= */

    public static void acceptAlert() {
        getWait().until(ExpectedConditions.alertIsPresent()).accept();
    }

    public static void dismissAlert() {
        getWait().until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public static String getAlertText() {
        return getWait().until(ExpectedConditions.alertIsPresent()).getText();
    }

    /* ========================= JS UTILITIES ========================= */

    public static void scrollIntoView(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickUsingJS(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].click();", element);
    }

    public static void setValueUsingJS(By locator, String value) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].value='" + value + "';", element);
    }

    /* ========================= ACTIONS ========================= */

    public static void hoverOverElement(By locator) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(waitForVisible(locator)).perform();
    }

    public static void doubleClick(By locator) {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(waitForVisible(locator)).perform();
    }

    public static void rightClick(By locator) {
        Actions actions = new Actions(getDriver());
        actions.contextClick(waitForVisible(locator)).perform();
    }

    /* ========================= DROPDOWN ========================= */

    public static void selectByVisibleText(By locator, String text) {
        new Select(waitForVisible(locator)).selectByVisibleText(text);
    }

    public static void selectByValue(By locator, String value) {
        new Select(waitForVisible(locator)).selectByValue(value);
    }

    public static List<WebElement> getDropdownOptions(By locator) {
        return new Select(waitForVisible(locator)).getOptions();
    }

    /* ========================= FILE HANDLING ========================= */

    public static void uploadFile(By locator, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }
        waitForVisible(locator).sendKeys(file.getAbsolutePath());
    }

    public static boolean isFileDownloaded(String downloadDir, String fileName) {
        File dir = new File(downloadDir);
        File[] files = dir.listFiles();
        if (files == null) return false;

        for (File file : files) {
            if (file.getName().equalsIgnoreCase(fileName)) return true;
        }
        return false;
    }

    /* ========================= PAGE UTILITIES ========================= */

    public static void refreshPage() {
        getDriver().navigate().refresh();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static String getPageTitle() {
        return getDriver().getTitle();
    }


    /* ========================= From Old Projects ========================= */

    public static void terminateBrowser() {
        if (getDriver() != null) getDriver().quit();
    }

    // ================= ALERT HANDLING =================

    public static boolean loginPageCheck() {
        boolean login = false;
        try {
            Alert alert = getDriver().switchTo().alert();
            logger.info("Alert Found: " + alert.getText());
            alert.accept();
            login = true;
        } catch (NoAlertPresentException e) {
            login = true;
        } catch (Exception e) {
            logger.error("Login check failed", e);
        }
        return login;
    }

    public static void unExpectedAlertHandling() throws AWTException {
        try {
            Alert alert = getDriver().switchTo().alert();
            String text = alert.getText();
            if (!text.contains("Please enter value for Amount")) {
                alert.accept();
                logger.info("Unexpected Alert: " + text);
            }
        } catch (NoAlertPresentException ignored) {
        }
    }

    // ================= SCREENSHOTS =================

    public static void attachScreenshot(String tcName) throws IOException {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/" + tcName + "_" + System.currentTimeMillis() + ".png");
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Screenshot saved: " + dest.getAbsolutePath());
    }

//    public static void alertScreenshot(String tcName) throws Exception {
//        BufferedImage image = new Robot().createScreenCapture(
//                new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//        File file = new File("screenshots/" + tcName + "_" + System.currentTimeMillis() + ".png");
//        ImageIO.write(image, "png", file);
//    }

    // ================= WINDOW HANDLING =================

    public static void closeAllWindowsExceptLogin() {
        String mainWindow = getDriver().getWindowHandle();
        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                getDriver().switchTo().window(handle);
                getDriver().close();
            }
        }
        getDriver().switchTo().window(mainWindow);
    }

    // ================= ELEMENT UTILITIES =================

    public static void verifyValue(By element, String expectedValue) {
        try {
            WebElement el = getDriver().findElement(element);
            String actual = el.getText().isEmpty() ? el.getAttribute("value") : el.getText();
            if (expectedValue.contains(actual)) {
                logger.info("Verification Passed: " + actual);
            } else {
                logger.info("Verification Failed. Actual: " + actual);
            }
        } catch (Exception e) {
            logger.error("Value verification failed", e);
        }
    }

    public static void waitForTextfieldNotEmpty(By element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(d -> !getDriver().findElement(element).getAttribute("value").isEmpty());
    }

    public static void retryClick(By element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void elementStatus(By element, String type, String name) {
        WebElement el = getDriver().findElement(element);
        boolean status = switch (type.toLowerCase()) {
            case "enabled" -> el.isEnabled();
            case "displayed" -> el.isDisplayed();
            case "selected" -> el.isSelected();
            default -> false;
        };
        logger.info(name + " is " + (status ? type : "NOT " + type));
    }

    // ================= WAITS =================

    public static void waitUntilElement(By element, String action) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(element));

        if ("click".equalsIgnoreCase(action)) {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }
    }

    public static void waitForWindow(int seconds, int expectedWindows) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .until(d -> d.getWindowHandles().size() == expectedWindows);
    }

    // ================= TABLE =================

    public static void verifyTextInTableColumn(String tableId, int columnIndex, String expectedText) {
        WebElement table = getDriver().findElement(By.id(tableId));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            if (cols.size() > columnIndex && cols.get(columnIndex).getText().equals(expectedText)) {
                logger.info("Expected text found in table");
                return;
            }
        }
        logger.info("Expected text NOT found in table");
    }

    // ================= ANGULAR WAIT =================

    public static void waitForAngular() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return (window.getAllAngularTestabilities && window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1)")
                        .equals(true));
    }

    // ================= EXCEL =================

    public static void appendDataToExcel(String fileName, String sheetName, List<String> data) {
        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) sheet = workbook.createSheet(sheetName);

            int rowNum = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowNum);

            for (int i = 0; i < data.size(); i++) {
                row.createCell(i).setCellValue(data.get(i));
            }

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            logger.error("Excel write failed", e);
        }
    }

    // ================= COPY DISABLED FIELD =================

    public static String getDisabledTextValueForAngular(By element) {
        try {
            WebElement el = getDriver().findElement(element);
            new Actions(getDriver()).doubleClick(el).perform();

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String) t.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            logger.error("Copy disabled text failed", e);
        }
        return null;
    }

    public static void waitUntil(Supplier<Boolean> condition,
                                 Duration timeout,
                                 Duration pollingInterval) {

        long endTime = System.currentTimeMillis() + timeout.toMillis();

        while (System.currentTimeMillis() < endTime) {

            try {
                if (condition.get()) {
                    return;
                }

                Thread.sleep(pollingInterval.toMillis());

            } catch (Exception e) {
                // Ignore exception and continue polling
            }
        }

        throw new RuntimeException("Condition not met within timeout");
    }

}