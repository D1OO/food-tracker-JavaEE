package net.shvdy.food_tracker.model.dao;

import java.sql.SQLException;
import java.util.Optional;

import net.shvdy.food_tracker.model.entity.User;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsername(String username) throws SQLException;
}
