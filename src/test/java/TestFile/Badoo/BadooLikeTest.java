package TestFile.Badoo;

import POM.Badoo.EncounterPage;
import POM.Badoo.SignInPage;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BadooLikeTest {
    public static WebDriver driver;
    public static String driverPath = "src/test/resource/driver/chromedriver.exe";
    private static final String BADOO_SCREENSHOT_BASE = "src/test/resource/screenshot/badoo/";

    @Test
    public void BadooLikeTest01() throws Exception {
        String signInUrl = "https://badoo.com/en-us/signin/?f=top";
        String encounterUrl = "https://badoo.com/encounters";
        String username = "nguyenmanhtuan_xh@yahoo.com";
        String password = "50503324xh";

        // Setting up Chrome driver path.
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors");
        driver = new ChromeDriver(options);

        // Sign in
        driver.get(signInUrl);
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn(username, password);
        Thread.sleep(5000);

        // Like
//        driver.get(encounterUrl);
        EncounterPage encounterPage = new EncounterPage(driver);

        for(int i = 1; i <= 1000; i++) {
            System.out.println("Loop: " + i);
//            Thread.sleep(5000);
            takeSnapShot(driver, getFilePath(BADOO_SCREENSHOT_BASE, ".png"));
            encounterPage.clickLike();
            encounterPage.clickSkipNoti();
        }

        driver.quit();
    }

    /**
     * This function will take screenshot
     *
     * @param webdriver
     * @param fileWithPath
     * @throws Exception
     */
    public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile = new File(fileWithPath);

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public String getFilePath(String prefix, String suffix) {
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        //get current date time with Date()
        Date date = new Date();

        // Now format the date
        String formatDate= dateFormat.format(date);

        // Build file path
        String filePath = prefix + formatDate + suffix;

        return filePath;
    }

    @Test
    public void testFilePath() {
        String filePath = getFilePath(BADOO_SCREENSHOT_BASE, ".png");
        System.out.println(filePath);
    }
}