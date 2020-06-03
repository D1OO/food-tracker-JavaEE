package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.util.Locale;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsernameLocalised(String username, Locale locale);

    void create(User user);

    void updateProfile(UserProfile userProfile);
}
