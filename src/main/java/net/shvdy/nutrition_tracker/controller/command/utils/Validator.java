package net.shvdy.nutrition_tracker.controller.command.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 27.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Validator {

    public static Map<String, String> validateFormAndReturnErrors(HttpServletRequest request,
                                                                  Map<String, Map<String, String>> fieldsValidationData) {
        return fieldsValidationData.entrySet().stream()
                .filter(field -> !request.getParameter(field.getKey()).matches((field.getValue().get("regex"))))
                .collect(Collectors.toMap(field -> field.getKey() + "Error", field -> field.getValue().get("errorMessage")));
    }
}
