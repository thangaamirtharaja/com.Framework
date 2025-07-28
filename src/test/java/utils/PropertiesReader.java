package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static String getValue(String key, String fileDotPath) {
        String filePath = fileDotPath.replace(".", "/") + ".properties";

        try (InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
				throw new RuntimeException("Properties file not found: " + filePath);
            }

            Properties props = new Properties();
            props.load(inputStream);
            return props.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException("Error reading properties file: " + e.getMessage(), e);
        }
    }
}
