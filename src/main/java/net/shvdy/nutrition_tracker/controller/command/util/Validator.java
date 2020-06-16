package net.shvdy.nutrition_tracker.controller.command.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 27.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Validator {

    /**
     * Accepts request with form fields to validate, and a form validation config from
     * {@link net.shvdy.nutrition_tracker.PropertiesContainer.FormValidationConfig}
     * where form field name is the key and the value is {@link Properties} with a validation parameters
     * <br><br>
     * Recognizes the next properties as config parameters:<br>
     * {@code "regex"} - regular expression to match with<br>
     * {@code "max"} - maximum(including) value for numeric fields<br>
     * {@code "min"} - minimum(including) value for numeric fields<br>
     * <br>
     * Form field names must be identical between request parameters and {@code fieldsValidationConfig} Map keys
     * <br><br>
     * Returns validation errors in a Map, where the key is form field name + "Error", and the value is the error message, defined in the
     * {@code "errorMessage"} property
     *
     * @param request                Request with a form to validate
     * @param fieldsValidationConfig Validation configuration
     * @return Form validation errors
     */
    public static Map<String, String> validateFormAndReturnErrors(HttpServletRequest request,
                                                                  Map<String, Properties> fieldsValidationConfig) {
        return Stream.of(
                fieldsValidationConfig.entrySet().stream()
                        .filter(f -> !request.getParameter(f.getKey()).matches(f.getValue().getProperty("regex"))),
                fieldsValidationConfig.entrySet().stream()
                        .filter(f -> Optional.ofNullable(f.getValue().get("max")).isPresent())
                        .filter(f -> errorIfXMoreY.test(request.getParameter(f.getKey()), f.getValue().getProperty("max"))),
                fieldsValidationConfig.entrySet().stream()
                        .filter(f -> Optional.ofNullable(f.getValue().get("min")).isPresent())
                        .filter(f -> errorIfXLessY.test(request.getParameter(f.getKey()), f.getValue().getProperty("min"))))
                .flatMap(i -> i)
                .collect(Collectors.toMap(f -> f.getKey() + "Error", f -> f.getValue().getProperty("errorMessage"),
                        (duplicate1, duplicate2) -> duplicate1));
    }

    private static BiPredicate<String, String> errorIfXMoreY = (x, y) -> {
        try {
            return Double.parseDouble(x) > Double.parseDouble(y);
        } catch (Exception e) {
            return true;
        }
    };

    private static BiPredicate<String, String> errorIfXLessY = (x, y) -> {
        try {
            return Double.parseDouble(x) < Double.parseDouble(y);
        } catch (Exception e) {
            return true;
        }
    };

}
