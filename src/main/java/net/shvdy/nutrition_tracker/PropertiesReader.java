package net.shvdy.nutrition_tracker;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class PropertiesReader {

	public enum Props {
		VALIDATION_REGEX("/validation_regex.properties"),
		DAO_SQL_QUERIES("/DAO_SQL_queries.properties");

		private final String path;
		private Properties prop;

		Props(String path) {
			this.path = path;
		}

		public Properties getProp() {
			return prop;
		}

		private void setProp(Properties prop) {
			this.prop = prop;
		}

		public String getPath() {
			return path;
		}
	}

	public static void readProperties(ClassLoader classLoader) {
		Arrays.stream(Props.values()).forEach(property -> {
			Properties loadedProperty = new Properties();
			try {
				loadedProperty.load(Objects.requireNonNull(classLoader.getResourceAsStream(property.getPath())));
			} catch (IOException | NullPointerException e) {
				System.err.println("Could not read properties from file " + property.getPath() + " in classpath. " + e);
			}
			property.setProp(loadedProperty);
		});
	}
}
