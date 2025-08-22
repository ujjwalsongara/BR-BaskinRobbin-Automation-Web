package BR.example.page.tripleScoopesCupFlowPage;

import BR.example.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class tripleCupTimeSetUpPage extends BasePage {

    @FindBy(id = "time")
    private WebElement timePicker;

    @FindBy(xpath = "//*[@id=\"SchedulePickup\"]/div/div/div[3]/button")
    private WebElement confirmBtn;

    public tripleCupTimeSetUpPage(WebDriver driver) {
        super(driver);
    }

    public void TimePickerFlow() throws InterruptedException {
        try {
            test = extent.createTest("Time Setup Flow").assignCategory("Regression");
            Thread.sleep(2000);

//            waitWebElement(timePicker, driver);
//            timePicker.click();
//            test.pass("clicked on TimePicker");
//            System.out.println("Clicked on time");
//
//            String currentTime = timePicker.getAttribute("value");
//
//            // Fallback if empty
//            if (currentTime == null || currentTime.isEmpty()) {
//                currentTime = "09:00";
//            }
//
//            // Parse and increment 1 hour
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//            LocalTime time = LocalTime.parse(currentTime, formatter);
//            LocalTime updatedTime = time.plusHours(1);
//
//            String newTime = updatedTime.format(formatter);
//
//            // Set new time via JavaScript
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].value = arguments[1];", timePicker, newTime);
//
//            // Optionally trigger any change event
//            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", timePicker);

            // Locate time picker(s)
            List<WebElement> timePickers = driver.findElements(By.id("timePicker")); // update locator
            System.out.println("Found timePickers: " + timePickers.size());

// Pick first available time picker
            WebElement timePicker = timePickers.size() > 0 ? timePickers.get(0) : null;

            if (timePicker != null) {
                // Wait before interacting
                waitWebElement(timePicker, driver);

                // Click using JS
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", timePicker);
                test.pass("Clicked on TimePicker");
                System.out.println("Clicked on time");

                // Get current value
                String currentTime = timePicker.getAttribute("value");
                if (currentTime == null || currentTime.isEmpty()) {
                    currentTime = "09:00"; // fallback
                }

                // Parse and add +1 hr
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime time = LocalTime.parse(currentTime, formatter);
                LocalTime updatedTime = time.plusHours(1);
                String newTime = updatedTime.format(formatter);

                // Set new time via JS
                js.executeScript("arguments[0].value = arguments[1];", timePicker, newTime);
                js.executeScript("arguments[0].dispatchEvent(new Event('change'));", timePicker);

                test.pass("Updated TimePicker value to: " + newTime);
                System.out.println("Updated TimePicker value to: " + newTime);

            } else {
                test.fail("No TimePicker found!");
                System.out.println("No TimePicker found!");
            }

//            waitWebElement(confirmBtn, driver);
//            confirmBtn.click();

            Thread.sleep(3000);

            List<WebElement> confirm = driver.findElements(By.xpath("//*[@id=\"SchedulePickup\"]/div/div/div[3]/button"));
            System.out.println("Found elements: " + confirm.size());
            WebElement confirmBtn = confirm.size() > 0 ? confirm.get(0) : null;
            JavascriptExecutor confirmTime = (JavascriptExecutor) driver;
            confirmTime.executeScript("arguments[0].click();",  confirmBtn);

            test.pass("Clicked on Confirm");

            Thread.sleep(10000);
        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            throw e;
        }
    }
}

