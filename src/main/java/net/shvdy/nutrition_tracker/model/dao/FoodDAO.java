package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.Food;

public interface FoodDAO extends GenericDAO<Food> {

    Long createForProfile(Food food, Long profileId);
}
