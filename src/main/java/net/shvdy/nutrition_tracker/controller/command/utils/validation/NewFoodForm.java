package net.shvdy.nutrition_tracker.controller.command.utils.validation;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public enum NewFoodForm implements ValidatedForm {
	NAME("newFoodName", "newFoodNameError",
			"food.name", "Incorrect name");
//		CALORIES("newFoodCalories", "newFoodCaloriesError",
//				"food_calories", "Incorrect calories");

	String paramName;
	String paramErrorName;
	String regexParamName;
	String errorMsg;

	NewFoodForm(String paramName, String paramErrorName, String regexParamName, String errorMsg) {
		this.paramName = paramName;
		this.paramErrorName = paramErrorName;
		this.regexParamName = regexParamName;
		this.errorMsg = errorMsg;
	}

	public String getFieldName() {
		return paramName;
	}

	public String getFieldErrorName() {
		return paramErrorName;
	}

	public String getFieldRegexPropertyName() {
		return regexParamName;
	}

	public String getFieldErrorMsg() {
		return errorMsg;
	}

	@Override
	public NewFoodForm[] getFields() {
		return NewFoodForm.values();
	}
}


