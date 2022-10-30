package TestFile;

import POM.FindFriendPage;
import POM.UserProfilePage;
import POM.ZaloPage;
import Util.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
        System.setProperty("webdriver.gecko.driver", Constant.FIREFOX_DRIVER_PATH);
        driver = new FirefoxDriver();
    }

    @Test
    public void SearchNumber01() throws Exception {
        driver.get(Constant.BASE_URL);
        Thread.sleep(Constant.WAIT_QR);

        ZaloPage zlPage = new ZaloPage(driver);
        FindFriendPage findFriendPage = zlPage.accessFindFriendFeature();


        try {
            // Read phone numbers from Excel file into a list
            // TO DO
//            File file = new File(Constant.DATA_PATH);
//            //Create an object of FileInputStream class to read excel file
//            FileInputStream fis = new FileInputStream(file);
//
//            //Create object of XSSFWorkbook class
//            XSSFWorkbook wb = new XSSFWorkbook(fis);
//
//            //Read excel sheet by sheet name
//            XSSFSheet sheet = wb.getSheet(Constant.SHEET_NAME);

            // Get number of batch
            int numOfBatch = ExcelUtils.getNumOfBatch(Constant.START_ROW_NUM, Constant.END_ROW_NUM);
            int batchNo = 1;
            int fromRowNum = Constant.START_ROW_NUM;
            int toRowNum = ExcelUtils.determineNextToRowNum(fromRowNum);

            // Get index of header to write result
//            int zlStatusColumnIndex = ExcelUtils.getColumnIndex(sheet, "Zl_Status", 1);
//            int displayNameColumnIndex = ExcelUtils.getColumnIndex(sheet, "Display_Name", 1);
//            int genderColumnIndex = ExcelUtils.getColumnIndex(sheet, "Gender", 1);
//            int dobColumnIndex = ExcelUtils.getColumnIndex(sheet, "DOB", 1);
//            int yobColumnIndex = ExcelUtils.getColumnIndex(sheet, "YOB", 1);
//            int screenshotLinkColumnIndex = ExcelUtils.getColumnIndex(sheet, "Screenshot_Link", 1);

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
                        String displayName = userProfilePage.getDisplayName();
                        String gender = userProfilePage.getGender();
                        String uniformGender = General.getUniformGender(gender);
                        String birthday = userProfilePage.getBirthday();
                        String dob = DateTimeUtils.getDOBYYYYMMDD(birthday);
                        String yob = DateTimeUtils.getYOB(birthday);
                        System.out.println("display name: " + displayName);
                        System.out.println("gender: " + gender);
                        System.out.println("birthday: " + birthday);
                        // Write back to Excel file
                        // Write Zl_Status
//                        sheet.getRow(fromRowNum + i - 1).createCell(Constant.ZL_STATUS_COLUMN_INDEX).setCellValue(Constant.ZL_STATUS_REG);
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.ZL_STATUS_COLUMN_INDEX, Constant.ZL_STATUS_REG);
                        // Write Display_Name
//                        sheet.getRow(fromRowNum + i - 1).createCell(Constant.DISPLAY_NAME_COLUMN_INDEX).setCellValue(displayName);
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.DISPLAY_NAME_COLUMN_INDEX, displayName);
                        // Write Gender
//                        sheet.getRow(fromRowNum + i - 1).createCell(Constant.GENDER_COLUMN_INDEX).setCellValue(gender);
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.GENDER_COLUMN_INDEX, gender);
                        // Write birthday
//                        sheet.getRow(fromRowNum + i - 1).createCell(Constant.DOB_ORIGIN_COLUMN_INDEX).setCellValue(birthday);
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.DOB_ORIGIN_COLUMN_INDEX, birthday);
                        // Write DOB
//                        sheet.getRow(fromRowNum + i - 1).createCell(Constant.DOB_COLUMN_INDEX).setCellValue(dob);
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.DOB_COLUMN_INDEX, dob);
                        // Write YOB
//                        sheet.getRow(fromRowNum + i - 1).createCell(Constant.YOB_COLUMN_INDEX).setCellValue(yob);
                        ExcelUtils.setDataToCell(sheet, rowIndex, Constant.YOB_COLUMN_INDEX, yob);
                        Thread.sleep(1000);
                        String imageFileName = uniformGender + "_" + phoneNumList.get(i);
                        String fileWithPath = FileUtils.getImageFilePath(Constant.SCREENSHOT_FOLDER_PATH, imageFileName, "png");
                        // Take screenshot
                        ImageUtils.takeScreenShot(driver, fileWithPath);
                        // Write screenshot link into Excel file
                        // TO DO
                        userProfilePage.clearPhone();
                        Thread.sleep(1000);
                    } else {
                        // Process when phone number has NOT been registered Zl
                        System.out.println(phoneNumList.get(i) + " not registered Zl");
                        // Write Zl_Status
//                        sheet.getRow(fromRowNum + i - 1).createCell(Constant.ZL_STATUS_COLUMN_INDEX).setCellValue(Constant.ZL_STATUS_NOT);
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
            // Output
//            fis.close();
//            FileOutputStream outputStream = new FileOutputStream(Constant.DATA_PATH);
//            // Write to file
//            wb.write(outputStream);
//            wb.close();
//            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void TearDown() {
//        driver.quit();
    }
}
