package POM.Vietstock;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StockChartPage {

    private WebDriver driver;

    private By btnInterval = By.xpath("//div[text()='D'][1]");
    private By btnIntervalDaily = By.xpath("//span[text()='1 ngày']");
    private By btnIntervalWeekly = By.xpath("//span[text()='1 tuần']");
    private By btnIntervalMonthly = By.xpath("//span[text()='1 tháng']");
    private By iframe = By.xpath("//iframe[@title='Financial Chart']");

    // Login
    private By btnLogin = By.className("btnlogin");
    private By inputEmail = By.xpath("//form[@id='form1']//input[@id='txtEmailLogin']");
    private By inputPassword = By.xpath("//form[@id='form1']//input[@id='txtPassword']");
    private By btnLoginAccount = By.id("btnLoginAccount");

    // Load saved chart
    private By btnLoadChart = By.xpath("//div[@data-name='save-load-menu']");
    private By lblLoadChart = By.xpath("//span[contains(text(),'Tải bố cục')]");
    private By lblChart = By.xpath("//div[contains(text(),'1D Technical Simple')]");
    private By btnClose = By.xpath("//button[@data-name='close']");

    // Search symbol
    private By divHeaderToolbarSymbolSearch = By.id("header-toolbar-symbol-search");
    private By inputSearchSymbol = By.xpath("//input[@data-role='search']");

    public StockChartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Set interval
    public void SetInterval(String interval) {
        try {
            WebElement iframe = driver.findElement(this.iframe);
            driver.switchTo().frame(iframe);
            driver.findElement(btnInterval).click();
            Thread.sleep(1000);

            switch (interval) {
                case "D":
                    driver.findElement(btnIntervalDaily).sendKeys(Keys.DOWN);
                    break;
                case "W":
                    driver.findElement(btnIntervalWeekly).click();
                    break;
                case "M":
                    driver.findElement(btnIntervalMonthly).sendKeys(Keys.DOWN);
                    break;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void Login(String email, String password) {
        try {
            driver.findElement(btnLogin).click();
            driver.findElement(inputEmail).sendKeys(email);
            driver.findElement(inputPassword).sendKeys(password);
            driver.findElement(btnLoginAccount).click();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void LoadChart() {
        // Switch to iframe
        WebElement iframe = driver.findElement(this.iframe);
        driver.switchTo().frame(iframe);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLoadChart));
        driver.findElement(btnLoadChart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblLoadChart));
        driver.findElement(lblLoadChart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblChart));
        driver.findElement(lblChart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnClose));
        driver.findElement(btnClose).click();

        // Switch to default frame
        driver.switchTo().defaultContent();
    }

    public void SearchSymbol(String symbol) throws Exception {
        // Switch to iframe
        WebElement iframe = driver.findElement(this.iframe);
        driver.switchTo().frame(iframe);

        driver.findElement(divHeaderToolbarSymbolSearch).click();
        driver.findElement(inputSearchSymbol).clear();
        driver.findElement(inputSearchSymbol).sendKeys(symbol, Keys.RETURN);
        Thread.sleep(2000);

        // Switch to default frame
        driver.switchTo().defaultContent();
    }

}
