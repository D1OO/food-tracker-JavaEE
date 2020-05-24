package net.shvdy.nutrition_tracker;

import java.io.IOException;
import java.io.InputStream;
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
		for (Props propsValue : Props.values()) {
			Properties loadedProperty = new Properties();
			try {
				loadedProperty.load(classLoader.getResourceAsStream(propsValue.getPath()));
			} catch (IOException | NullPointerException e) {
				System.err.println("Could not read properties from file " + propsValue.getPath() + " in classpath. " + e);
			}
			propsValue.setProp(loadedProperty);
		}
	}
}
