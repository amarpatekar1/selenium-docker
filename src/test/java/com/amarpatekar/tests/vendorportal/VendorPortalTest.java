package com.amarpatekar.tests.vendorportal;

import com.amarpatekar.pages.vendorportal.DashboardPage;
import com.amarpatekar.pages.vendorportal.LoginPage;
import com.amarpatekar.tests.AbstractTest;
import com.amarpatekar.tests.vendorportal.model.VendorPortalTestData;
import com.amarpatekar.util.Config;
import com.amarpatekar.util.Constants;
import com.amarpatekar.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }


    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(),testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        dashboardPage.searchOrderHistory("adams");
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), 8);


    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }


}
