package net.shvdy.nutrition_tracker.controller.command.utils;

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
