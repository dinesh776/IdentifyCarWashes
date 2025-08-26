package utilities;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Utility class for writing service data to a JSON file.
 * Uses org.json.simple to construct and save JSON objects.
 */
public class JsonUtility {


    // Path to the JSON file, read from configuration
    private static final String filePath=ConfigReader.getJsonFilePath();

    // Root JSON object and array to hold all service data
    private static final JSONObject data=new JSONObject();
    private static final JSONArray arr=new JSONArray();


    // Static block to ensure output directory exists
    static {
        File f=new File("output");
        if(!f.exists()){
            f.mkdirs();      // Create directory if it doesn't exist
        }
    }


    /**
     * Saves the accumulated JSON data to the file specified in config.
     * Wraps the array of service entries under the "results" key.
     */

    @SuppressWarnings("unchecked")
	public static void saveJson() {
        data.put("results",arr);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON file: " + e.getMessage(), e);
        }
    }

    // Overloaded method to write a list of strings as a JSON array

    /**
     * Adds a list of services to the JSON array under a named section.
     * Each service is stored as a nested JSON object with name and mobile number.
     *
     * @param list List of Service objects to write
     * @param serviceName Name of the service category or section
     */

    @SuppressWarnings("unchecked")
    public static void writeJson(List<Service> list,String serviceName) {
        JSONObject json = new JSONObject();
        List<JSONObject> sample=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Service s=list.get(i);
            // Outer object with service label
            JSONObject outerObj=new JSONObject();
            // Inner object with service details
            JSONObject innerObj=new JSONObject();
            innerObj.put("name",s.getName());
            innerObj.put("mobileNumber",s.getMobileNumber());
            outerObj.put("Service "+(i+1),innerObj);
            sample.add(outerObj);
        }
        json.put(serviceName, sample);
        arr.add(json);
    }
}
