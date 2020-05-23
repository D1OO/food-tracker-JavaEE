package net.shvdy.nutrition_tracker.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

public class UserMapper implements ResultSetMapper<User> {

	@Override
	public User map(ResultSet resultSet) throws SQLException {
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
						.userFood(setFood(resultSet)).build())
				.build();
	}

	private List<Food> setFood(ResultSet rs) throws SQLException {
		List<Food> userFood = new ArrayList<>();

		userFood.add(extractFoodFromResultSet(rs));
		while (rs.next()){
			userFood.add(extractFoodFromResultSet(rs));
		}

		return userFood;
	}

	private Food extractFoodFromResultSet(ResultSet rs) throws SQLException {
		return Food.builder()
				.food_id(rs.getLong("food_id"))
				.name(rs.getString("name"))
				.calories(rs.getInt("calories"))
				.carbohydrates(rs.getInt("carbohydrates"))
				.proteins(rs.getInt("proteins"))
				.fats(rs.getInt("fats"))
				.build();
	}
}
