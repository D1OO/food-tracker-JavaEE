package net.shvdy.nutrition_tracker.controller.command.utils;

import net.shvdy.nutrition_tracker.PropertiesReader;
import net.shvdy.nutrition_tracker.controller.exception.FoodValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class FoodValidator {

	private enum NewFoodFields {
		NAME("newFoodName", "newFoodNameError",
				"validation.test", "Incorrect name"),
		CALORIES("newFoodCalories", "newFoodCaloriesError",
				"validation.test2", "Incorrect calories");

		String paramName;
		String paramErrorName;
		String regexParamName;
		String errorMsg;

		NewFoodFields(String paramName, String paramErrorName, String regexParamName, String errorMsg) {
			this.paramName = paramName;
			this.paramErrorName = paramErrorName;
			this.regexParamName = regexParamName;
			this.errorMsg = errorMsg;
		}

		public String getParamName() {
			return paramName;
		}

		public String getParamErrorName() {
			return paramErrorName;
		}

		public String getRegexParamName() {
			return regexParamName;
		}

		public String getErrorMsg() {
			return errorMsg;
		}
	}

	public void validate(HttpServletRequest request) throws FoodValidationException {
		Arrays.stream(NewFoodFields.values()).forEach(x -> request.getServletContext().setAttribute(x.getParamErrorName(),
				request.getParameter(x.getParamName()).matches(PropertiesReader.Props.VALIDATION_REGEX.getProp()
						.getProperty(x.getRegexParamName())) ? null : x.getErrorMsg()));

		if (Arrays.stream(NewFoodFields.values())
				.anyMatch(x -> request.getServletContext().getAttribute(x.getParamName()) != null))
			throw new FoodValidationException();

	}
}
