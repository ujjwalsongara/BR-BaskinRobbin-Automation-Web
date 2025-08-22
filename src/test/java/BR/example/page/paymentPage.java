package BR.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class paymentPage extends BasePage {

    @FindBy(xpath = "")
    private WebElement payment;

    public paymentPage(WebDriver driver) {
        super(driver);
    }

    public void paymentFlow() throws InterruptedException {

        try {
            test = extent.createTest("Payment Flow").assignCategory("Regression");
            Thread.sleep(2000);

            List<WebElement> elementPayment = driver.findElements(By.xpath("//*[@id=\"paytype_2_2\"]"));
            System.out.println("Found elements: " + elementPayment.size());
            WebElement OnlinePayment = elementPayment.size() > 0 ? elementPayment.get(0) : null;
            JavascriptExecutor payment = (JavascriptExecutor) driver;
            payment.executeScript("arguments[0].click();", OnlinePayment);

            test.pass("Clicked on Payment method");

            Thread.sleep(3000);

            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("window.scrollBy(0, 900)");

            Thread.sleep(20000);
        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}
