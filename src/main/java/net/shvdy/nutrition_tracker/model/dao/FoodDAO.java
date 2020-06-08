package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.Food;

import java.util.List;

public interface FoodDAO extends GenericDAO<Food> {

    Long createForProfile(Food food, Long profileId);

    List<Food> findByNameStart(String nameStartsWith);
}
