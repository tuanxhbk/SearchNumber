package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserProfilePage {

    WebDriver driver;

    By btnAddFriend = By.cssSelector("[data-translate-inner='STR_PROFILE_ADD_FRIEND']");

    By btnSendMsg = By.cssSelector("[data-translate-inner='STR_CHAT']");

    By txtDisplayName = By.cssSelector(".friend-profile__display-name");

    By txtGender = By.xpath("//div[@id='profile-info']//div[@class='user-profile-details']//div[@class='user-profile-details__line'][1]/span[2]");

    By txtBirthday = By.xpath("//div[@id='profile-info']//div[@class='user-profile-details']//div[@class='user-profile-details__line'][2]/span[2]");

    By btnClearPhone = By.cssSelector(".fa-close_24");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isZlRegistered() {
        boolean zlRegistered = driver.findElements(btnSendMsg).size() != 0;
        return zlRegistered;
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

    public FindFriendPage clearPhone() {
        driver.findElement(btnClearPhone).click();
        return new FindFriendPage(driver);
    }
}
