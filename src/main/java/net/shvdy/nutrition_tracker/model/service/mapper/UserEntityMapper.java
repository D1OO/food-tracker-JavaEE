package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.dto.FoodDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.entity.User;

import java.util.List;
import java.util.Properties;
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
        Properties formulaData = PropertiesContainer.DotProperties.APP_PROPERTIES.getProp();
        int coef1 = (int) formulaData.get("daily-calories-norm-formula.coef1");
        int weightModifier = (int) formulaData.get("daily-calories-norm-formula.weight-modifier");
        int heightModifier = (int) formulaData.get("daily-calories-norm-formula.height-modifier");
        int ageModifier = (int) formulaData.get("daily-calories-norm-formula.age-modifier");

        return (int) (coef1 + weightModifier * user.getUserProfile().getWeight() + heightModifier
                * user.getUserProfile().getHeight() - ageModifier * user.getUserProfile().getAge()
                * user.getUserProfile().getLifestyle().getFactor());
    }
}
