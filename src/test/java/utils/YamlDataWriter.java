package utils;

import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.Map;

public class YamlDataWriter {
    public static void writeValue(String section, String key, String subKey,  String folderDotFile, String value) {
        try {
            String filePath = "src/test/resources/" + folderDotFile.replace(".", "/") + ".yaml";
            Yaml yaml = new Yaml();
            FileInputStream fis = new FileInputStream(filePath);
            Map<String, Object> data = yaml.load(fis);
            fis.close();

            Map<String, Object> sectionMap = (Map<String, Object>) data.get(section);
            if (sectionMap != null) {
                Map<String, Object> innerMap = (Map<String, Object>) sectionMap.get(key);
                if (innerMap != null) {
                    innerMap.put(subKey, value);
                } else {
                    sectionMap.put(key, Map.of(subKey, value));
                }
            }

            FileWriter writer = new FileWriter(filePath);
            yaml.dump(data, writer);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Failed to write YAML: " + e.getMessage(), e);
        }
    }
}
