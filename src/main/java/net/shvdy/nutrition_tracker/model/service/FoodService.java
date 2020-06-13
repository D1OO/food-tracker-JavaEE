package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.model.dao.FoodDAO;
import net.shvdy.nutrition_tracker.model.entity.Food;

import java.util.List;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodService {

    private final FoodDAO foodDAO;

    public FoodService(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    public Long saveForProfile(Food food, Long profileId) {
        return foodDAO.createForProfile(food, profileId);
    }

    public List<Food> foodSearch(String nameStartsWith) {
        return foodDAO.findByNameStart(nameStartsWith);
    }
}
