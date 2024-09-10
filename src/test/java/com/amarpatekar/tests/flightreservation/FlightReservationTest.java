package com.amarpatekar.tests.flightreservation;

import com.amarpatekar.pages.flightreservation.*;
import com.amarpatekar.tests.AbstractTest;
import com.amarpatekar.tests.flightreservation.model.FlightReservationTestData;
import com.amarpatekar.util.Config;
import com.amarpatekar.util.Constants;
import com.amarpatekar.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
        Assert.assertNotNull(testData, "testData is null! Check the testDataPath or JsonUtil implementation.");
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.manage().window().maximize();
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest(){
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.passengerCount());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        FlightSelectionPage flightsSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightsConfirmationPage flightsConfirmationPage = new FlightsConfirmationPage(driver);
        Assert.assertTrue(flightsConfirmationPage.isAt());
        Assert.assertEquals(flightsConfirmationPage.getPrice(), testData.expectedPrice());

    }




}
