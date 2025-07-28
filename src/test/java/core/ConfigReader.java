package core;

import utils.PropertiesReader;

public class ConfigReader {
	private static String path = "properties.config";

	public static String get(String key) {
		return PropertiesReader.getValue(key, path);
	}
}
