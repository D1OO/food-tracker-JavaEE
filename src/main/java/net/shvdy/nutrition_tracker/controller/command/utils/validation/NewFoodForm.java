package net.shvdy.nutrition_tracker.controller.command.utils.validation;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public enum NewFoodForm implements ValidatedForm {
	NAME("newFoodName", "newFoodNameError",
			"food.name", "Incorrect name"),
	CALORIES("newFoodCalories", "newFoodCaloriesError",
			"food.calories", "Incorrect value (only digits)"),
	PROT("newFoodProt", "newFoodProtError",
			"food.prot", "Incorrect value (only digits)"),
	FATS("newFoodFats", "newFoodFatsError",
			"food.fats", "Incorrect value (only digits)"),
	CARBOHYDRATES("newFoodCarbohydrates", "newFoodCarbohydratesError",
			"food.carbohydrates", "Incorrect value (only digits)");

	String fieldName;
	String fieldErrorName;
	String fieldRegexKey;
	String validationErrorMsg;

	NewFoodForm(String fieldName, String fieldErrorName, String fieldRegexKey, String validationErrorMsg) {
		this.fieldName = fieldName;
		this.fieldErrorName = fieldErrorName;
		this.fieldRegexKey = fieldRegexKey;
		this.validationErrorMsg = validationErrorMsg;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldErrorName() {
		return fieldErrorName;
	}

	public String getFieldRegexPropertyName() {
		return fieldRegexKey;
	}

	public String getFieldErrorMsg() {
		return validationErrorMsg;
	}

}


