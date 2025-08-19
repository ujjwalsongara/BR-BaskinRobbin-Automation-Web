package BR.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class applyCouponPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"btnAppPromoMain\"]")
    private WebElement applyCoupon;

    @FindBy(xpath = "//*[@id=\"promotion_277\"]/div")
    private WebElement WelcomeOffer;

    @FindBy(xpath = "//*[@id=\"apply_promo\"]/div/div/div[2]/div[1]/div[2]/button")
    private WebElement applyCouponBTn;

    public applyCouponPage(WebDriver driver) {
        super(driver);
    }

    public void applyCouponFlow() throws InterruptedException {
        try {
            test = extent.createTest("Apply Coupon Flow").assignCategory("Regression");
            Thread.sleep(2000);

            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"btnAppPromoMain\"]"));
            System.out.println("Found elements: " + elements.size());
            WebElement element = elements.size() > 0 ? elements.get(0) : null;
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);

            test.pass("Clicked on Apply Coupon");

            Thread.sleep(3000);

            List<WebElement> elementItem = driver.findElements(By.xpath("//*[@id=\"promotion_277\"]/div"));
            System.out.println("Found elements: " + elementItem.size());
            WebElement elementss = elementItem.size() > 0 ? elementItem.get(0) : null;
            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("arguments[0].click();", elementss);

            test.pass("Clicked on Welcome Offer");

            Thread.sleep(3000);

            List<WebElement> elementCoupon = driver.findElements(By.xpath("//*[@id=\"apply_promo\"]/div/div/div[2]/div[1]/div[2]/button"));
            System.out.println("Found elements: " + elementCoupon.size());
            WebElement elementsss = elementCoupon.size() > 0 ? elementCoupon.get(0) : null;
            JavascriptExecutor applyCoupon = (JavascriptExecutor) driver;
            applyCoupon.executeScript("arguments[0].click();", elementsss);

            test.pass("Clicked on Apply");

            Thread.sleep(7000);

            WebElement couponElement = driver.findElement(By.xpath("//*[@id=\"promoName\"]"));
            String actualText = couponElement.getText();

            Assert.assertTrue(actualText.contains("Welcome Offer - 1st Order 50% discount"),
                    "Coupon text not found in element!");

            test.pass("Coupon code selected");

            Thread.sleep(10000);

        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}
