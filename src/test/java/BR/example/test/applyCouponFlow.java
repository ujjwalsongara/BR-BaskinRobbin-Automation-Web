package BR.example.test;

import BR.example.BaseTest;
import BR.example.page.*;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class applyCouponFlow extends BaseTest {

    private loginPage loginPage;
    private menuPage menuPage;
    private orderPickupPage orderPickupPage;
    private timeSetupPage TimeSetupPage;
    private applyCouponPage applyCouponPage;
//    private paymentPage paymentPage;
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

        orderPickupPage = new orderPickupPage(driver);
        orderPickupPage.pickupFlow();

        TimeSetupPage = new timeSetupPage(driver);
        TimeSetupPage.TimeSetupFlow();

        applyCouponPage = new applyCouponPage(driver);
        applyCouponPage.applyCouponFlow();

//        paymentPage = new paymentPage(driver);
//        paymentPage.paymentFlow();

    }
}

