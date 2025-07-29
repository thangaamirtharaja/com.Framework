package utils;

import java.io.*;
import java.util.Properties;

public class PropertiesWriter {


    public static void writeValue(String section, String key, String subKey, String fileDotPath, String value) {
        // Only `key` and `fileDotPath` are relevant for .properties
        try {
            String filePath = "src/test/resources/" + fileDotPath.replace(".", "/") + ".properties";
            File file = new File(filePath);
            Properties props = new Properties();

            // Load existing properties if file exists
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    props.load(fis);
                }
            }

            // Set or update key
            props.setProperty(key, value);

            // Write back to file
            try (FileOutputStream fos = new FileOutputStream(file)) {
                props.store(fos, "Updated by PropertiesWriter");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to write to properties file: " + e.getMessage(), e);
        }
    }
}
