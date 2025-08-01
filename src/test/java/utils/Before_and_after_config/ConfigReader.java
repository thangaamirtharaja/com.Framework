package utils.Before_and_after_config;

import utils.Interface.GetValue;
import utils.Interface.WriteValue;

public abstract class ConfigReader implements GetValue, WriteValue {
    private static String path = "properties.config";

    public static String get(String key) {
        return propertiesreader.getValue(null, key, null, path);
    }

    public static void write(String key, String value) {
        propertieswriter.writeValue(null, key, null, path, value);
    }

}
