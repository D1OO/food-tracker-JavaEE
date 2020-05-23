package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.dto.FoodDTO;
import net.shvdy.nutrition_tracker.model.entity.Food;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodEntityMapper {

	public FoodDTO entityToDTO(Food food) {
		return FoodDTO.builder().name(food.getName())
				.calories(food.getCalories())
				.fats(food.getFats())
				.prot(food.getProteins())
				.carbohydrates(food.getCarbohydrates())
				.build();
	}

	public Food DTOToEntity(FoodDTO foodDTO) {
		return Food.builder().name(foodDTO.getName())
				.calories(foodDTO.getCalories())
				.proteins(foodDTO.getProt())
				.carbohydrates(foodDTO.getCarbohydrates())
				.fats(foodDTO.getFats())
				.build();
	}
}
