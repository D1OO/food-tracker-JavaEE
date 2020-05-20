package net.shvdy.nutrition_tracker.model.dao.mapper;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordMapper implements ResultSetMapper<DailyRecord> {
	@Override
	public DailyRecord map(ResultSet resultSet) throws SQLException {
		return DailyRecord.builder()
				.recordId(resultSet.getLong("record_id"))
				.recordDate(resultSet.getString("record_date"))
				.userProfile(new UserProfile())
				.entries(new ArrayList<>())
				.build();
	}
}
