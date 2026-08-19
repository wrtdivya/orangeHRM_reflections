package base;

import java.util.Random;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;


import utils.Reporting;
import utils.ScreenRecorderUtil;

public class setupteardown {

    public ChromeDriver driver;

      public static String employeeId = "11" + (new Random().nextInt(9000) + 1000); // Example employee ID
    public static String firstName = "John"; // Example first name
    public static String lastName = "Doe"; // Example last name
    public static String jobRole = "Software Engineer"; // Example last name
public static String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    @BeforeClass

    public void setup() throws Exception {

        
        ScreenRecorderUtil.startRecord("OrangeHRM_Login_Test");

        driver = new ChromeDriver();
        driver.get(url);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        Reporting.addAllureLog("Browser initialized and navigated to target URL.");

    }

    @AfterClass
    public void teardown() throws Exception {
        if (driver != null) {
            driver.quit();
            Reporting.addAllureLog("Browser closed after test execution.");
            ScreenRecorderUtil.stopRecord();
        }
    }

}
