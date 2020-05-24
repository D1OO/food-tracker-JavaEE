package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.dto.FoodDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserEntityMapper {

	private FoodEntityMapper foodEntityMapper = new FoodEntityMapper();

	public UserDTO entityToDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.role(user.getRole())
				.firstName(user.getUserProfile().getFirstName())
				.lastName(user.getUserProfile().getLastName())
				.userFoodDTO(mapFoodList(user.getUserProfile().getUserFood()))
				.build();
	}

	private List<FoodDTO> mapFoodList(List<Food> foodList) {
		return foodList.stream().map(foodEntityMapper::entityToDTO).collect(Collectors.toList());
	}
}
