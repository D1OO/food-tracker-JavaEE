package net.shvdy.nutrition_tracker.controller.command.utils.validation;

public interface ValidatedForm {

	String getFieldName();

	String getFieldErrorName();

	String getFieldRegexPropertyName();

	String getFieldErrorMsg();

	ValidatedForm[] getFields();
}
