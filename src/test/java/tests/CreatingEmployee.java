package tests;

import org.testng.annotations.Test;

import base.setupteardown;
import pages.HomePage;

public class CreatingEmployee  extends setupteardown {

    @Test
    public void createEmployee() throws InterruptedException {
        // Code to create a new employee in the application
        // This method can be implemented to fill out the employee creation form and submit it
        HomePage homePage = new HomePage(driver);
        homePage.clickPIMButton();
    }

}
