package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetMapper<T> {

    T map(ResultSet resultSet) throws SQLException;
}
