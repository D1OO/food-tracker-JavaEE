package net.shvdy.food_tracker.model.dao;

import java.sql.SQLException;

public interface GenericDAO<T> {

    void create(T entity) throws SQLException;
}
