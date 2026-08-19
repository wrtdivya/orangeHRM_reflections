package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.setupteardown;
import utils.Reporting;

public class HomePage extends setupteardown {

    public WebDriverWait wait;

    public HomePage(ChromeDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

    public boolean isDashboardDisplayed() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));

        Reporting.addAllureLog("Dahsboard displayed");

        try {
            return driver.findElement(dashboardHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    By pimButton = By.xpath("//span[text()='PIM']");
    By pimHeader = By.xpath("//h6[text()='PIM']");

    public HomePage clickPIMButton() throws InterruptedException {
        // Code to click the PIM button on the home page
        driver.findElement(pimButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(pimHeader));

        // Thread.sleep(3000); // Wait for 3 seconds to allow the page to load after
        // clicking PIM

        Reporting.addAllureLog("PIM Button clicked");
        return this;// Return the HomePage instance after successful click
    }

    By addEmployeeButton = By.linkText("Add Employee");

    public String addEmployeeURL = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee";

    public EmployeeAdditionDetailsPage clickAddEmployeeButton() {

        driver.findElement(addEmployeeButton).click();
        wait.until(ExpectedConditions.urlToBe(addEmployeeURL));
Reporting.addAllureLog("Add Employee button is clicked");
        return new EmployeeAdditionDetailsPage(driver);

    }

}
