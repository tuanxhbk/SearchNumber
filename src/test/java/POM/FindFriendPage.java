package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindFriendPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@id='findFriend']/div[@class='flx flx-center']//input[@title='Vui lòng điền số điện thoại']")
    WebElement txtbxPhoneNumber;

    @FindBy(xpath = "//body//div[@class='truncate' and contains(text(),'Tìm kiếm')]")
    WebElement btnFindFriend;

    public FindFriendPage(WebDriver driver) {
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //    Set phone number in text box
    public void setPhoneNumber(String strPhoneNumber) {
        txtbxPhoneNumber.clear();
        txtbxPhoneNumber.sendKeys(strPhoneNumber);
    }

    //    Set phone number in text box
    public UserProfilePage findFriend() {
        btnFindFriend.click();
        return new UserProfilePage(this.driver);
    }
}
