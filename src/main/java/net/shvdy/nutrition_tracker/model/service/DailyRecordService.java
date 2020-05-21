package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.service.mapper.DailyRecordEntityMapper;

import java.sql.SQLException;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordService {

	private final DailyRecordDAO dailyRecordDAO;
	private final DailyRecordEntityMapper dailyRecordMapper;

	public DailyRecordService(DailyRecordDAO dailyRecordDAO, DailyRecordEntityMapper dailyRecordMapper) {
		this.dailyRecordDAO = dailyRecordDAO;
		this.dailyRecordMapper = dailyRecordMapper;
	}

	public DailyRecordDTO findByDate(String date) throws SQLException {
		return dailyRecordMapper.entityToDTO(
				dailyRecordDAO.findByRecordDate(date)
						.orElse(new DailyRecord()));

//		return userDao.findByUsername(username)
//				.orElseThrow(() -> new UserNotFoundException(String.format("Username '%s' not found", username)));
	}

}
