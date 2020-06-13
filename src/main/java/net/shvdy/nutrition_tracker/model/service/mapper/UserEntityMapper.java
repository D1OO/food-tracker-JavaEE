package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.util.Properties;

public class UserEntityMapper {

    public static UserDTO entityToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .role(user.getRole())
                .username(user.getUsername())
                .firstName(user.getUserProfile().getFirstName())
                .lastName(user.getUserProfile().getLastName())
                .dailyCaloriesNorm(getDailyCaloriesNorm(user))
                .userProfileDTO(userProfileEntityToDTO(user.getUserProfile()))
                .userFood(user.getUserProfile().getUserFood())
                .build();
    }

    private static UserProfileDTO userProfileEntityToDTO(UserProfile userProfile) {
        return UserProfileDTO.builder()
                .profileId(userProfile.getProfileId())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .lifestyle(userProfile.getLifestyle())
                .age(userProfile.getAge())
                .height(userProfile.getHeight())
                .weight(userProfile.getWeight())
                .build();
    }

    public static UserProfile userProfileDTOToEntity(UserProfileDTO userProfileDTO) {
        return UserProfile.builder()
                .firstName(userProfileDTO.getFirstName())
                .lastName(userProfileDTO.getLastName())
                .lifestyle(userProfileDTO.getLifestyle())
                .age(userProfileDTO.getAge())
                .weight(userProfileDTO.getWeight())
                .height(userProfileDTO.getHeight())
                .profileId(userProfileDTO.getProfileId())
                .build();
    }

    private static int getDailyCaloriesNorm(User user) {
        Properties formulaData = PropertiesContainer.DotProperties.APP_PROPERTIES.getProp();
        double coef1 = parsePropertyValue(formulaData.get("daily-calories-norm-formula.coef1"));
        double weightModifier = parsePropertyValue(formulaData.get("daily-calories-norm-formula.weight-modifier"));
        double heightModifier = parsePropertyValue(formulaData.get("daily-calories-norm-formula.height-modifier"));
        double ageModifier = parsePropertyValue(formulaData.get("daily-calories-norm-formula.age-modifier"));

        return (int) (coef1 + weightModifier * user.getUserProfile().getWeight() + heightModifier
                * user.getUserProfile().getHeight() - ageModifier * user.getUserProfile().getAge()
                * user.getUserProfile().getLifestyle().getFactor());
    }

    private static Double parsePropertyValue(Object propertyValue) {
        return Double.parseDouble(String.valueOf(propertyValue));
    }
}
