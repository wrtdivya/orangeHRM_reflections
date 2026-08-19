package tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.setupteardown;
import io.cucumber.java.an.E;
import io.restassured.response.Response;
import pages.APIValidation;
import pages.EmployeeAdditionDetailsPage;
import pages.EmployeeDetailsPage;
import pages.EmployeeListPage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import utils.Reporting;

public class LoginTest extends setupteardown {

   

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Enter username and password
        loginPage.enterUsername("Admin")
                .enterPassword("admin123")
                .clickLoginButton().isDashboardDisplayed();

        // Add assertions to verify successful login
        Assert.assertTrue(homePage.isDashboardDisplayed(), "Dashboard is not displayed after login.");
    }

    @Test(priority = 2)
    public void createEmployee() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.clickPIMButton().clickAddEmployeeButton();
    }

    

    @Test(priority = 3)
    public void addEmployee() throws InterruptedException {
        EmployeeAdditionDetailsPage employeeAdditionDetailsPage = new EmployeeAdditionDetailsPage(driver);
        employeeAdditionDetailsPage.addEmployeeDetails();
    }

    @Test(priority = 4)
    public void verifyIfEmployeeDetailsPageDisplayed()  {

        EmployeeDetailsPage employeeDetailsPage = new EmployeeDetailsPage(driver);

        employeeDetailsPage.isEmployeeDetailsPageDisplayed();
        
    }

    @Test(priority = 5)
     public void  editEmployeeDetails() {
EmployeeDetailsPage employeeDetailsPage = new EmployeeDetailsPage(driver);

employeeDetailsPage.clickJobButton().selectRequiredRole().selectStatus().clickSaveButton();
     }



     @Test(priority = 6)
     public void apiValidation(){

        
        APIValidation apiValidation = new APIValidation();

        Response apiResponse = apiValidation.verifyEmployeeRecordOnBackend();

       String apiFullName = apiResponse.jsonPath().getString("name");
        String apiJobRole = apiResponse.jsonPath().getString("job");
        String apiEmpID = apiResponse.jsonPath().getString("employeeId");
        
     
        String expectedFullName = firstName + " " + lastName;

       
        Assert.assertEquals(apiFullName, expectedFullName, "FAIL: Combined employee target name mismatch!");
        Assert.assertEquals(apiJobRole, jobRole, "FAIL: Target job position mapping mismatch!");
        Assert.assertEquals(apiEmpID, employeeId, "FAIL: Target dynamic employee identifier mismatch!");
        
        Reporting.addAllureLog("SUCCESS: Backend API dataset verification matches the active UI execution state.");
    }
     

     @Test(priority = 7)

     public void deleteEmployee(){

        EmployeeListPage employeeListPage = new EmployeeListPage(driver);

        employeeListPage.clickEmployeeListPage().searchForEmployee().deleteEmployee();
     }

     @Test(priority = 8)

     public void logout(){

        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.loggingOut();
     }

}
