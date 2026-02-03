package com.kathir.pages.mmt;

import com.kathir.base.BasePage;
import com.kathir.context.TestContext;
import com.kathir.driver.DriverManager;
import com.kathir.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
import java.util.List;

public class FlightsPage extends BasePage {

    public FlightsPage(TestContext context) {   // ✅ Pico will inject this
        super(context);
    }
    // Close popup if present
    private final By closePopup = By.xpath("//span[@class='commonModal__close']");

    // Trip type
    private final By roundTrip = By.xpath("//li[contains(@data-cy,'roundTrip')]");

    // From/To inputs
    private final By fromCityInput = By.id("fromCity");
    private final By toCityInput = By.id("toCity");

    private final By fromInputText = By.xpath("//input[@placeholder='From']");
    private final By toInputText = By.xpath("//input[@placeholder='To']");

    // Suggestions list
    private final By suggestionItems = By.xpath("//ul[@role='listbox']//li");

    // Calendar
    private final By departureLabel = By.xpath("//label[@for='departure']");
    private final By returnLabel = By.xpath("//label[@for='return']");

    // Lowest fare date blocks (MakeMyTrip shows price inside calendar)
    private final By calendarPriceElements = By.xpath("//div[contains(@class,'DayPicker-Day')]//p[contains(@class,'todayPrice') or contains(@class,'price')]");

    // Search
    private final By searchBtn = By.xpath("//a[contains(@class,'primaryBtn') and contains(text(),'Search')]");

    // Results validation
    private final By resultsContainer = By.xpath("//div[contains(@class,'listingRhs')] | //div[contains(@class,'flightResult')]");


    public void closeAnyPopup() {
        try {
            DriverManager.getDriver().findElement(closePopup).click();
        } catch (Exception ignored) {}
    }

    public void selectRoundTrip() {
        closeAnyPopup();
        click(roundTrip);
    }

    public void selectFromCity(String city) {
        click(fromCityInput);
        type(fromInputText, city);
        pickFirstSuggestion();
    }

    public void selectToCity(String city) {
        click(toCityInput);
        type(toInputText, city);
        pickFirstSuggestion();
    }

    private void pickFirstSuggestion() {
        List<WebElement> items = DriverManager.getDriver().findElements(suggestionItems);
        if (!items.isEmpty()) {
            items.get(0).click();
        }
    }

    public void openDepartureCalendar() {
        click(departureLabel);
    }

    public void openReturnCalendar() {
        click(returnLabel);
    }

    /**
     * Select lowest price day visible in current calendar month view.
     * Works when the price exists in date cell.
     */
    public void selectLowestPriceDateInCurrentMonth() {
        List<WebElement> prices = DriverManager.getDriver().findElements(calendarPriceElements);

        WebElement lowest = prices.stream()
                .filter(e -> e.getText() != null && e.getText().replaceAll("[^0-9]", "").length() > 0)
                .min(Comparator.comparingInt(e -> Integer.parseInt(e.getText().replaceAll("[^0-9]", ""))))
                .orElseThrow(() -> new RuntimeException("No calendar price elements found"));

        // Usually price element is inside day cell - click parent container
        lowest.click();
    }

    public void clickSearch() {
        click(searchBtn);
    }

    public boolean isResultsDisplayed() {
        try {
            WaitUtil.waitForVisible(resultsContainer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

