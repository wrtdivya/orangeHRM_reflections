package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.setupteardown;
import utils.Reporting;

public class LogoutPage extends setupteardown{
public WebDriverWait wait ;
    public LogoutPage(ChromeDriver driver){

        this.driver = driver;

     this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }


    By profileButton = By.xpath("//span[@class ='oxd-userdropdown-tab']");

    By logOutButton = By.linkText("Logout");




    public LogoutPage loggingOut(){

        driver.findElement(profileButton).click();

        driver.findElement(logOutButton).click();

         wait.until(ExpectedConditions.urlToBe(url));

         Reporting.addAllureLog("Logged out");

        return this;
    }
}
