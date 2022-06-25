package Util;

public class General {
    public static String getUniformGender(String gender) {
        if(gender.equals(Constant.ZL_GENGER_MALE)) {
            return Constant.U_GENGER_MALE;
        } else {
            return Constant.U_GENGER_FEMALE;
        }
    }
}
