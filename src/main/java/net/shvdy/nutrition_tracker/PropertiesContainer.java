package net.shvdy.nutrition_tracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger log = LogManager.getLogger(PropertiesContainer.class);

    public enum DotProperties {
        DAO_SQL_QUERIES("DAO_SQL_queries.properties"),
        APP_PROPERTIES("application.properties");

        final String FILE_PATH;
        private Properties prop;

        DotProperties(String filePath) {
            this.FILE_PATH = filePath;
        }

        public Properties getProp() {
            return prop;
        }

        private void setProp(Properties prop) {
            this.prop = prop;
        }

    }

    public enum FormValidationConfig {
        USER_SIGN_UP("form-validation-data/user-sign-up.json"),
        USER_PROFILE("form-validation-data/user-profile.json"),
        FOOD("form-validation-data/food.json"),
        ARTICLE("form-validation-data/article.json"),
        GROUP_INVITE("form-validation-data/group-invite.json");

        final String FILE_PATH;
        private Map<String, Properties> formValidationConfig;

        FormValidationConfig(String filePath) {
            this.FILE_PATH = filePath;
        }

        public Map<String, Properties> get() {
            return formValidationConfig;
        }

        private void setFormValidationConfig(Map<String, Properties> formFields) {
            this.formValidationConfig = formFields;
        }
    }

    public static void readProperties() {
        ObjectMapper jsonMapper = new ObjectMapper();

        Arrays.stream(DotProperties.values()).forEach(property -> {
            Properties loadedProperty = new Properties();
            try {
                loadedProperty.load(Objects.requireNonNull(PropertiesContainer.class.getClassLoader()
                        .getResourceAsStream(property.FILE_PATH)));
                property.setProp(loadedProperty);
            } catch (IOException | NullPointerException e) {
                log.error("Could not read properties from file " + property.FILE_PATH + " in classpath. " + e);
            }

        });

        Arrays.stream(FormValidationConfig.values()).forEach(jsonProperty -> {

            try {
                jsonProperty.setFormValidationConfig(jsonMapper
                        .readValue(IOUtils.toString(Objects.requireNonNull(PropertiesContainer.class.getClassLoader()
                                        .getResourceAsStream(jsonProperty.FILE_PATH)), StandardCharsets.UTF_8),
                                new TypeReference<Map<String, Properties>>() {
                                }));
            } catch (IOException e) {
                log.error("Could not read properties from file " + jsonProperty.FILE_PATH + " in classpath. " + e);
            }
        });
    }
}
