package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.dto.FoodDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class UserEntityMapper {

    private FoodEntityMapper foodEntityMapper = new FoodEntityMapper();

    public UserDTO entityToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .role(user.getRole())
                .username(user.getUsername())
                .firstNameLocalisation(user.getUserProfile().getFirstNameLocalisation())
                .lastName(user.getUserProfile().getLastName())
                .dailyCaloriesNorm(getDailyCaloriesNorm(user))
                .userProfileDTO(userProfileEntityToDTO(user.getUserProfile()))
                .userFood(mapFoodList(user.getUserProfile().getUserFood()))
                .build();
    }

    private List<FoodDTO> mapFoodList(List<Food> foodList) {
        return foodList.stream().map(foodEntityMapper::entityToDTO).collect(Collectors.toList());
    }

    private UserProfileDTO userProfileEntityToDTO(UserProfile userProfile) {
        return UserProfileDTO.builder()
                .profileId(userProfile.getProfileId())
                .firstNameEN(userProfile.getFirstNameEN())
                .firstNameRU(userProfile.getFirstNameRU())
                .lastName(userProfile.getLastName())
                .lifestyle(userProfile.getLifestyle())
                .age(userProfile.getAge())
                .height(userProfile.getHeight())
                .weight(userProfile.getWeight())
                .build();
    }

    public UserProfile userProfileDTOToEntity(UserProfileDTO userProfileDTO) {
        return UserProfile.builder()
                .firstNameEN(userProfileDTO.getFirstNameEN())
                .firstNameRU(userProfileDTO.getFirstNameRU())
                .lastName(userProfileDTO.getLastName())
                .lifestyle(userProfileDTO.getLifestyle())
                .age(userProfileDTO.getAge())
                .weight(userProfileDTO.getWeight())
                .height(userProfileDTO.getHeight())
                .profileId(userProfileDTO.getProfileId())
                .build();
    }

    private int getDailyCaloriesNorm(User user) {
        Properties formulaData = PropertiesContainer.DotProperties.APP_PROPERTIES.getProp();
        double coef1 = parsePropertyValue(formulaData.get("daily-calories-norm-formula.coef1"));
        double weightModifier = parsePropertyValue(formulaData.get("daily-calories-norm-formula.weight-modifier"));
        double heightModifier = parsePropertyValue(formulaData.get("daily-calories-norm-formula.height-modifier"));
        double ageModifier = parsePropertyValue(formulaData.get("daily-calories-norm-formula.age-modifier"));

        return (int) (coef1 + weightModifier * user.getUserProfile().getWeight() + heightModifier
                * user.getUserProfile().getHeight() - ageModifier * user.getUserProfile().getAge()
                * user.getUserProfile().getLifestyle().getFactor());
    }

    private Double parsePropertyValue(Object propertyValue) {
        return Double.parseDouble(String.valueOf(propertyValue));
    }
}
