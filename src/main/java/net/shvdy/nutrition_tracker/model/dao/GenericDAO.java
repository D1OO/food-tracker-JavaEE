package net.shvdy.nutrition_tracker.model.dao;

import java.sql.SQLException;

public interface GenericDAO<T> {

    void create(T entity) throws SQLException;
}
