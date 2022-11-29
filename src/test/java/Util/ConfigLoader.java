package Util;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resource/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getChromeDriverPath() {
        String prop = properties.getProperty("chrome_driver_path");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getBadooSignInUrl() {
        String prop = properties.getProperty("badoo_signin_url");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getBadooUsername() {
        String prop = properties.getProperty("badoo_username");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getBadooPassword() {
        String prop = properties.getProperty("badoo_password");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getBadooScreenshotBase() {
        String prop = properties.getProperty("badoo_screenshot_base");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getBadooListCsvPath() {
        String prop = properties.getProperty("badoo_list_csv_path");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }
}
