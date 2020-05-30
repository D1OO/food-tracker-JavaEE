package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsernameLocalised(String username, Locale locale) throws SQLException;

    void create(User user) throws SQLException;

    void updateProfile(UserProfile userProfile) throws SQLException;
}
