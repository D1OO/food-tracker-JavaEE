package net.shvdy.nutrition_tracker.controller.command.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
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
                                                                  Map<String, Map<String, String>> fieldsValidationData) {
        return Stream.of(
                fieldsValidationData.entrySet().stream()
                        .filter(field -> !request.getParameter(field.getKey()).matches((field.getValue().get("regex")))),
                fieldsValidationData.entrySet().stream()
                        .filter(field -> Optional.ofNullable(field.getValue().get("max")).isPresent())
                        .filter(field -> errorIfP1MoreP2
                                .test(request.getParameter(field.getKey()), field.getValue().get("max"))),
                fieldsValidationData.entrySet().stream()
                        .filter(field -> Optional.ofNullable(field.getValue().get("min")).isPresent())
                        .filter(field -> errorIfP1LessP2
                                .test(request.getParameter(field.getKey()), field.getValue().get("min"))))
                .flatMap(i -> i)
                .collect(Collectors.toMap(field -> field.getKey() + "Error", field -> field.getValue().get("errorMessage"),
                        (duplicate1, duplicate2) -> duplicate1));
    }

    private static BiPredicate<String, String> errorIfP1MoreP2 = (x, y) -> {
        try {
            return Double.parseDouble(x) > Double.parseDouble(y);
        } catch (Exception e) {
            return true;
        }
    };

    private static BiPredicate<String, String> errorIfP1LessP2 = (x, y) -> {
        try {
            return Double.parseDouble(x) < Double.parseDouble(y);
        } catch (Exception e) {
            return true;
        }
    };

}
