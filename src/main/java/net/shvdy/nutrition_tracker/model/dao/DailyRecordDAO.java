package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface DailyRecordDAO extends GenericDAO<DailyRecord> {

	Optional<DailyRecord> findByRecordDate(String recordDate) throws SQLException;

	void save (DailyRecord dailyRecord) throws SQLException;
}