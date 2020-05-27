package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import net.shvdy.nutrition_tracker.model.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodMapper implements ResultSetMapper<Food> {

	@Override
	public Food map(ResultSet resultSet) throws SQLException {
		return Builder.buildFood(resultSet);
	}
}
