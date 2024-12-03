package POM.Zalo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfilePage {

    WebDriver driver;

    By btnAddFriend = By.cssSelector("[data-translate-inner='STR_PROFILE_ADD_FRIEND']");

    By btnSendMsg = By.cssSelector("[data-translate-inner='STR_CHAT']");

    By txtDisplayName = By.cssSelector(".pi-mini-info-section__name");

    By txtGender = By.xpath("//span[@data-translate-inner='STR_PROFILE_LABEL_GENDER']/following-sibling::span/p");

    By txtBirthday = By.xpath("//span[@data-translate-inner='STR_PROFILE_LABEL_BIRTHDAY']/following-sibling::span/p");

    By btnBackToFindFriendPage = By.cssSelector(".fa-icon-solid-left");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isZlRegistered() {
        return !driver.findElements(btnSendMsg).isEmpty();
    }

    public String getDisplayName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtDisplayName));
        return driver.findElement(txtDisplayName).getText();
    }

    public String getGender() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtGender));
        return driver.findElement(txtGender).getText();
    }

    public String getBirthday() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtBirthday));
        return driver.findElement(txtBirthday).getText();
    }

    public void backToFindFriendPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnBackToFindFriendPage));
        driver.findElement(btnBackToFindFriendPage).click();
    }
}
