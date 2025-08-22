package BR.example.page.tripleScoopesCupFlowPage;

import BR.example.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class tripleCupOrderPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"modf_5576\"]/div/div[3]/span/i")
    private WebElement plusIcon;

    @FindBy(id = "btnCart")
    private WebElement addToCart;

    @FindBy(id = "btnGoToCart")
    private WebElement viewCart;

    @FindBy(xpath = "//a[@class='checkoutLinkTag btn btn-primary btn-lg mb-2 mt-2']")
    private WebElement checkout;

    public tripleCupOrderPage(WebDriver driver) {
        super(driver);
    }

    public void tripleCupOrderFlow() throws InterruptedException {
        try {
            test = extent.createTest("Add triple Scoop Cup Flow").assignCategory("Regression");

            Thread.sleep(4000);

//            JavascriptExecutor scroll = (JavascriptExecutor) driver;
//            scroll.executeScript("window.scrollBy(0,700)");

            WebElement item = driver.findElement(By.xpath("//*[@id=\"modf_5576\"]/div/img")); // element inside side screen
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", item);

            Thread.sleep(2000);

            List<WebElement> plusIcons = driver.findElements(By.xpath("//*[@id=\"modf_5576\"]/div/div[3]/span/i"));
            if (plusIcons.size() > 0) {
                for (int i = 0; i < plusIcons.size(); i++) {
                    WebElement plusIcon = plusIcons.get(i);
                    waitWebElement(plusIcon, driver);

                    plusIcon.click();
                    test.pass("Clicked on Add Cup icon number: " + (i + 1));

                    Thread.sleep(2000); // Not recommended, better use WebDriverWait
                }
            } else {
                test.fail("No plus icons found on the page.");
            }

            test.pass("Clicked on Add Triple Cup");

            test = extent.createTest("Confirm Order Flow").assignCategory("Regression");

            Thread.sleep(2000);

            List<WebElement> addToCart = driver.findElements(By.id("btnCart"));
            System.out.println("Found elements: " + addToCart.size());
            WebElement addToCartBtn = addToCart.size() > 0 ? addToCart.get(0) : null;
            JavascriptExecutor addCartBtn = (JavascriptExecutor) driver;
            addCartBtn.executeScript("arguments[0].click();", addToCartBtn);

            test.pass("Clicked on add To Cart");

            Thread.sleep(2000);

            List<WebElement> viewCart = driver.findElements(By.id("btnGoToCart"));
            System.out.println("Found elements: " + viewCart.size());
            WebElement viewCartBtn = viewCart.size() > 0 ? viewCart.get(0) : null;
            JavascriptExecutor view = (JavascriptExecutor) driver;
            view.executeScript("arguments[0].click();", viewCartBtn);

            test.pass("Clicked on View Cart");

            Thread.sleep(10000);
            JavascriptExecutor jsss = (JavascriptExecutor) driver;
            jsss.executeScript("window.scrollBy(0, 9000)");

            Thread.sleep(2000);

            List<WebElement> checkout = driver.findElements(By.xpath("//a[@class='checkoutLinkTag btn btn-primary btn-lg mb-2 mt-2']"));
            System.out.println("Found elements: " + checkout.size());
            WebElement checkoutBtn = checkout.size() > 0 ? checkout.get(0) : null;
            JavascriptExecutor checkoutBtnn = (JavascriptExecutor) driver;
            checkoutBtnn.executeScript("arguments[0].click();", checkoutBtn);

            test.pass("'Clicked on Checkout");

            Thread.sleep(30000);

        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}

