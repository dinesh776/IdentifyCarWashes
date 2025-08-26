package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Utility class to read configuration properties from a file.
 * Provides static methods to access various configuration values.
 */
public class ConfigReader {


    // Logger for logging messages
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    // Properties object to hold key-value pairs from the config file
    private static Properties properties;

    // Path to the configuration file
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";


    // Static block to load properties when the class is first loaded
    static {
        loadProperties();
    }


    /**
     * Loads properties from the configuration file.
     * Logs success or failure messages.
     */
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


    /**
     * Retrieves the value of a property by key.
     * Logs a warning if the key is not found.
     *
     * @param key The property key
     * @return The property value or null if not found
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found for key: {}", key);
        } else {
            logger.debug("Property retrieved - {}: {}", key, value);
        }
        return value;
    }



// Specific getters for commonly used configuration properties

    /**
     * @return Application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url");
    }

    //@return Implicit wait time in seconds
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    //@return Explicit wait time in seconds
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    //@return Path to the test data file
    public static String getTestDataFile() {
        return getProperty("test.data.file");
    }

    //return Total number of services to be tested
    public static String getNumberOfServices(){
        return getProperty("total.number.of.services");
    }

    //return Sheet name in the test data file
    public static String getTestDataSheetName(){
        return getProperty("test.data.sheet.name");
    }


    //return Current environment
    public static String getEnvironment(){
        return getProperty("environment");
    }

    //@return Remote WebDriver URL for distributed testing
    public static String getRemoteUrl(){
        return getProperty("remote.url");
    }

    //return Expected number of votes for filtering services
    public static String getExpectedVotes(){return getProperty("expected.votes");}

    //@return Path to the JSON file used in tests
    public static String getJsonFilePath(){
        return getProperty("json.file.path");
    }

    //@return Path to the Allure report batch file
    public static String getAllureBatPath(){return getProperty("allure.bat.path");}

}
