package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import base.setupteardown;
import utils.Reporting;

public class LoginPage extends setupteardown {
    

    public LoginPage(ChromeDriver driver) {
        this.driver = driver;
    }

    By usernameField = By.xpath("//input[@name='username']");
    By passwordField = By.xpath("//input[@name='password']");
    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    By invalidCredentialsAlert = By.xpath("//p[contains(@class, 'alert')]");

    public LoginPage enterUsername(String username) {
        // Code to enter the username in the login page

        Reporting.addAllureLog("Entering username: " + username);

        driver.findElement(usernameField).sendKeys(username);

        return this; // Return the current instance of LoginPage for method chaining

    }

    public LoginPage enterPassword(String password) {
        // Code to enter the password in the login page
        Reporting.addAllureLog("Entering password: " + password);

        driver.findElement(passwordField).sendKeys(password);
        return this; // Return the current instance of LoginPage for method chaining
    }

    public HomePage clickLoginButton() throws InterruptedException {
        // Code to click the login button on the login page

        Reporting.addAllureLog("Clicking login button.");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Thread.sleep(5000); // Wait for 5 seconds to allow the page to load after login

        return new HomePage(driver); // Return the HomePage instance after successful login
    }

  

    public String getErrorMessageText() {
        try {
            return driver.findElement(invalidCredentialsAlert).getText();
        } catch (Exception e) {
            return e.getMessage();        }
}
}
