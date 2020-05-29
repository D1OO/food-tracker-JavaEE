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
                .dailyCaloriesNorm(getDailyCaloriesNorm(user))
                .userFood(mapFoodList(user.getUserProfile().getUserFood()))
                .build();
    }

    private List<FoodDTO> mapFoodList(List<Food> foodList) {
        return foodList.stream().map(foodEntityMapper::entityToDTO).collect(Collectors.toList());
    }

    private int getDailyCaloriesNorm(User user) {
        return (int) ((66 + 13.75 * user.getUserProfile().getWeight() + 5 *
                user.getUserProfile().getHeight() - 6.755 * user.getUserProfile().getAge()) *
                user.getUserProfile().getLifestyle().getFactor());
    }
}
