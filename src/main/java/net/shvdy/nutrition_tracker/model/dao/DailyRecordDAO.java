package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;

import java.sql.SQLException;
import java.util.List;

public interface DailyRecordDAO extends GenericDAO<DailyRecord> {

	List<DailyRecord> findFromDateByQuantity(String recordDate, int quantity, Long profileId) throws SQLException;

	void save(DailyRecord dailyRecord) throws SQLException;
}