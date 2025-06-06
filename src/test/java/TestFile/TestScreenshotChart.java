package TestFile;

import POM.Vietstock.StockChartPage;
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

        // Set Firefox Headless mode as TRUE
        options.setHeadless(true);

        // Create an object of Firefox Driver class and pass the Firefox Options object
        // as an argument
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    /*
     * @Test
     * public void TestScreenshotDailyChartHSX() throws Exception {
     * try {
     * File hsx_stock_list = new File(Constant.HSX_STOCK_LIST);
     * 
     * //Create an object of FileInputStream class to read Excel file
     * FileInputStream fis = new FileInputStream(hsx_stock_list);
     * 
     * //Create object of XSSFWorkbook class
     * XSSFWorkbook wb = new XSSFWorkbook(fis);
     * 
     * //Read Excel sheet by sheet name
     * XSSFSheet sheet = wb.getSheet(Constant.HSX_STOCK_LIST_SHEET_NAME);
     * 
     * // Get list of stock symbols
     * List<String> stockSymbolList = ExcelUtils.getDataInColumnFromTo(sheet,
     * Constant.HSX_STOCK_COLUMN_INDEX,
     * Constant.HSX_STOCK_SYMBOL_START_INDEX, Constant.HSX_STOCK_SYMBOL_END_INDEX);
     * 
     * // Loop through stock symbol list and take chart's screenshot
     * int i = 0;
     * while (i < stockSymbolList.size()) {
     * String dailyChartUrl = Constant.BASE_STOCK_CHART_URL +
     * stockSymbolList.get(i);
     * 
     * driver.get(dailyChartUrl);
     * 
     * Thread.sleep(1000);
     * String imageFileName = stockSymbolList.get(i);
     * // Need to change folder each day
     * String dailyFileWithPath =
     * FileUtils.getImageFilePath(Constant.HSX_CHART_SCREENSHOT_FOLDER,
     * imageFileName, "png");
     * // Take screenshot
     * ImageUtils.takeScreenShot(driver, dailyFileWithPath);
     * Thread.sleep(1000);
     * i++;
     * }
     * 
     * // Close FileInputStream
     * fis.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */

    @Test
    public void TestScreenshotDailyChartHSXWithSavedChart() throws Exception {

        try {
            File hsx_stock_list = new File(Constant.HSX_STOCK_LIST);

            // Create an object of FileInputStream class to read Excel file
            FileInputStream fis = new FileInputStream(hsx_stock_list);

            // Create object of XSSFWorkbook class
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            // Read Excel sheet by sheet name
            XSSFSheet sheet = wb.getSheet(Constant.HSX_STOCK_LIST_SHEET_NAME);

            // Get list of stock symbols
            List<String> stockSymbolList = ExcelUtils.getDataInColumnFromTo(sheet, Constant.HSX_STOCK_COLUMN_INDEX,
                    Constant.HSX_STOCK_SYMBOL_START_INDEX, Constant.HSX_STOCK_SYMBOL_END_INDEX);

            // Login and laod chart
            driver.get(Constant.BASE_STOCK_CHART_URL_2);
            StockChartPage stockChartPage = new StockChartPage(driver);
            stockChartPage.Login(Constant.VIETSTOCK_USERNAME, Constant.VIETSTOCK_PASSWORD);
            stockChartPage.LoadChart();
            // Loop through stock symbol list and take chart's screenshot
            int i = 0;
            while (i < stockSymbolList.size()) {
                String symbol = stockSymbolList.get(i);
                System.out.println("Process symbol: " + symbol);
                stockChartPage.SearchSymbol(symbol);

                // Thread.sleep(1000);
                String imageFileName = symbol;
                // Need to change folder each day
                String dailyFileWithPath = FileUtils.getImageFilePath(Constant.HSX_CHART_SCREENSHOT_FOLDER,
                        imageFileName, "png");
                // Take screenshot
                ImageUtils.takeScreenShot(driver, dailyFileWithPath);
                Thread.sleep(1000);
                i++;
            }

            // Close workbook
            wb.close();

            // Close FileInputStream
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestScreenshotDailyChartHNXWithSavedChart() throws Exception {

        try {
            File hnx_stock_list = new File(Constant.HNX_STOCK_LIST);

            // Create an object of FileInputStream class to read Excel file
            FileInputStream fis = new FileInputStream(hnx_stock_list);

            // Create object of XSSFWorkbook class
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            // Read Excel sheet by sheet name
            XSSFSheet sheet = wb.getSheet(Constant.HNX_STOCK_LIST_SHEET_NAME);

            // Get list of stock symbols
            List<String> stockSymbolList = ExcelUtils.getDataInColumnFromTo(sheet, Constant.HNX_STOCK_COLUMN_INDEX,
                    Constant.HNX_STOCK_SYMBOL_START_INDEX, Constant.HNX_STOCK_SYMBOL_END_INDEX);

            // Login and laod chart
            driver.get(Constant.BASE_STOCK_CHART_URL_2);
            StockChartPage stockChartPage = new StockChartPage(driver);
            stockChartPage.Login(Constant.VIETSTOCK_USERNAME, Constant.VIETSTOCK_PASSWORD);
            stockChartPage.LoadChart();
            // Loop through stock symbol list and take chart's screenshot
            int i = 0;
            while (i < stockSymbolList.size()) {
                String symbol = stockSymbolList.get(i);
                System.out.println("Process symbol: " + symbol);
                stockChartPage.SearchSymbol(symbol);

                // Thread.sleep(1000);
                String imageFileName = symbol;
                // Need to change folder each day
                String dailyFileWithPath = FileUtils.getImageFilePath(Constant.HNX_CHART_SCREENSHOT_FOLDER,
                        imageFileName, "png");
                // Take screenshot
                ImageUtils.takeScreenShot(driver, dailyFileWithPath);
                Thread.sleep(1000);
                i++;
            }

            // Close workbook
            wb.close();

            // Close FileInputStream
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestScreenshotDailyChartUPCOMWithSavedChart() throws Exception {

        try {
            File upcom_stock_list = new File(Constant.UPCOM_STOCK_LIST);

            // Create an object of FileInputStream class to read Excel file
            FileInputStream fis = new FileInputStream(upcom_stock_list);

            // Create object of XSSFWorkbook class
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            // Read Excel sheet by sheet name
            XSSFSheet sheet = wb.getSheet(Constant.UPCOM_STOCK_LIST_SHEET_NAME);

            // Get list of stock symbols
            List<String> stockSymbolList = ExcelUtils.getDataInColumnFromTo(sheet, Constant.UPCOM_STOCK_COLUMN_INDEX,
                    Constant.UPCOM_STOCK_SYMBOL_START_INDEX, Constant.UPCOM_STOCK_SYMBOL_END_INDEX);

            // Login and laod chart
            driver.get(Constant.BASE_STOCK_CHART_URL_2);
            StockChartPage stockChartPage = new StockChartPage(driver);
            stockChartPage.Login(Constant.VIETSTOCK_USERNAME, Constant.VIETSTOCK_PASSWORD);
            stockChartPage.LoadChart();
            // Loop through stock symbol list and take chart's screenshot
            int i = 0;
            while (i < stockSymbolList.size()) {
                String symbol = stockSymbolList.get(i);
                System.out.println("Process symbol: " + symbol);
                stockChartPage.SearchSymbol(symbol);

                // Thread.sleep(1000);
                String imageFileName = symbol;
                // Need to change folder each day
                String dailyFileWithPath = FileUtils.getImageFilePath(Constant.UPCOM_CHART_SCREENSHOT_FOLDER,
                        imageFileName, "png");
                // Take screenshot
                ImageUtils.takeScreenShot(driver, dailyFileWithPath);
                Thread.sleep(1000);
                i++;
            }

            // Close workbook
            wb.close();

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
