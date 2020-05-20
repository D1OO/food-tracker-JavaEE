package net.shvdy.nutrition_tracker.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {

    T map(ResultSet resultSet) throws SQLException;
}
