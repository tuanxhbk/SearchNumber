package Util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {


    @Test
    public void testExcelRead() {
        String path = "src/test/resource/data/viettel/sample_96.xlsx";

        try {

            //Create an object of FileInputStream class to read excel file
            FileInputStream fis = new FileInputStream(Constant.DATA_PATH);

            //Create object of XSSFWorkbook class
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            //Read excel sheet by sheet name
            XSSFSheet sheet1 = wb.getSheet("960");

            getColumnIndex(sheet1, "Screenshot_Link", 1);

            //Get data from specified cell
            DataFormatter dataFormatter = new DataFormatter();
            System.out.println(dataFormatter.formatCellValue(sheet1.getRow(1).getCell(0)));
            System.out.println(dataFormatter.formatCellValue(sheet1.getRow(1).getCell(6)));


            System.out.println(getDataInColumnByBatch(sheet1, "Phone_No", 1, 1).toString());



            wb.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBatch() {
        System.out.println(getNumOfBatch(Constant.START_ROW_NUM, Constant.END_ROW_NUM));
    }

    public static int getColumnIndex(Sheet sheet, String headerName, int headerRowNum) {
        int columnIndex = -1;
        Row header = sheet.getRow(headerRowNum - 1);
        int firstCellNum = header.getFirstCellNum();
        int lastCellNum = header.getLastCellNum();
        DataFormatter dataFormatter = new DataFormatter();

        for (int i = firstCellNum; i <= lastCellNum; i++) {
            String data = dataFormatter.formatCellValue(header.getCell(i));
            if (data.equals(headerName)) {
                columnIndex = i;
                break;
            }
        }
        System.out.println("Index of column \"" + headerName + "\": " + columnIndex);
        return columnIndex;
    }

    public static List<String> getDataInColumnByBatch (Sheet sheet, String headerName, int headerRowNum, int fromRowNum) {
        int colunmIndex = getColumnIndex(sheet, headerName, headerRowNum);
        List<String> dataList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();

        for (int i = fromRowNum; i < (fromRowNum + Constant.BATCH_SIZE); i++) {
            try {
                String data = dataFormatter.formatCellValue(sheet.getRow(i).getCell(colunmIndex));
                dataList.add(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dataList;
    }

    public static List<String> getDataInColumnFromTo (Sheet sheet, String headerName, int headerRowNum, int fromRowNum, int toRowNum) {
        int colunmIndex = getColumnIndex(sheet, headerName, headerRowNum);
        List<String> dataList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();

        for (int i = fromRowNum - 1; i < toRowNum; i++) {
            try {
                String data = dataFormatter.formatCellValue(sheet.getRow(i).getCell(colunmIndex));
                dataList.add(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dataList;
    }

    public static int getNumOfBatch(int fromNum, int toNum) {
        int numOfBatch = ((toNum - fromNum + 1) / Constant.BATCH_SIZE) + (((toNum - fromNum + 1) % Constant.BATCH_SIZE == 0) ? 0 : 1);
        System.out.println("numOfBatch: " + numOfBatch);
        return numOfBatch;
    }

    public static int determineNextToRowNum(int fromRowNum) {
        if ((Constant.END_ROW_NUM - fromRowNum + 1) <= Constant.BATCH_SIZE) {
            return Constant.END_ROW_NUM;
        } else {
            return fromRowNum + Constant.BATCH_SIZE - 1;
        }
    }
}
