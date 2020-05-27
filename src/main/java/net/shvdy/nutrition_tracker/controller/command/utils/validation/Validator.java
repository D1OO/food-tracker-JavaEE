package net.shvdy.nutrition_tracker.controller.command.utils.validation;

import net.shvdy.nutrition_tracker.PropertiesReader;
import net.shvdy.nutrition_tracker.controller.exception.ValidationErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 27.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Validator {

	public static void validateForm(HttpServletRequest request, ValidatedForm[] fields)
			throws ValidationErrorException {
		Arrays.stream(fields).forEach(x ->
				request.getServletContext().setAttribute(x.getFieldErrorName(),
						request.getParameter(x.getFieldName()).matches(PropertiesReader.Props.VALIDATION_REGEX.getProp()
								.getProperty(x.getFieldRegexPropertyName())) ? "" : x.getFieldErrorMsg()));

		if (Arrays.stream(fields)
				.anyMatch(x -> request.getServletContext().getAttribute(x.getFieldErrorName()).toString().length() > 0))
			throw new ValidationErrorException();

	}
}
