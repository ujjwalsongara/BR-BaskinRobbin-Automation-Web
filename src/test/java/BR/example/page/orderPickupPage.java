package BR.example.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class orderPickupPage extends BasePage {

//    @FindBy(id="Pickup")
//    private WebElement pickupBtn;

    @FindBy(xpath = "//*[@id=\"divcustomize\"]/div[2]/div/img")
    private WebElement CupsOrder;

//    @FindBy(xpath = "//*[@id=\"divcustomize\"]/div[8]/div/div/div[3]/a")
    @FindBy(xpath = "//*[@id=\"divcustomize\"]/div[1]/div/div/div[3]/a")
    private WebElement item;

    @FindBy(xpath = "//a[@radio_id=\"Pickup\"]")
    private WebElement pickUpBtnn;

//    @FindBy(id = "ContentPlaceHolder1_openmysavedrestaurant")
//    private WebElement locationFav;

//    @FindBy(xpath = "//*[@id=\"id_openListStores\"]/a")

    @FindBy(id = "id_openListStores")
    private WebElement listStores;

    @FindBy(xpath = "//*[@id=\"bindrestaurant\"]/div[1]/div/div/a[2]")
    //   @FindBy(xpath= "//*[@id=\"mysavedrestrns\"]/div[2]/div/div/a[2]")
//    @FindBy(xpath = "//*[@id=\"mysavedrestrns\"]/div/div/div/a[2]")
    private WebElement locationSelect;

    public orderPickupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(), this);
        getWebDriver().manage().window().maximize();
    }

    public void pickupFlow() throws InterruptedException {
        try {
            test = extent.createTest("PickUp Flow").assignCategory("Regression");
            Thread.sleep(2000);

//            waitWebElement(pickupBtn, driver);
//            pickupBtn.click();
//            test.pass("clicked on pickUp");

            waitWebElement(CupsOrder, driver);
            CupsOrder.click();
            test.pass("clicked on Cups");

//            Thread.sleep(4000);
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("window.scrollBy(0,1000)");
//
//            Thread.sleep(2000);

            waitWebElement(item, driver);
            item.click();
            test.pass("clicked on One Scoops Cup");
//            test.pass("clicked on Triple Value Scoops Cup");

            waitWebElement(pickUpBtnn, driver);
            pickUpBtnn.click();
            test.pass("clicked on Pickup");

            Thread.sleep(5000);
            waitWebElement(listStores, driver);
            listStores.click();
            test.pass("Clicked on Show Stores list");

            waitWebElement(locationSelect, driver);
            locationSelect.click();
            System.out.println("clicked");
            test.pass("Clicked on location");

            Thread.sleep(20000);

        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}
