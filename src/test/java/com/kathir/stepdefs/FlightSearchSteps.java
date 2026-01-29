package com.kathir.stepdefs;


import com.kathir.pages.mmt.FlightsPage;
import io.cucumber.java.en.*;

import org.testng.Assert;

public class FlightSearchSteps {

    private final FlightsPage flightsPage = new FlightsPage();

    @And("user selects round trip")
    public void user_selects_round_trip() {
        flightsPage.selectRoundTrip();
    }

    @And("user selects from city {string}")
    public void user_selects_from_city(String fromCity) {
        flightsPage.selectFromCity(fromCity);
    }

    @And("user selects to city {string}")
    public void user_selects_to_city(String toCity) {
        flightsPage.selectToCity(toCity);
    }

    @And("user selects lowest price departure date in current month")
    public void user_selects_lowest_price_departure_date_in_current_month() {
        flightsPage.openDepartureCalendar();
        flightsPage.selectLowestPriceDateInCurrentMonth();
    }

    @And("user selects lowest price return date in current month")
    public void user_selects_lowest_price_return_date_in_current_month() {
        flightsPage.openReturnCalendar();
        flightsPage.selectLowestPriceDateInCurrentMonth();
    }

    @And("user clicks search flights")
    public void user_clicks_search_flights() {
        flightsPage.clickSearch();
    }

    @Then("user should see flight results page")
    public void user_should_see_flight_results_page() {
        Assert.assertTrue(flightsPage.isResultsDisplayed(), "Flight results not displayed");
    }
}
