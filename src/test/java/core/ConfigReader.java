package core;

import utils.Interface.GetValue;

public abstract class ConfigReader implements GetValue {
	private static String path = "properties.config";

	public static String get(String key) {
		return propertiesreader.getValue(null,key,null, path);
	}
}
