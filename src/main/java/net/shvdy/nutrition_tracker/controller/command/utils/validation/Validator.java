package net.shvdy.nutrition_tracker.controller.command.utils.validation;

import net.shvdy.nutrition_tracker.PropertiesReader;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 27.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Validator {

	public static Map<String, String> validateFormAndReturnErrors(HttpServletRequest request, ValidatedForm[] fields) {
		return Arrays.stream(fields)
				.filter(field -> !request.getParameter(field.getFieldName())
						.matches(PropertiesReader.Props.VALIDATION_REGEX.getProp().getProperty(field.getFieldRegexPropertyName())))
				.collect(Collectors.toMap(ValidatedForm::getFieldErrorName, ValidatedForm::getFieldErrorMsg));
	}
}
