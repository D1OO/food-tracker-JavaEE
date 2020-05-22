package net.shvdy.nutrition_tracker.model.dao.mapper;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Food;

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

		DailyRecord dailyRecord = DailyRecord.builder()
				.recordId(resultSet.getLong("record_id"))
				.recordDate(resultSet.getString("record_date"))
				.dailyCaloriesNorm(resultSet.getInt("daily_calories_norm"))
				.build();

		ArrayList<DailyRecordEntry> dailyRecordEntries = new ArrayList<>();
		dailyRecordEntries.add(extractFromResultSet(resultSet, dailyRecord));
		while (resultSet.next()){
			dailyRecordEntries.add(extractFromResultSet(resultSet, dailyRecord));
		}
		dailyRecord.setEntries(dailyRecordEntries);

		return dailyRecord;
	}

	private DailyRecordEntry extractFromResultSet(ResultSet resultSet, DailyRecord dailyRecord) throws SQLException {
		return DailyRecordEntry.builder()
				.entryId(resultSet.getLong("entry_id"))
				.quantity(resultSet.getInt("quantity"))
				.dailyRecord(dailyRecord)
				.food(Food.builder()
						.food_id(resultSet.getLong("food_id"))
						.name(resultSet.getString("name"))
						.calories(resultSet.getInt("calories"))
						.fats(resultSet.getInt("fats"))
						.proteins(resultSet.getInt("proteins"))
						.carbohydrates(resultSet.getInt("carbohydrates"))
						.build())
				.build();
	}
}
