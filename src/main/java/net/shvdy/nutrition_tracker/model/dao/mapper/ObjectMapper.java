package net.shvdy.nutrition_tracker.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper<T> {

    T mapResultSet(ResultSet resultSet) throws SQLException;
}
