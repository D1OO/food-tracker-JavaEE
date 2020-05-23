package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.FoodDTO;
import net.shvdy.nutrition_tracker.model.dao.FoodDAO;
import net.shvdy.nutrition_tracker.model.service.mapper.FoodEntityMapper;

import java.sql.SQLException;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodService {

	private final FoodDAO foodDAO;
	private final FoodEntityMapper foodMapper;

	public FoodService(FoodDAO foodDAO, FoodEntityMapper foodMapper) {
		this.foodDAO = foodDAO;
		this.foodMapper = foodMapper;
	}

	public Long saveForProfile(FoodDTO foodDTO, Long profileId) throws SQLException {
		return foodDAO.createForProfile(foodMapper.DTOToEntity(foodDTO), profileId);
	}

	public void getPaginated(String backInTimeFromDate, int quantity) {


	}
}
