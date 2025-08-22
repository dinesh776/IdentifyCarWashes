package utilities;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class jsonutility {
	public static void writeJsonToFile(String filePath, JSONObject data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON file: " + e.getMessage(), e);
        }
    }

    // Overloaded method to write a list of strings as a JSON array
    @SuppressWarnings("unchecked")
    public static void writeJsonToFile(String filePath, List<String> list) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        array.addAll(list);
        json.put("results", array);
        writeJsonToFile(filePath, json);
    }
}
