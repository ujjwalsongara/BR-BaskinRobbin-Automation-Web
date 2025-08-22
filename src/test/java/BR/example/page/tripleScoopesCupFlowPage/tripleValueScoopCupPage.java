package BR.example.page.tripleScoopesCupFlowPage;

import BR.example.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class tripleValueScoopCupPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"divcustomize\"]/div[2]/div/img")
    private WebElement CupsOrder;

    @FindBy(xpath = "//*[@id=\"divcustomize\"]/div[8]/div/div/div[3]/a")
    private WebElement item;

    @FindBy(xpath = "//a[@radio_id=\"Pickup\"]")
    private WebElement pickUpBtnn;

//    @FindBy(id = "ContentPlaceHolder1_openmysavedrestaurant")
//    private WebElement locationFav;

    //    @FindBy(xpath = "//*[@id=\"id_openListStores\"]/a")
    @FindBy(id = "id_openListStores")
    private WebElement listStores;

    @FindBy(xpath = "//*[@id=\"bindrestaurant\"]/div[1]/div/div/a[2]")
//    @FindBy(xpath= "//*[@id=\"mysavedrestrns\"]/div[2]/div/div/a[2]")
//    @FindBy(xpath = "//*[@id=\"mysavedrestrns\"]/div/div/div/a[2]")
    private WebElement locationSelect;

    public tripleValueScoopCupPage(WebDriver driver) {
        super(driver);
    }

    public void tripleValueScoopCupFlow() throws InterruptedException {
        try {
            test = extent.createTest("PickUp Flow").assignCategory("Regression");
            Thread.sleep(3000);

            List<WebElement> cup = driver.findElements(By.xpath("//*[@id=\"divcustomize\"]/div[2]/div/img"));
            System.out.println("Found elements: " + cup.size());
            WebElement cups = cup.size() > 0 ? cup.get(0) : null;
            JavascriptExecutor CupsOrder = (JavascriptExecutor) driver;
            CupsOrder.executeScript("arguments[0].click();", cups);
            test.pass("clicked on Cups");

            Thread.sleep(4000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)");

            Thread.sleep(2000);

            List<WebElement> cupitem = driver.findElements(By.xpath("//*[@id=\"divcustomize\"]/div[8]/div/div/div[3]/a"));
            System.out.println("Found elements: " + cupitem.size());
            WebElement items = cupitem.size() > 0 ? cupitem.get(0) : null;
            JavascriptExecutor cupItems = (JavascriptExecutor) driver;
            cupItems.executeScript("arguments[0].click();", items);
            test.pass("clicked on Triple Value Scoops Cup");

            Thread.sleep(2000);

            List<WebElement> pickUp = driver.findElements(By.xpath("//a[@radio_id=\"Pickup\"]"));
            System.out.println("Found elements: " + pickUp.size());
            WebElement pickUpBtn = pickUp.size() > 0 ? pickUp.get(0) : null;
            JavascriptExecutor pick = (JavascriptExecutor) driver;
            pick.executeScript("arguments[0].click();", pickUpBtn);
            test.pass("clicked on Pickup");

            Thread.sleep(5000);

            List<WebElement> listStores = driver.findElements(By.xpath("//*[@id=\"id_openListStores\"]/a"));
            System.out.println("Found elements: " + listStores.size());
            WebElement listStoress = listStores.size() > 0 ? listStores.get(0) : null;
            JavascriptExecutor list = (JavascriptExecutor) driver;
            list.executeScript("arguments[0].click();", listStoress);

            test.pass("Clicked on Show Stores list");

            Thread.sleep(4000);
            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("window.scrollBy(0,50)");

            Thread.sleep(2000);

            List<WebElement> locationSelect = driver.findElements(By.xpath("//*[@id=\"bindrestaurant\"]/div[1]/div/div/a[2]"));
            System.out.println("Found elements: " + locationSelect.size());
            WebElement locationSelects = locationSelect.size() > 0 ? locationSelect.get(0) : null;
            JavascriptExecutor location = (JavascriptExecutor) driver;
            location.executeScript("arguments[0].click();", locationSelects);
            test.pass("Clicked on location");

            Thread.sleep(20000);

        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }

}
