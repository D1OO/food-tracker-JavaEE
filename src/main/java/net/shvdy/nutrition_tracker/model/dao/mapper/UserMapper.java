package net.shvdy.nutrition_tracker.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

public class UserMapper implements ObjectMapper<User> {

	@Override
	public User mapResultSet(ResultSet resultSet) throws SQLException {
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
						.lastName(resultSet.getString("last_name")).build())
				.build();
	}
}
