package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.Food;

import java.sql.SQLException;

public interface FoodDAO extends GenericDAO<Food> {

	 Long createForProfile(Food food, Long profileId) throws SQLException;
}
