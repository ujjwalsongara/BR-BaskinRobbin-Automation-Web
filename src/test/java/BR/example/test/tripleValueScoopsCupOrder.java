package BR.example.test;

import BR.example.BaseTest;
import BR.example.page.*;
import BR.example.page.tripleScoopesCupFlowPage.tripleCupOrderPage;
import BR.example.page.tripleScoopesCupFlowPage.tripleCupTimeSetUpPage;
import BR.example.page.tripleScoopesCupFlowPage.tripleValueScoopCupPage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class tripleValueScoopsCupOrder extends BaseTest {

    private loginPage loginPage;
    private menuPage menuPage;
    private tripleValueScoopCupPage tripleValueScoopCupPage;
    private tripleCupTimeSetUpPage tripleCupTimeSetUpPage;
    private tripleCupOrderPage tripleCupOrderPage;
    private paymentPage paymentPage;
    private Logger log = LoggerFactory.getLogger(loginTest.class);

    @Test
    @Parameters("url")
    public void couponFlow(@Optional String url) throws InterruptedException {
        log.info("test started");
        ExtentTest test = extent.createTest("Login Test Flow");

        WebDriver driver = getWebDriver();

        loginPage = new loginPage(driver, url, test);
        loginPage.login(username, password);

        menuPage = new menuPage(driver);
        menuPage.menuFlow();

        tripleValueScoopCupPage = new tripleValueScoopCupPage(driver);
        tripleValueScoopCupPage.tripleValueScoopCupFlow();

        tripleCupTimeSetUpPage = new tripleCupTimeSetUpPage(driver);
        tripleCupTimeSetUpPage.TimePickerFlow();

       tripleCupOrderPage = new tripleCupOrderPage(driver);
       tripleCupOrderPage.tripleCupOrderFlow();

        paymentPage = new paymentPage(driver);
        paymentPage.paymentFlow();

    }

}
