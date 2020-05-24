package net.shvdy.nutrition_tracker.controller.command.utils;

import net.shvdy.nutrition_tracker.PropertiesReader;
import net.shvdy.nutrition_tracker.dto.FoodDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodValidator {

	public void validate(HttpServletRequest request) throws IOException {

//		FoodDTO foodDTO = FoodDTO.builder().name(request.getParameter("newFoodName"))
//				.calories(Integer.parseInt(request.getParameter("newFoodCalories")))
//				.carbohydrates(Integer.parseInt(request.getParameter("newFoodCarbohydrates")))
//				.fats(Integer.parseInt(request.getParameter("newFoodFats")))
//				.proteins(Integer.parseInt(request.getParameter("newFoodProteins")))
//				.build();

		System.out.println(PropertiesReader.Props.VALIDATION_REGEX.getProp().get("validation_test"));

	}
}
