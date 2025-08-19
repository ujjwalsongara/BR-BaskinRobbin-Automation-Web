package BR.example.page;

import BR.example.BaseTest;
import org.openqa.selenium.WebDriver;

public class BasePage extends BaseTest {

    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }

}
