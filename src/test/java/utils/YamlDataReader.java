package utils;

import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class YamlDataReader {

	public static String getValue(String section, String key, String subKey, String fileDotPath) {
		String filePath = fileDotPath.replace(".", "/") + ".yaml";

		try (InputStream inputStream = YamlDataReader.class.getClassLoader().getResourceAsStream(filePath)) {
			if (inputStream == null) {
				throw new RuntimeException("YAML file not found: " + filePath);
			}

			Yaml yaml = new Yaml();
			Map<String, Object> data = yaml.load(inputStream);

			Object value = ((Map<?, ?>) ((Map<?, ?>) data.get(section)).get(key));
			if (value instanceof Map && subKey != null) {
				return ((Map<?, ?>) value).get(subKey).toString();
			} else {
				return value.toString();
			}
		} catch (Exception e) {
			throw new RuntimeException("Error reading YAML: " + e.getMessage(), e);
		}
	}
}
