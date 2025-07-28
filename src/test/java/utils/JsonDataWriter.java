package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonDataWriter {
    public static void writeValue(String section, String key, String subKey, String value, String folderDotFile) {
        try {
            String filePath = "src/test/resources/" + folderDotFile.replace(".", "/") + ".json";
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
            Map<String, Object> data = mapper.readValue(file, Map.class);

            Map<String, Object> sectionMap = (Map<String, Object>) data.get(section);
            if (sectionMap != null) {
                Map<String, Object> innerMap = (Map<String, Object>) sectionMap.get(key);
                if (innerMap != null) {
                    innerMap.put(subKey, value);
                } else {
                    sectionMap.put(key, Map.of(subKey, value));
                }
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON: " + e.getMessage(), e);
        }
    }
}
