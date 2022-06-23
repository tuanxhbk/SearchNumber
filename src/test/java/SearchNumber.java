import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchNumber {
    WebDriver driver;

    @Before
    public void SetUp() {
        System.setProperty("webdriver.gecko.driver","src/test/resource/driver/geckodriver.exe");
        driver = new FirefoxDriver();
    }
    @Test
    public void SearchNumber() {
        driver.get("https://chat.zalo.me/");
    }

    @After
    public void TearDown() {
        driver.quit();
    }
}
