package net.shvdy.nutrition_tracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class PropertiesContainer {

	public enum Props {
		VALIDATION_REGEX("/validation_regex.properties"),
		DAO_SQL_QUERIES("/DAO_SQL_queries.properties");

		private final String filePath;
		private Properties prop;

		Props(String filePath) {
			this.filePath = filePath;
		}

		public Properties getProp() {
			return prop;
		}

		private void setProp(Properties prop) {
			this.prop = prop;
		}

		public String getFilePath() {
			return filePath;
		}
	}

	public enum JSONProperties {
		FOOD_FORM_VALIDATION_DATA("food-form-validation-data.json");

		final String FILE_PATH;
		private Map<String, Map<String, String>> formFieldsValidationData;

		JSONProperties(String filePath) {
			this.FILE_PATH = filePath;
		}

		public Map<String, Map<String, String>> getFormFieldsValidationData() {
			return formFieldsValidationData;
		}

		public void setFormFieldsValidationData(Map<String, Map<String, String>> formFields) {
			this.formFieldsValidationData = formFields;
		}
	}


	public static void readProperties(ClassLoader classLoader) {
		Arrays.stream(Props.values()).forEach(property -> {
			Properties loadedProperty = new Properties();
			try {
				loadedProperty.load(Objects.requireNonNull(classLoader.getResourceAsStream(property.getFilePath())));
			} catch (IOException | NullPointerException e) {
				System.err.println("Could not read properties from file " + property.getFilePath() + " in classpath. " + e);
			}
			property.setProp(loadedProperty);
		});

		Arrays.stream(JSONProperties.values()).forEach(jsonProperty -> {
			ObjectMapper jsonMapper = new ObjectMapper();
			try {
				jsonProperty.setFormFieldsValidationData(jsonMapper
						.readValue(IOUtils.toString(Objects.requireNonNull(classLoader
										.getResourceAsStream(jsonProperty.FILE_PATH)), StandardCharsets.UTF_8),
								new TypeReference<Map<String, Map<String, String>>>() {
								}));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
