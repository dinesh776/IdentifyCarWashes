package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonUtility {

    private static final Logger logger = LogManager.getLogger(JsonUtility.class);
    private static final String filePath = ConfigReader.getJsonFilePath();
    private static final JSONObject data = new JSONObject();
    private static final JSONArray arr = new JSONArray();

    static {
        File f = new File("output");
        if (!f.exists()) {
            boolean created = f.mkdirs();
            if (created) {
                logger.info("Created 'output' directory.");
            } else {
                logger.warn("Failed to create 'output' directory.");
            }
        } else {
            logger.info("'output' directory already exists.");
        }
    }

    @SuppressWarnings("unchecked")
    public static void saveJson() {
        logger.info("Saving JSON data to file: {}", filePath);
        data.put("results", arr);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data.toJSONString());
            writer.flush();
            logger.info("JSON file written successfully.");
        } catch (IOException e) {
            logger.error("Failed to write JSON file: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to write JSON file: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void writeJson(List<Service> list, String serviceName) {
        logger.info("Writing {} services under category '{}'", list.size(), serviceName);
        JSONObject json = new JSONObject();
        List<JSONObject> sample = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Service s = list.get(i);
            JSONObject outerObj = new JSONObject();
            JSONObject innerObj = new JSONObject();
            innerObj.put("name", s.getName());
            innerObj.put("mobileNumber", s.getMobileNumber());
            outerObj.put("Service " + (i + 1), innerObj);
            sample.add(outerObj);
            logger.debug("Added service: {} - {}", s.getName(), s.getMobileNumber());
        }

        json.put(serviceName, sample);
        arr.add(json);
        logger.info("Service data added to JSON array.");
    }
}
