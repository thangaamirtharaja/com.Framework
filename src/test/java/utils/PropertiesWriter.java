package utils;

import java.io.*;
import java.util.Properties;

public class PropertiesWriter {
    public static void writeValue(String key, String value, String folderDotFile) {
        try {
            String filePath = "src/test/resources/" + folderDotFile.replace(".", "/") + ".properties";
            File file = new File(filePath);
            Properties props = new Properties();

            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    props.load(fis);
                }
            }

            props.setProperty(key, value);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                props.store(fos, "Updated by PropertiesWriter");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to write to properties file: " + e.getMessage(), e);
        }
    }
}
