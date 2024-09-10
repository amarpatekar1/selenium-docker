package com.amarpatekar.pages.flightreservation;

import com.amarpatekar.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationPage extends AbstractPage {

    @FindBy(id= "firstName")
    private WebElement firstNameInput;

    @FindBy(id= "lastName")
    private WebElement lastNameInput;

    @FindBy(id= "email")
    private WebElement emailInput;

    @FindBy(id= "password")
    private WebElement passwordInput;

    @FindBy(name= "street")
    private WebElement streetNameInput;

    @FindBy(name= "city")
    private WebElement cityNameInput;

    @FindBy(name= "zip")
    private WebElement zipInput;

    @FindBy(id= "register-btn")
    private WebElement registerButton;

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
        return this.firstNameInput.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName){
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
    }
    public void enterCredentials(String email, String password){
        this.emailInput.sendKeys(email);
        this.passwordInput.sendKeys(password);
    }

    public void enterAddress(String street, String city, String zip){
        this.streetNameInput.sendKeys(street);
        this.cityNameInput.sendKeys(city);
        this.zipInput.sendKeys(zip);
    }

    public void register(){
        this.registerButton.click();
    }
}
