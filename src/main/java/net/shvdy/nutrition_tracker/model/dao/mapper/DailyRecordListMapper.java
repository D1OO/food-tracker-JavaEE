package net.shvdy.nutrition_tracker.model.dao.mapper;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordListMapper implements ResultSetMapper<List<DailyRecord>> {

	@Override
	public List<DailyRecord> map(ResultSet resultSet) throws SQLException {
		ArrayList<DailyRecordEntry> entries = new ArrayList<>();
		HashMap<Long, DailyRecord> paginatedRecords = new HashMap<>();
		while (resultSet.next()) {
			entries.add(extractEntryFromResultSet(resultSet));
			paginatedRecords.put(resultSet.getLong("record_id"), extractRecordFromResultSet(resultSet));
		}

		entries.forEach(entry -> paginatedRecords.get(entry.getRecordId())
				.getEntries().add(entry));

		return new ArrayList<>(paginatedRecords.values());
	}

	private DailyRecordEntry extractEntryFromResultSet(ResultSet resultSet) throws SQLException {
		return DailyRecordEntry.builder()
				.entryId(resultSet.getLong("entry_id"))
				.recordId(resultSet.getLong("record_id"))
				.quantity(resultSet.getInt("quantity"))
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

	private DailyRecord extractRecordFromResultSet(ResultSet resultSet) throws SQLException {
		return DailyRecord.builder()
				.recordId(resultSet.getLong("record_id"))
				.userProfileId(resultSet.getLong("profile_id"))
				.recordDate(resultSet.getString("record_date"))
				.dailyCaloriesNorm(resultSet.getInt("daily_calories_norm"))
				.entries(new ArrayList<>())
				.build();
	}

}

