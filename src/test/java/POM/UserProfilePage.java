package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserProfilePage {

    WebDriver driver;

    By btnAddFriend = By.cssSelector("[data-translate-inner='STR_PROFILE_ADD_FRIEND']");

    By btnSendMsg = By.cssSelector("[data-translate-inner='STR_CHAT']");

    By txtDisplayName = By.cssSelector(".pi-mini-info-section__name");

    By txtGender = By.cssSelector(".pi-info-section__info-list .pi-info-item_horizontal:nth-of-type(1) .pi-info-item__desc");

    By txtBirthday = By.cssSelector(".pi-info-section__info-list .pi-info-item_horizontal:nth-of-type(2) .pi-info-item__desc");

    By btnBackToFindFriendPage = By.cssSelector(".fa-icon-solid-left");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isZlRegistered() {
        return !driver.findElements(btnSendMsg).isEmpty();
    }

    public String getDisplayName() {
        return driver.findElement(txtDisplayName).getText();
    }

    public String getGender() {
        return driver.findElement(txtGender).getText();
    }

    public String getBirthday() {
        return driver.findElement(txtBirthday).getText();
    }

    public void backToFindFriendPage() {
        driver.findElement(btnBackToFindFriendPage).click();
    }
}
