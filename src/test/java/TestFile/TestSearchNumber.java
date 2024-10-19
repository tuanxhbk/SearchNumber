package TestFile;

import POM.FindFriendPage;
import POM.UserProfilePage;
import POM.ZaloPage;
import Util.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TestSearchNumber {
    WebDriver driver;
    String[] phoneNumberArray = new String[]{"0987654321", "0123456789", "0969691870"};

    @Before
    public void SetUp() {
//        System.setProperty("webdriver.gecko.driver", Constant.FIREFOX_DRIVER_PATH);
//        // Create an object of Firefox Options class
//        FirefoxOptions options = new FirefoxOptions();
//
//        // Set Firefox Headless mode as TRUE
//        options.setHeadless(true);
//
//        // Create an object of WebDriver class and pass the Firefox Options object
//        // as an argument
//        driver = new FirefoxDriver(options);

        WebDriverManager.firefoxdriver().setup();

        // Create an object of Firefox Options class
        FirefoxOptions options = new FirefoxOptions();

        // Set Firefox Headless mode as TRUE
//        options.setHeadless(true);

        // Create an object of Firefox Driver class and pass the Firefox Options object
        // as an argument
        driver = new FirefoxDriver(options);
    }

    @Test
    public void SearchNumber01() throws Exception {
        driver.get(Constant.BASE_URL);
        Thread.sleep(Constant.WAIT_QR);

        ZaloPage zlPage = new ZaloPage(driver);
        FindFriendPage findFriendPage = zlPage.accessFindFriendFeature();


        try {
            // Get number of batch
            int numOfBatch = ExcelUtils.getNumOfBatch(Constant.START_ROW_NUM, Constant.END_ROW_NUM);
            int batchNo = 1;
            int fromRowNum = Constant.START_ROW_NUM;
            int toRowNum = ExcelUtils.determineNextToRowNum(fromRowNum);

            // Loop through all batches
            while (batchNo <= numOfBatch) {
                System.out.println("Batch: " + batchNo);
                File file = new File(Constant.DATA_PATH);
                //Create an object of FileInputStream class to read excel file
                FileInputStream fis = new FileInputStream(file);

                //Create object of XSSFWorkbook class
                XSSFWorkbook wb = new XSSFWorkbook(fis);

                //Read excel sheet by sheet name
                XSSFSheet sheet = wb.getSheet(Constant.SHEET_NAME);
                // Loop through the phone number list to check if phone number has been registered Zl
                List<String> phoneNumList = ExcelUtils.getDataInColumnFromTo(sheet, Constant.PHONE_NO_COLUMN_INDEX, fromRowNum, toRowNum);
                int i = 0;
                while (i < phoneNumList.size()) {
                    findFriendPage.setPhoneNumber(phoneNumList.get(i));
                    UserProfilePage userProfilePage = findFriendPage.findFriend();
                    int rowIndex = fromRowNum + i - 1;
                    if (userProfilePage.isZlRegistered()) {
                        // Process when phone number has already been registered Zl
                        System.out.println(phoneNumList.get(i) + " registered Zl");
                        // Get Zl profile data
                        String displayName = userProfilePage.getDisplayName();
                        String gender = userProfilePage.getGender();
                        String uniformGender = General.getUniformGender(gender);
                        String birthday = userProfilePage.getBirthday();
                        String dob = DateTimeUtils.getDOB(birthday, "YYYYMMDD");
                        String yob = DateTimeUtils.getYOB(birthday);
                        System.out.println("display name: " + displayName);
                        System.out.println("gender: " + gender);
                        System.out.println("birthday: " + birthday);
                        // Write back to Excel file
                        // Write Zl_Status
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.ZL_STATUS_COLUMN_INDEX, Constant.ZL_STATUS_REG);
                        // Write Display_Name
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.DISPLAY_NAME_COLUMN_INDEX, displayName);
                        // Write Gender
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.GENDER_COLUMN_INDEX, gender);
                        // Write birthday
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.DOB_ORIGIN_COLUMN_INDEX, birthday);
                        // Write DOB
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.DOB_COLUMN_INDEX, dob);
                        // Write YOB
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.YOB_COLUMN_INDEX, yob);
                        Thread.sleep(1000);
                        String imageFileName = uniformGender + "_" + phoneNumList.get(i);
                        String fileWithPath = FileUtils.getImageFilePath(Constant.SCREENSHOT_FOLDER_PATH, imageFileName, "png");
                        // Take screenshot
                        ImageUtils.takeScreenShot(driver, fileWithPath);
                        // Write screenshot link into Excel file
                        // TO DO
                        userProfilePage.backToFindFriendPage();
                        Thread.sleep(1000);
                    } else {
                        // Process when phone number has NOT been registered Zl
                        System.out.println(phoneNumList.get(i) + " not registered Zl");
                        // Write Zl_Status
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.ZL_STATUS_COLUMN_INDEX, Constant.ZL_STATUS_NOT);
                    }
                    i++;
                }


                // Determine next batch
                fromRowNum = toRowNum + 1;
                toRowNum = ExcelUtils.determineNextToRowNum(fromRowNum);
                batchNo++;

                // Close FileInputStream first
                fis.close();
                // Create FileOutputStream
                FileOutputStream outputStream = new FileOutputStream(Constant.DATA_PATH);
                // Write to file
                wb.write(outputStream);
                wb.close();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void TearDown() {
        driver.quit();
    }
}
