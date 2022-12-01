package TestFile.Badoo;

import POM.Badoo.EncounterPage;
import POM.Badoo.SignInPage;
import Util.ConfigLoader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BadooLikeTest {
    public static WebDriver driver;
    public static ConfigLoader configLoader = ConfigLoader.getInstance();
    public static String driverPath = configLoader.getChromeDriverPath();
    private static String badooScreenshotBase = configLoader.getBadooScreenshotBase();
    private static String badooListCsvPath = configLoader.getBadooListCsvPath();

    @Test
    public void BadooLikeTest01() throws Exception {
        String signInUrl = configLoader.getBadooSignInUrl();
//        String encounterUrl = "https://badoo.com/encounters";
        String username = configLoader.getBadooUsername();
        String password = configLoader.getBadooPassword();

        // Setting up Chrome driver path.
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);

        // Setup csv writer
        CSVPrinter badooListCsvPrinter = this.getBadooListCsvPrinter(getFilePath(badooListCsvPath, ".csv", "yyyyMMdd"));

        // Sign in
        driver.get(signInUrl);
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn(username, password);
        Thread.sleep(5000);

        // Like
//        driver.get(encounterUrl);
        EncounterPage encounterPage = new EncounterPage(driver);
        // Outer loop
        int outerLoopMax = 100;
        int innerLoopMax = 10;
        for (int i = 0; i < outerLoopMax; i++) {
            for (int j = 0; j < innerLoopMax; j++) {
                int loopTime = i * innerLoopMax + j + 1;
                System.out.println("Loop: " + loopTime);
//                Thread.sleep(5000);
//                takeSnapShot(driver, getFilePath(badooScreenshotBase, ".png", "yyyyMMdd_HHmmss"));

                String profileName = encounterPage.getProfileName();
                String profileAge = encounterPage.getProfileAge();
                String aboutMe = encounterPage.getAboutMe();
                String profileImageSrc = encounterPage.getProfileImageSrc();
                badooListCsvPrinter.printRecord(profileName, profileAge, aboutMe, profileImageSrc);
                encounterPage.clickLike();
                encounterPage.clickSkipNoti();
            }
            badooListCsvPrinter.flush();
        }
        badooListCsvPrinter.close(true);

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

    public String getFilePath(String prefix, String suffix, String dateTimeFormat) {
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);

        //get current date time with Date()
        Date date = new Date();

        // Now format the date
        String formatDate = dateFormat.format(date);

        // Build file path
        String filePath = prefix + formatDate + suffix;

        return filePath;
    }

    public CSVPrinter getBadooListCsvPrinter(String filePath) throws Exception {
        File file = new File(filePath);
        BufferedWriter badooListWriter;
        CSVPrinter badooListCsvPrinter;
        Path path = Paths.get(filePath);

        if (!file.exists()) {
            badooListWriter = Files.newBufferedWriter(path);
            badooListCsvPrinter = new CSVPrinter(badooListWriter, CSVFormat.DEFAULT);
            badooListCsvPrinter.printRecord("Profile Name", "Profile Age", "About Me", "Profile Image Src");
        } else {
            badooListWriter = Files.newBufferedWriter(path,
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            badooListCsvPrinter = new CSVPrinter(badooListWriter, CSVFormat.DEFAULT);
        }
        return badooListCsvPrinter;
    }

    @Test
    public void testFilePath() {
        String filePath = getFilePath(badooScreenshotBase, ".png", "yyyyMMdd_HHmmss");
        System.out.println(filePath);
    }
}
