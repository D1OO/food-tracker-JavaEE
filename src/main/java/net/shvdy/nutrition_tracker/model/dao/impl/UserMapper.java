package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserMapper {

    public User extractUser(ResultSet resultSet, Locale locale) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("email"))
                .account_non_expired(resultSet.getBoolean("account_non_expired"))
                .account_non_locked(resultSet.getBoolean("account_non_locked"))
                .credentials_non_expired(resultSet.getBoolean("credentials_non_expired"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role")))
                .enabled(resultSet.getBoolean("enabled"))
                .userProfile(UserProfile.builder()
                        .profileId(resultSet.getLong("profile_id"))
                        .firstNameLocalised(resultSet.getString("first_name_" + locale.getLanguage()))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .weight(resultSet.getInt("weight"))
                        .height(resultSet.getInt("height"))
                        .lifestyle(Enum.valueOf(UserProfile.Lifestyle.class, resultSet.getString("lifestyle")))
                        .userFood(extractFood(resultSet)).build())
                .build();
    }

    private List<Food> extractFood(ResultSet rs) throws SQLException {
        List<Food> userFood = new ArrayList<>();
        do {
            userFood.add(Food.builder().food_id(rs.getLong("food_id")).name(rs.getString("name"))
                    .calories(rs.getInt("calories")).fats(rs.getInt("fats")).proteins(rs.getInt("proteins"))
                    .carbohydrates(rs.getInt("carbohydrates")).build());
        } while (rs.next());
        return userFood;
    }

    public List<User> extractGroup(ResultSet rs) throws SQLException {
        List<User> group = new ArrayList<>();
        do {
            group.add(User.builder().userProfile(UserProfile.builder()
                    .firstNameLocalised(rs.getString("first_name_localized"))
                    .lastName(rs.getString("last_name"))
                    .age(rs.getInt("age"))
                    .height(rs.getInt("height"))
                    .weight(rs.getInt("weight"))
                    .lifestyle(Enum.valueOf(UserProfile.Lifestyle.class, rs.getString("lifestyle"))).build()).build());
        } while (rs.next());
        return group;
    }

}
