package BR.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class rewardsPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"ContentPlaceHolder1_Rewards_section\"]/div/div/div[2]/div/div[2]/div/div/div[3]/a")
    private WebElement getReward;

    public rewardsPage(WebDriver driver) {
        super(driver);
    }

    public void rewardFlow() throws InterruptedException {
        try {
            test = extent.createTest("Reward Flow").assignCategory("Regression");
            Thread.sleep(2000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)");

            Thread.sleep(4000);

            List<WebElement> getReward = driver.findElements(By.xpath("//*[@id=\"ContentPlaceHolder1_Rewards_section\"]/div/div/div[2]/div/div[2]/div/div/div[3]/a"));
            System.out.println("Found elements: " + getReward.size());
            WebElement reward = getReward.size() > 0 ? getReward.get(0) : null;
            JavascriptExecutor applyCoupon = (JavascriptExecutor) driver;
            applyCoupon.executeScript("arguments[0].click();", reward);
            test.pass("Clicked on Get Rewards");

            Thread.sleep(5000);

        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}
