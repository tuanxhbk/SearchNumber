package POM.Vietstock;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class StockChartPage {

    WebDriver driver;

    By btnInterval = By.xpath("//div[text()='D'][1]");
    By btnIntervalDaily = By.xpath("//span[text()='1 ngày']");
    By btnIntervalWeekly = By.xpath("//span[text()='1 tuần']");
    By btnIntervalMonthly = By.xpath("//span[text()='1 tháng']");
    By iframe = By.xpath("//iframe[@title='Financial Chart']");

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
}
