package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage {

    WebDriver driver;

//    @FindBy(xpath = "//div[@id='profile-info']//div[@data-id='btn_UserProfile_AddFrd']")
//    WebElement btnAddFriend;
    By btnAddFriend = By.xpath("//div[@id='profile-info']//div[@data-id='btn_UserProfile_AddFrd']");

//    @FindBy(xpath = "//div[@id='profile-info']//div[@class='user-profile-details']//div[@class='user-profile-details__line'][1]/span[2]")
//    WebElement txtGender;
    By txtGender = By.xpath("//div[@id='profile-info']//div[@class='user-profile-details']//div[@class='user-profile-details__line'][1]/span[2]");

//    @FindBy(xpath = "//div[@id='profile-info']//div[@class='user-profile-details']//div[@class='user-profile-details__line'][2]/span[2]")
//    WebElement txtBirthday;
    By txtBirthday = By.xpath("//div[@id='profile-info']//div[@class='user-profile-details']//div[@class='user-profile-details__line'][2]/span[2]");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public boolean isZlRegistered() {
        boolean zlRegistered = driver.findElements(btnAddFriend).size() != 0;
        return zlRegistered;
    }

    public String getGender() {
        return driver.findElement(txtGender).getText();
    }

    public String getBirthday() {
        return driver.findElement(txtBirthday).getText();
    }
}
