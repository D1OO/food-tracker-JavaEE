package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsername(String username) throws SQLException;

    void create(User user) throws SQLException;
}
