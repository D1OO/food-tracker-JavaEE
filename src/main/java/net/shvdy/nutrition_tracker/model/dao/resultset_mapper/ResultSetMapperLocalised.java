package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public interface ResultSetMapperLocalised<T> {

    T mapLocalised(ResultSet resultSet, Locale locale) throws SQLException;
}
