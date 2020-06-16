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

    /**
     * Returns list of {@link Food}, whose name starting matches the {@code nameStartsWith} expression
     *
     * @param nameStartsWith Expression to compare name start with
     * @return {@link Food} list
     */
    public List<Food> findByNameStart(String nameStartsWith) {
        return foodDAO.findByNameStart(nameStartsWith);
    }
}
