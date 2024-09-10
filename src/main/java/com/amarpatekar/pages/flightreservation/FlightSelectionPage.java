package com.amarpatekar.pages.flightreservation;

import com.amarpatekar.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;



import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {
    @FindBy(name ="departure-flight")
    private List<WebElement> departureFlightsOptions;

    @FindBy(name ="arrival-flight")
    private List<WebElement> arrivalFlightsOptions;

    @FindBy(id ="confirm-flights")
    private WebElement confirmFlightsButton;

    public FlightSelectionPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
        return this.confirmFlightsButton.isDisplayed();
    }

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, departureFlightsOptions.size());
        this.departureFlightsOptions.get(random).click();
        this.arrivalFlightsOptions.get(random).click();
    }

    public void confirmFlights(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-flights")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        this.confirmFlightsButton.click();
    }
}
