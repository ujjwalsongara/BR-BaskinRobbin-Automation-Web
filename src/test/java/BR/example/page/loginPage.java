package BR.example.page;

import BR.example.utils.ApplicationProperties;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class loginPage extends BasePage {
    static ExtentTest test;

    @FindBy(xpath = "//*[@id=\"onload\"]/div/div/div[1]/button/span/img")
    private static WebElement cross;

    @FindBy(id = "btnSignIn")
    private static WebElement signInBtn;

    @FindBy(id = "signinOption")
    private static WebElement emailBtn;

    @FindBy(id = "txtemailmobile")
    private static WebElement enterEmail;

    @FindBy(id = "txtpassword")
    private static WebElement pass;

    @FindBy(id = "btnSave")
    private static WebElement loginButton;

    @FindBy(xpath = "//a[@href=\"javascript:__doPostBack('ctl00$btncancelpopup','')\"]")
    private static WebElement pop;

    public loginPage(WebDriver driver, String url, ExtentTest test) {
        super(driver);
        this.test = test;

        PageFactory.initElements(getWebDriver(), this);
        getWebDriver().manage().window().maximize();

        if (url == null) {
            getWebDriver().get(ApplicationProperties.INSTANCE.getBaseUrl());
        } else {
            getWebDriver().get(url);
        }

    }

    public static void login(String username, String password) throws InterruptedException {

        try {

            Thread.sleep(2000);
            test.info("Web Application Started");
            test = extent.createTest("SignIn Flow").assignCategory("Regression");

            Thread.sleep(2000);
            cross.click();
            test.pass("Clicked on Cross");

            waitWebElement(signInBtn, driver);
            signInBtn.click();
            test.pass("Clicked on sign In");

            waitWebElement(emailBtn, driver);
            emailBtn.click();
            test.pass("Clicked on sign In with email");

            waitWebElement(enterEmail, driver);
            enterEmail.clear();
            enterEmail.sendKeys(username);
            assertThat("username not sent", enterEmail.getAttribute("value"), is(username));
            test.pass("Enter UserEmail");

            waitWebElement(pass, driver);
            pass.clear();
            pass.sendKeys(password);
            assertThat("password not sent", pass.getAttribute("value"), is(password));
            test.pass("Enter Password");

            waitWebElement(loginButton, driver);
            loginButton.click();
            test.pass("Clicked on Sign In");

//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("document.getElementById('btnEmailcancelpopup').click();");
//            Thread.sleep(10000);
//            Assert.assertTrue(pop.isDisplayed(), "Close button is not visible");
//            waitWebElement(pop, driver);
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].click();", pop);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pop);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pop);
//            pop.click();
//            System.out.println("Clicked on pop");

//            Thread.sleep(10000);
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].click();", pop);

//            Actions actions = new Actions(driver);
//            actions.moveToElement(pop).click().perform();
//            test.pass("clicked on pop");

            Thread.sleep(20000);

        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}
