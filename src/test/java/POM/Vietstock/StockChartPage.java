package POM.Vietstock;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class StockChartPage {

    WebDriver driver;

    By btnInterval = By.xpath("//div[text()='D'][1]");
    By btnIntervalDaily = By.xpath("//span[text()='1 ngày']");
    By btnIntervalWeekly = By.xpath("//span[text()='1 tuần']");
    By btnIntervalMonthly = By.xpath("//span[text()='1 tháng']");
    By iframe = By.xpath("//iframe[@title='Financial Chart']");

    // Login
    By btnLogin = By.className("btnlogin");
    By inputEmail = By.xpath("//form[@id='form1']//input[@id='txtEmailLogin']");
    By inputPassword = By.xpath("//form[@id='form1']//input[@id='txtPassword']");
    By btnLoginAccount = By.id("btnLoginAccount");

    // Load saved chart
    By btnLoadChart = By.xpath("//div[@data-name='save-load-menu']");
    By lblLoadChart = By.xpath("//span[contains(text(),'Tải bố cục')]");
    By lblChart = By.xpath("//div[contains(text(),'1D Technical Simple')]");
    By btnClose = By.xpath("//button[@data-name='close']");

    // Search symbol
    By divHeaderToolbarSymbolSearch = By.id("header-toolbar-symbol-search");
    By inputSearchSymbol = By.xpath("//input[@data-role='search']");
    By firstItem = By.xpath("//div[@data-role='list-item'][1]/div[1]");

    // Other element
    By btnHuongDan = By.className("btnHuongDan");

    public StockChartPage(WebDriver driver) {
        this.driver = driver;
    }

    //    Set interval
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

        driver.findElement(btnLoadChart).click();
        driver.findElement(lblLoadChart).click();
        driver.findElement(lblChart).click();
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
        Thread.sleep(1000);

        // Switch to default frame
        driver.switchTo().defaultContent();
    }

}
