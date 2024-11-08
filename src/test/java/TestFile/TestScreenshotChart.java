package TestFile;

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
import java.io.IOException;
import java.util.List;

public class TestScreenshotChart {
    WebDriver driver;

    @Before
    public void SetUp() {
        WebDriverManager.firefoxdriver().setup();

        // Create an object of Firefox Options class
        FirefoxOptions options = new FirefoxOptions();

        //Set Firefox Headless mode as TRUE
        options.setHeadless(true);

        // Create an object of Firefox Driver class and pass the Firefox Options object
        // as an argument
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void TestScreenshotChart01() throws Exception {
        try {
            File hsx_stock_list = new File(Constant.HSX_STOCK_LIST);

            //Create an object of FileInputStream class to read excel file
            FileInputStream fis = new FileInputStream(hsx_stock_list);

            //Create object of XSSFWorkbook class
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            //Read excel sheet by sheet name
            XSSFSheet sheet = wb.getSheet(Constant.HSX_STOCK_LIST_SHEET_NAME);

            // Get list of stock symbols
            List<String> stockSymbolList = ExcelUtils.getDataInColumnFromTo(sheet, Constant.HSX_STOCK_COLUMN_INDEX, Constant.HSX_STOCK_SYMBOL_START_INDEX,
                    Constant.HSX_STOCK_SYMBOL_END_INDEX);

            // Loop through stock symbol list and take chart's screenshot
            int i = 0;
            while (i < stockSymbolList.size()) {
                String chartUrl = Constant.BASE_STOCK_CHART_URL + stockSymbolList.get(i);

                driver.get(chartUrl);

                Thread.sleep(1000);
                String imageFileName = stockSymbolList.get(i);
                // Need to change folder each day
                String fileWithPath = FileUtils.getImageFilePath(Constant.HSX_CHART_SCREENSHOT_FOLDER, imageFileName, "png");
                // Take screenshot
                ImageUtils.takeScreenShot(driver, fileWithPath);
                Thread.sleep(1000);
                i++;
            }

            // Close FileInputStream
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void TearDown() {
        driver.quit();
    }
}
