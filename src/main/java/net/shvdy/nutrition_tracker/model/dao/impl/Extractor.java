package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Extractor {

    public User extractUser(ResultSet resultSet) throws SQLException {
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
                        .firstName(resultSet.getString("first_name"))
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
            group.add(User.builder()
                    .username(rs.getString("email"))
                    .userProfile(UserProfile.builder()
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .age(rs.getInt("age"))
                            .height(rs.getInt("height"))
                            .weight(rs.getInt("weight"))
                            .lifestyle(Enum.valueOf(UserProfile.Lifestyle.class,
                                    rs.getString("lifestyle"))).build()).build());
        } while (rs.next());
        return group;
    }

    public Set<Notification> extractNotifications(ResultSet rs, UserDTO receiver) throws SQLException {
        Set<Notification> notifications = new HashSet<>();
        do {
            String timeStampWithMs = rs.getTimestamp("datetime").toString();
            notifications.add(Notification.builder()
                    .sender(UserDTO.builder().username(rs.getString("sender_username"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name")).build())
                    .receiver(UserDTO.builder().username(receiver.getUsername()).build())
                    .dateTime(timeStampWithMs.substring(0, timeStampWithMs.length() - 2))
                    .message(rs.getString("message")).build());
        } while (rs.next());
        return notifications;
    }

}
