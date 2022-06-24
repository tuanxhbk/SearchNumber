package Util;

public class FileUtils {

    public static String getImageFilePath(String folderPath, String fileName, String imageExtension) {
        String filePath = folderPath + "/" + fileName + "." + imageExtension;
        return filePath;
    }
}
