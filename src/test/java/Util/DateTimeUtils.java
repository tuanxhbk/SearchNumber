package Util;

import org.junit.Test;

public class DateTimeUtils {
    public static String getYOB(String dob) {
        String yob = "";
        if (!dob.equals("")) {
            String numberOnly = dob.replaceAll("[^0-9]", "");
            if (numberOnly.length() == 8){
                yob = numberOnly.substring(numberOnly.length() - 4);
            }
        }
        return yob;
    }

    public static String getDOBYYYYMMDD(String dobOrigin) {
        String dobResult = "";
        if (!dobOrigin.equals("")) {
            String numberOnly = dobOrigin.replaceAll("[^0-9]", "");
            if (numberOnly.length() == 8){
                String dd = numberOnly.substring(numberOnly.length() - 8, numberOnly.length() - 6);
                String mm = numberOnly.substring(numberOnly.length() - 6, numberOnly.length() - 4);
                String yyyy = numberOnly.substring(numberOnly.length() - 4);

                dobResult = yyyy + mm + dd;
            }
        }
        return dobResult;
    }

    @Test
    public void testGetYOB() {
        System.out.println(getYOB("05 tháng 08, 1988"));
    }

    @Test
    public void testGetDOBYYYYMMDD() {
        System.out.println(getDOBYYYYMMDD("05 tháng 08, 1988"));
    }
}
