package POM.Badoo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EncounterPage {
    WebDriver driver;

    By likeBtn = By.xpath("//div[@class='profile-action profile-action--lg profile-action--color-yes profile-action--filled   js-profile-header-vote-yes js-profile-header-vote']");

    By skipNoti = By.xpath("//div[@class='btn btn--monochrome js-chrome-pushes-deny']");
    public EncounterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLike() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(likeBtn));
        element.click();
    }

    public void clickSkipNoti() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(skipNoti));
            element.click();
//            driver.findElement(skipNoti).click();
        } catch (Exception exception) {

        }
    }
}
