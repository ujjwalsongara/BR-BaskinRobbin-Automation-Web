package BR.example.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class timeSetupPage extends BasePage {

    @FindBy(id = "time")
    private WebElement timePicker;

    @FindBy(xpath = "//*[@id=\"SchedulePickup\"]/div/div/div[3]/button")
    private WebElement confirmBtn;

    @FindBy(xpath = "//*[@id=\"modf_15759\"]/div/img")
    private WebElement crunchItem;

//    @FindBy(xpath = "//*[@id=\"modf_5576\"]/div/div[3]/span/i")
//    private WebElement plusIcon;

    @FindBy(id = "btnCart")
    private WebElement addToCart;

    @FindBy(id = "btnGoToCart")
    private WebElement viewCart;

    @FindBy(xpath = "//a[@class='checkoutLinkTag btn btn-primary btn-lg mb-2 mt-2']")
    private WebElement checkout;

    public timeSetupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(), this);
        getWebDriver().manage().window().maximize();
    }

    public void TimeSetupFlow() throws InterruptedException {

        try {
            test = extent.createTest("Time Setup Flow").assignCategory("Regression");
            Thread.sleep(2000);

            waitWebElement(timePicker, driver);
            timePicker.click();
            test.pass("clicked on TimePicker");
            System.out.println("Clicked on time");

            String currentTime = timePicker.getAttribute("value");

            // Fallback if empty
            if (currentTime == null || currentTime.isEmpty()) {
                currentTime = "09:00";
            }

            // Parse and increment 1 hour
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(currentTime, formatter);
            LocalTime updatedTime = time.plusHours(1);

            String newTime = updatedTime.format(formatter);

            // Set new time via JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = arguments[1];", timePicker, newTime);

            // Optionally trigger any change event
            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", timePicker);

            waitWebElement(confirmBtn, driver);
            confirmBtn.click();
            test.pass("Clicked on Confirm");

            waitWebElement(crunchItem, driver);
            crunchItem.click();
            test.pass("Clicked on Crunch");

//            Thread.sleep(4000);
//            JavascriptExecutor scroll = (JavascriptExecutor) driver;
//            scroll.executeScript("window.scrollBy(0,700)");
//
//            Thread.sleep(2000);
//
//            waitWebElement(plusIcon, driver);
//            plusIcon.click();
//            Thread.sleep(2000);
//            plusIcon.click();
//            Thread.sleep(2000);
//            plusIcon.click();
//            test.pass("Clicked on Add Triple Cup");


            test = extent.createTest("Confirm Order Flow").assignCategory("Regression");

            waitWebElement(addToCart, driver);
            addToCart.click();
            test.pass("Clicked on add To Cart");

            waitWebElement(viewCart, driver);
            viewCart.click();
            test.pass("Clicked on View Cart");

            Thread.sleep(10000);
            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("window.scrollBy(0, 9000)");

            waitWebElement(checkout, driver);
            checkout.click();
            test.pass("'Clicked on Checkout");

            Thread.sleep(30000);
        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}
