package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.DailyRecordMapper;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordService {

	private final DailyRecordDAO dailyRecordDAO;
	private final DailyRecordMapper dailyRecordMapper;

	public DailyRecordService(DailyRecordDAO dailyRecordDAO, DailyRecordMapper dailyRecordMapper) {
		this.dailyRecordDAO = dailyRecordDAO;
		this.dailyRecordMapper = dailyRecordMapper;
	}

	public DailyRecordDTO findByDate() {
		return new DailyRecordDTO();
	}

}
