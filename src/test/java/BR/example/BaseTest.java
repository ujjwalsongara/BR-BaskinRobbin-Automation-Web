package BR.example;

import BR.example.utils.ApplicationProperties;
import BR.example.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {

    public static ExtentReports extent;
    public static ExtentTest test;
    public long beforeTime = System.nanoTime();
    public long afterTime = System.nanoTime();
    public String username = ApplicationProperties.INSTANCE.getUsername();
    public String password = ApplicationProperties.INSTANCE.getPassword();
    protected WebDriver driver;
    Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUpSuite() {
        extent = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void tearDownSuite() {
        extent.flush(); // writes everything to the report
    }

    @BeforeSuite
    public void beforeSuite() {
        log.info("Test Started");
    }

    @AfterSuite
    public void afterSuite() {
        log.info("Test case is End");
        log.info("total execution " + (afterTime - beforeTime) + "ms");
    }

//    @AfterMethod
//    public void tearDownMethod(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            test.fail(result.getThrowable());
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            test.pass("Test passed");
//        }
//    }


    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        // Log status to report
        if (result.getStatus() == ITestResult.FAILURE) {
            log.error(result.getThrowable().getMessage()); // Log error to console
            if (test != null) { // Null check for test object
                test.fail(result.getThrowable());
            } else {
                log.warn("ExtentTest object is null. Cannot report failure.");
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            if (test != null) { // Null check for test object
                test.pass("Test passed");
            } else {
                log.warn("ExtentTest object is null. Cannot report success.");
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            if (test != null) { // Null check for test object
                test.skip(result.getThrowable());
            } else {
                log.warn("ExtentTest object is null. Cannot report skip.");
            }
        }
        // --- WebDriver Cleanup ---
        log.info("Executing WebDriver cleanup in tearDownMethod...");
        try {
            if (driver != null) {
                driver.quit(); // Use quit() to close all windows and end the WebDriver session
                driver = null; // Set driver to null after quitting to prevent "WebDriver already closed" errors
                log.info("WebDriver successfully closed and quit.");
            } else {
                log.info("WebDriver instance was null, no browser to close.");
            }
        } catch (Exception e) {
            log.error("Error during WebDriver cleanup: " + e.getMessage(), e);
            // Don't re-throw here unless you want to fail the entire build for cleanup errors
        }
    }

    public static void waitWebElement(WebElement element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebDriver getWebDriver() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
//        option.addArguments("--incognito");
//        option.addArguments("--headless=new");
        driver = new ChromeDriver(option);
        return driver;

    }
}

