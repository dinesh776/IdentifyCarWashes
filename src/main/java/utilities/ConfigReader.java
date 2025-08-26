package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties = new Properties();
            properties.load(fileInputStream);
            logger.info("Configuration properties loaded successfully from {}", CONFIG_FILE_PATH);
        } catch (IOException e) {
            logger.error("Failed to load configuration properties from {}: {}", CONFIG_FILE_PATH, e.getMessage(), e);
            throw new RuntimeException("Configuration file not found", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found for key: {}", key);
        } else {
            logger.debug("Property retrieved - {}: {}", key, value);
        }
        return value;
    }

    public static String getAppUrl() {
        return getProperty("app.url");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public static String getTestDataFile() {
        return getProperty("test.data.file");
    }

    public static String getNumberOfServices() {
        return getProperty("total.number.of.services");
    }

    public static String getTestDataSheetName() {
        return getProperty("test.data.sheet.name");
    }

    public static String getEnvironment() {
        return getProperty("environment");
    }

    public static String getRemoteUrl() {
        return getProperty("remote.url");
    }

    public static String getExpectedVotes() {
        return getProperty("expected.votes");
    }

    public static String getJsonFilePath() {
        return getProperty("json.file.path");
    }

    public static String getAllureBatPath() {
        return getProperty("allure.bat.path");
    }
}
