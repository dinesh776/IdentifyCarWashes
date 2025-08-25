package utilities;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonUtility {

    private static final String filePath=ConfigReader.getJsonFilePath();
    private static final JSONObject data=new JSONObject();
    private static final JSONArray arr=new JSONArray();

    static {
        File f=new File("output");
        if(!f.exists()){
            f.mkdirs();
        }
    }

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
    @SuppressWarnings("unchecked")
    public static void writeJson(List<Service> list,String serviceName) {
        JSONObject json = new JSONObject();
        List<JSONObject> sample=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Service s=list.get(i);
            JSONObject outerObj=new JSONObject();
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
