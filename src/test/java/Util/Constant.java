package Util;

public class Constant {

    public static final int BATCH_SIZE = 1000;
    public static final String DATA_PATH = "src/test/resource/data/viettel/viettel_96/viettel_969.xlsx";
    public static final String SHEET_NAME = "960";
    public static final String BASE_URL = "https://chat.zalo.me/";
    public static final String SCREENSHOT_FOLDER_PATH = "src/test/resource/screenshot/viettel/969";
    public static final String FIREFOX_DRIVER_PATH = "src/test/resource/driver/geckodriver.exe";
    public static final int START_ROW_NUM = 610887;
    public static final int END_ROW_NUM = 700001;
    public static final String ZL_STATUS_NOT = "NOT";
    public static final String ZL_STATUS_REG = "REG";

    // Excel file column index
    public static final int PHONE_NO_COLUMN_INDEX = 0;
    public static final int ZL_STATUS_COLUMN_INDEX = 1;
    public static final int DISPLAY_NAME_COLUMN_INDEX = 2;
    public static final int GENDER_COLUMN_INDEX = 3;
    public static final int DOB_ORIGIN_COLUMN_INDEX = 4;
    public static final int DOB_COLUMN_INDEX = 5;
    public static final int YOB_COLUMN_INDEX = 6;
    public static final int SCREENSHOT_LINK_COLUMN_INDEX = 7;

    // Zl Gender
    public static final String ZL_GENGER_MALE = "Nam";
    public static final String Zl_GENGER_FEMALE = "Nữ";

    // Uniform Gender
    public static final String U_GENGER_MALE = "M";
    public static final String U_GENGER_FEMALE = "F";

    // Wait to scan QR code (ms)
    public static final int WAIT_QR = 20000;
}