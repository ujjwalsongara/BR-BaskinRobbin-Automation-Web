package BR.example.test;

import BR.example.BaseTest;
import BR.example.page.loginPage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class loginTest extends BaseTest {

    private loginPage loginPage;
    private Logger log = LoggerFactory.getLogger(loginTest.class);

    @Test
    @Parameters("url")
    public void loginFlow(@Optional String url) throws InterruptedException {
        log.info("test started");
        ExtentTest test = extent.createTest("Login Test Flow");

        WebDriver driver = getWebDriver();

        loginPage = new loginPage(driver, url, test);
        loginPage.login(username, password);

    }
}