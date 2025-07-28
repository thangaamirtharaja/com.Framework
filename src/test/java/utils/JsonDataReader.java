package utils;

import java.io.InputStream;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

	public static String getValue(String section, String key, String subKey, String fileDotPath) {
		String filePath = fileDotPath.replace(".", "/") + ".json";

		try (InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(filePath)) {
			if (inputStream == null) {
				throw new RuntimeException("JSON file not found: " + filePath);
			}

			ObjectMapper mapper = new ObjectMapper();
			Map<?, ?> data = mapper.readValue(inputStream, Map.class);

			Object value = ((Map<?, ?>) ((Map<?, ?>) data.get(section)).get(key));
			if (value instanceof Map && subKey != null) {
				return ((Map<?, ?>) value).get(subKey).toString();
			} else {
				return value.toString();
			}
		} catch (Exception e) {
			throw new RuntimeException("Error reading JSON: " + e.getMessage(), e);
		}
	}
}
