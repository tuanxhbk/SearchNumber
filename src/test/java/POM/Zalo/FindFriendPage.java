package POM.Zalo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindFriendPage {

    WebDriver driver;

    By txtbxPhoneNumber = By.cssSelector("[class='phone-i-input flx-1']");

    By btnFindFriend = By.cssSelector(".btn-primary [data-translate-inner]");

    public FindFriendPage(WebDriver driver) {
        this.driver = driver;
    }

    //    Set phone number in text box
    public void setPhoneNumber(String strPhoneNumber) {
        driver.findElement(txtbxPhoneNumber).clear();
        driver.findElement(txtbxPhoneNumber).sendKeys(strPhoneNumber);
    }

    //    Click find friend
    public UserProfilePage findFriend() {
        driver.findElement(btnFindFriend).click();
        return new UserProfilePage(this.driver);
    }
}
