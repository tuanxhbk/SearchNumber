package TestFile;

import POM.FindFriendPage;
import POM.UserProfilePage;
import POM.ZaloPage;
import Util.FileUtils;
import Util.ImageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSearchNumber {
    WebDriver driver;
    String[] phoneNumberArray = new String[]{"0123456789", "0987654321"};
    String baseUrl = "https://chat.zalo.me/";
    String screenshotFolderPath = "src/test/resource/screenshot";

    @Before
    public void SetUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/resource/driver/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void SearchNumber01() throws Exception {
        driver.get(baseUrl);
        Thread.sleep(10000);

        ZaloPage zlPage = new ZaloPage(driver);
        FindFriendPage findFriendPage = zlPage.accessFindFriendFeature();

        // Find Friend by Phone Number
        for (int i = 0; i < phoneNumberArray.length; i++) {
            findFriendPage.setPhoneNumber(phoneNumberArray[i]);
            UserProfilePage userProfilePage = findFriendPage.findFriend();
            if (userProfilePage.isZlRegistered()) {
                String gender = userProfilePage.getGender();
                String birthday = userProfilePage.getBirthday();
                System.out.println("gender: " + gender);
                System.out.println("birthday: " + birthday);
                String imageFileName = gender + "_" + phoneNumberArray[i];
                String fileWithPath = FileUtils.getFilePath(screenshotFolderPath, imageFileName);
                ImageUtils.takeScreenShot(driver, fileWithPath);
                Thread.sleep(5000);
            }
        }
    }

    @After
    public void TearDown() {
        driver.quit();
    }
}
