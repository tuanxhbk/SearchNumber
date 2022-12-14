package TestFile.Badoo;

import POM.Badoo.EncounterPage;
import POM.Badoo.SignInPage;
import Util.ConfigLoader;
import Util.ImageUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BadooLikeTest {
    public static ChromeDriver driver;
    public static ConfigLoader configLoader = ConfigLoader.getInstance();

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    @Test
    public void BadooLikeTest01() throws Exception {
        String signInUrl = configLoader.getBadooSignInUrl();
//        String encounterUrl = "https://badoo.com/encounters";
        String username = configLoader.getBadooUsername();
        String password = configLoader.getBadooPassword();
        String badooScreenshotBase = configLoader.getBadooScreenshotBase();
        String badooListCsvPath = configLoader.getBadooListCsvPath();

        // Setup csv writer
        CSVPrinter badooListCsvPrinter = this.getBadooListCsvPrinter(getFilePath(badooListCsvPath, ".csv", "yyyyMMdd"));

        // Geolocation
        float geoLat = configLoader.getGeoLat();
        float geoLong = configLoader.getGeoLong();
        Map coordinates = new HashMap() {
            {
                put("latitude", geoLat);
                put("longitude", geoLong);
                put("accuracy", 1);
            }
        };

        // Set browser geolocation
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        // Sign in
        driver.get(signInUrl);
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn(username, password);
        Thread.sleep(10000);

        // Like
//        driver.navigate(encounterUrl);
        EncounterPage encounterPage = new EncounterPage(driver);
        // Loop Like
        int outerLoopMax = 100;
        int innerLoopMax = 10;
        boolean hasNext = true;
        for (int i = 0; (i < outerLoopMax) && (hasNext); i++) {
            for (int j = 0; j < innerLoopMax; j++) {
                int loopCount = i * innerLoopMax + j + 1;
                System.out.println("Loop: " + loopCount);
//                Thread.sleep(5000);
//                ImageUtils.takeScreenShot(driver, getFilePath(badooScreenshotBase, ".png", "yyyyMMdd_HHmmss"));

                String profileName = encounterPage.getProfileName();
                String profileAge = encounterPage.getProfileAge();
                String aboutMe = encounterPage.getAboutMe();
                String profileImageSrc = encounterPage.getProfileImageSrc();
                if (profileName != "") {
                    badooListCsvPrinter.printRecord(profileName, profileAge, aboutMe, profileImageSrc);
                    encounterPage.clickLike();
                    encounterPage.clickSkipNoti();
                } else {
                    hasNext = false;
                    System.out.println("Done");
                    break;
                }
            }
            badooListCsvPrinter.flush();
        }
        badooListCsvPrinter.close(true);
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
        String badooScreenshotBase = configLoader.getBadooScreenshotBase();
        String filePath = getFilePath(badooScreenshotBase, ".png", "yyyyMMdd_HHmmss");
        System.out.println(filePath);
    }

    @Test
    public void testGeolocation() throws Exception {
        // Geolocation
        float geoLat = configLoader.getGeoLat();
        float geoLong = configLoader.getGeoLong();
        Map coordinates = new HashMap() {
            {
                put("latitude", geoLat);
                put("longitude", geoLong);
                put("accuracy", 1);
            }
        };

        // Set browser geolocation
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        // Test location
        driver.get("https://where-am-i.org/");
        Thread.sleep(5000);
    }
}
