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
        DAO_SQL_QUERIES("/DAO_SQL_queries.properties"),
        APP_PROPERTIES("/application.properties");

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

    public enum JSONProperties {
        USER_SIGN_UP_FORM_VALIDATION_DATA("form-validation-data/user-sign-up.json"),
        USER_PROFILE_FORM_VALIDATION_DATA("form-validation-data/user-profile.json"),
        FOOD_FORM_VALIDATION_DATA("form-validation-data/food.json"),
        ARTICLE_FORM_VALIDATION_DATA("form-validation-data/article.json"),
        GROUP_INVITE_VALIDATION_DATA("form-validation-data/group-invite.json");

        final String FILE_PATH;
        private Map<String, Map<String, String>> formFieldsValidationData;

        JSONProperties(String filePath) {
            this.FILE_PATH = filePath;
        }

        public Map<String, Map<String, String>> getFormFieldsValidationData() {
            return formFieldsValidationData;
        }

        private void setFormFieldsValidationData(Map<String, Map<String, String>> formFields) {
            this.formFieldsValidationData = formFields;
        }
    }


    public static void readProperties(ClassLoader classLoader) {
        Arrays.stream(DotProperties.values()).forEach(property -> {
            Properties loadedProperty = new Properties();
            try {
                loadedProperty.load(Objects.requireNonNull(classLoader.getResourceAsStream(property.FILE_PATH)));
            } catch (IOException | NullPointerException e) {
                log.error("Could not read properties from file " + property.FILE_PATH + " in classpath. " + e);
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
                log.error("Could not read properties from file " + jsonProperty.FILE_PATH + " in classpath. " + e);
            }
        });
    }
}
