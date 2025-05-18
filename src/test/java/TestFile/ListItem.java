package TestFile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ListItem {
    public static WebDriver driver;
    public static String driverPath = "src/test/resource/driver/chromedriver.exe";
    private static final String COMPANY_CSV_FILE = "src/test/resource/output/list-org.csv";
    private static final String LOG_CSV_FILE = "src/test/resource/output/log.csv";

//    @Test
    public void GetListItem() throws IOException {
        String baseUrlPrefix = "https://www.guidestar.org/nonprofit-directory/human-services/general-human-services/";
        String baseUrlSuffix = ".aspx";
        String liXpathPrefix = "//div[@id='ctl00_phMainBody_divOrgListCol";
        String liXpathSuffix = "']//li";

        // Setting up Chrome driver path.
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors");
        driver = new ChromeDriver(options);

        int allPage = 2; //1314
        int allColumn = 3; //3

        try (
                BufferedWriter companyWriter = Files.newBufferedWriter(Paths.get(COMPANY_CSV_FILE));
                CSVPrinter companyCsvPrinter = new CSVPrinter(companyWriter, CSVFormat.DEFAULT
                        .withHeader("Company"));

                BufferedWriter logWriter = Files.newBufferedWriter(Paths.get(LOG_CSV_FILE));
                CSVPrinter logCsvPrinter = new CSVPrinter(logWriter, CSVFormat.DEFAULT
                        .withHeader("Url", "No of items"));
                ) {
            for(int baseIndex = 1; baseIndex <= allPage; baseIndex++) {
                String pageUrl = baseUrlPrefix + baseIndex + baseUrlSuffix;
                driver.get(pageUrl);
                logCsvPrinter.printRecord(pageUrl, "");

                for(int columnIndex = 1; columnIndex <= allColumn; columnIndex++) {
                    String liXpath = liXpathPrefix + columnIndex + liXpathSuffix;
                    List<WebElement> liList = driver.findElements(By.xpath(liXpath));

                    System.out.println(liList.size());
                    logCsvPrinter.printRecord("",liList.size());

                    for(int i = 0; i < liList.size(); i++) {
                        String elementText = liList.get(i).getText();
                        System.out.println(elementText);
                        companyCsvPrinter.printRecord(elementText);
                    }
                    companyCsvPrinter.flush();
                }
                logCsvPrinter.flush();
            }
        }
        driver.close();

    }
}
