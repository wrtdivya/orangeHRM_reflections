package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.setupteardown;
import utils.Reporting;

public class EmployeeDetailsPage extends setupteardown {

    public WebDriverWait wait;
    public EmployeeDetailsPage(ChromeDriver driver) {
        // Constructor for EmployeeDetailsPage

        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");
    public boolean isEmployeeDetailsPageDisplayed() {
        // Code to check if the Employee Details page is displayed
        String currentURL = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader));
        
        return currentURL.contains("viewPersonalDetails");
    }

    By jobButton = By.xpath("//a[text()='Job']");


    public EmployeeDetailsPage clickJobButton() {
        // Code to click the Job button on the Employee Details page
        driver.findElement(jobButton).click();

        Reporting.addAllureLog("Clicked Job tile");
        return this;
    }

By jobDetailsHeader = By.xpath("//h6[text()='Job']");

    public boolean isJobDetailsPageDisplayed() {
        // Code to check if the Job Details page is displayed
        String currentURL = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobDetailsHeader));

        Reporting.addAllureLog("Job details displayed");
        
        return currentURL.contains("viewJobDetails");
    }

    By reqRoleField = By.xpath("//div[@role='listbox']/div[@role='option']/span[text()='"+jobRole+"']");

    By jobTitleDropDown = By.xpath("//label[text()='Job Title']/../following-sibling::div");
 By formLoader = By.className("oxd-form-loader");
    public EmployeeDetailsPage selectRequiredRole()  {
        // Code to select the required role from the dropdown

        

       wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
         
        driver.findElement(jobTitleDropDown).click();
         
          wait.until(ExpectedConditions.visibilityOfElementLocated(reqRoleField));
         
        driver.findElement(reqRoleField).click();

        Reporting.addAllureLog("selected role");
        return this; // Return the current instance of EmployeeDetailsPage for method chaining
    }

    By employmentStatusDropDown = By.xpath("//label[text()='Employment Status']/../following-sibling::div");

    By statusField = By.xpath("//div[@role='listbox']/div[@role='option']/span[text()='Freelance']");

    public EmployeeDetailsPage selectStatus() {
        // Code to select the status from the dropdown
          
        driver.findElement(employmentStatusDropDown).click();
         
        driver.findElement(statusField).click();

        Reporting.addAllureLog("selected status");
        return this; // Return the current instance of EmployeeDetailsPage for method chaining
    }

    By saveButton = By.xpath("//button[text()=' Save ']");
    By employeeListLocator = By.linkText("Employee List");
     By successMessage = By.xpath("//div[contains(@class, 'oxd-toast-content')]/p[text()='Success']");
    public EmployeeDetailsPage clickSaveButton() {
        // Code to click the Save button on the Job Details page
        driver.findElement(saveButton).click();
         wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        try {
             driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
           e.getMessage();
        }

           driver.findElement(employeeListLocator).click();
           Reporting.addAllureLog("Updated Job details");
        return new EmployeeDetailsPage(driver); // Return the current instance of EmployeeDetailsPage for method chaining
    }


   
  

}
