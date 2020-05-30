package net.shvdy.nutrition_tracker.controller.command.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
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
                        .filter(field -> Double.parseDouble(request.getParameter(field.getKey())) >
                                Double.parseDouble(field.getValue().get("max"))),
                fieldsValidationData.entrySet().stream()
                        .filter(field -> Optional.ofNullable(field.getValue().get("min")).isPresent())
                        .filter(field -> Double.parseDouble(request.getParameter(field.getKey())) <
                                Double.parseDouble(field.getValue().get("min"))))
                .flatMap(i -> i)
                .collect(Collectors.toMap(field -> field.getKey() + "Error", field -> field.getValue().get("errorMessage"),
                        (duplicate1, duplicate2) -> duplicate1));
    }
}
