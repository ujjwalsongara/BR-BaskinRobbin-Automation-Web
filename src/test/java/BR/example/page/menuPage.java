package BR.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class menuPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"header_other\"]/div[1]")
    private WebElement threeLine;

    @FindBy(xpath = "//*[@id=\"mySidenav\"]/div[3]/a[1]")
    private WebElement menu;

    public menuPage(WebDriver driver) {
        super(driver);
    }

    public void menuFlow() throws InterruptedException {

        try {
            test = extent.createTest("menu Flow").assignCategory("Regression");
            Thread.sleep(20000);

//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollIntoView(true);", threeLine);
//            js.executeScript("arguments[0].click();", threeLine);

            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"header_other\"]/div[1]"));
            System.out.println("Found elements: " + elements.size());
            WebElement element = elements.size() > 0 ? elements.get(0) : null;
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);

//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].click();", threeLine);

//            if (threeLine != null && threeLine.isDisplayed()) {
//                threeLine.click();
//            }
//            if (threeLine != null && threeLine.isDisplayed()) {
//                threeLine.click();
//            } else {
//                System.out.println("threeLine is null or not displayed.");
//            }

//            threeLine.click();
//            Assert.assertTrue(threeLine.isDisplayed(), "line button is not visible");
            test.pass("Clicked on Three Line");

            Thread.sleep(10000);

            List<WebElement> elementss = driver.findElements(By.xpath("//*[@id=\"mySidenav\"]/div[3]/a[1]"));
            System.out.println("Found elements: " + elements.size());
            WebElement menu = elementss.size() > 0 ? elementss.get(0) : null;
            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("arguments[0].click();", menu);

//            waitWebElement(menu, driver);
//            JavascriptExecutor jss = (JavascriptExecutor) driver;
//            jss.executeScript("arguments[0].click();", menu);
//            menu.click();

            test.pass("Clicked on Menu");

            Thread.sleep(3000);

        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}
