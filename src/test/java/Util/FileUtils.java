package Util;

public class FileUtils {

    public static String getFilePath(String folderPath, String fileName) {
        String filePath = folderPath + "/" + fileName + ".png";
        return filePath;
    }
}
