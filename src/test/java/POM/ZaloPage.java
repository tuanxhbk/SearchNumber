package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZaloPage {

    WebDriver driver;

    By iconFindFriend = By.cssSelector("[class='fa fa-outline-add-new-contact-2 pre']");

    public ZaloPage(WebDriver driver) {
        this.driver = driver;
    }

    //    Access Find Friend feature
    public FindFriendPage accessFindFriendFeature() {
        driver.findElement(iconFindFriend).click();
        return new FindFriendPage(this.driver);
    }
}
