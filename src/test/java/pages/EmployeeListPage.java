package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.setupteardown;
import utils.Reporting;

public class EmployeeListPage extends setupteardown{

    public WebDriverWait wait;
    public EmployeeListPage(ChromeDriver driver){
    this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

    }



    By employeeInfo = By.xpath("//h5[text()='Employee Information']");
    public EmployeeListPage clickEmployeeListPage(){

     

        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeInfo));
Reporting.addAllureLog("Clicked employee list page");
        return this;
    }

    By employeeIDText = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");

    By searchButton = By.xpath("//button[text()=' Search ']");
    public EmployeeListPage searchForEmployee(){

        driver.findElement(employeeIDText).sendKeys(employeeId);

        driver.findElement(searchButton).click();

        Reporting.addAllureLog("Searched employee");

        return this;
    }


    By employeeIDMatchLocator = By.xpath("//div[@data-v-6c07a142 and text()='"+employeeId+"']");

    By deleteButton = By.xpath("//div[@data-v-6c07a142 and text()='"+employeeId+"']/../following-sibling::div//button/i[contains(@class, 'bi-trash')]");

    By deleteConfirmButton = By.xpath("//button[contains(., 'Yes, Delete')]");

    By successMessage = By.xpath("//div[contains(@class, 'oxd-toast-content')]/p[text()='Success']");
  
    public void deleteEmployee(){

        String empID = driver.findElement(employeeIDMatchLocator).getText();


        if(empID.contains(employeeId))
        {

            driver.findElement(deleteButton).click();

            driver.findElement(deleteConfirmButton).click();



        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

        Reporting.addAllureLog("Deleted employee");

        System.out.println("Deleted successfully");



    }

}
