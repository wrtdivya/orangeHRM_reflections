package pages;

import java.io.File;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.setupteardown;
import utils.Reporting;

public class EmployeeAdditionDetailsPage extends setupteardown {

    public WebDriverWait wait;

    public EmployeeAdditionDetailsPage(ChromeDriver driver) {
        // Constructor for the EmployeeAdditionDetailsPage class

        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

    }

    By firstNameField = By.xpath("//input[@name='firstName']");
    By lastNameField = By.xpath("//input[@name='lastName']");

    By employeeIdField = By.xpath("//div[label[text()='Employee Id']]/following-sibling::div//input");
   
    By saveButton = By.xpath("//button[text()=' Save ']");
    By imageUploadButton = By.xpath("//img[@class='employee-image']/../following-sibling::button/i");
    By successMessage = By.xpath("//div[contains(@class, 'oxd-toast-content')]/p[text()='Success']");
    public String employeeDetailsURL = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/";

    public EmployeeDetailsPage addEmployeeDetails() throws InterruptedException {
        // Code to add employee details on the Employee Addition Details page

        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(employeeIdField).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        driver.findElement(employeeIdField).sendKeys(employeeId);
        WebElement uploadButton = driver.findElement(By.xpath("//input[@type='file' or @class='oxd-file-input']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block'; arguments[0].style.visibility='visible';", uploadButton);

        // 3. Build a dynamic, safe absolute file path from your project folder
        File file = new File("./src/test/resources/flower_profile.jpg"); // Put your image in this project folder

        String absolutePath = file.getAbsolutePath();

        // 4. Send the file path directly to the input field
        uploadButton.sendKeys(absolutePath);

        driver.findElement(saveButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)); // Wait until the success message is
                                                                                   // visible

        Thread.sleep(5000); // Wait for 3 seconds to allow the page to load after clicking Save

        Reporting.addAllureLog("Added Employee Details");
        return new EmployeeDetailsPage(driver); // Return the EmployeeDetailsPage instance after successful addition

    }

}
