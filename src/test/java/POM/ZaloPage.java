package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZaloPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@id='contact-search']//i[@class='fa fa-outline-add-new-contact-2 pre']")
    WebElement iconFindFriend;

    public ZaloPage(WebDriver driver) {
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //    Access Find Friend feature
    public FindFriendPage accessFindFriendFeature() {
        iconFindFriend.click();
        return new FindFriendPage(this.driver);
    }
}
