package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 27.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class EntityExtractor {

    static Food extractFood(ResultSet rs) throws SQLException {
        return Food.builder()
                .food_id(rs.getLong("food_id"))
                .name(rs.getString("name"))
                .calories(rs.getInt("calories"))
                .fats(rs.getInt("fats"))
                .proteins(rs.getInt("proteins"))
                .carbohydrates(rs.getInt("carbohydrates"))
                .build();
    }

    static DailyRecord extractDailyRecord(ResultSet rs) throws SQLException {
        return DailyRecord.builder()
                .recordId(rs.getLong("record_id"))
                .userProfileId(rs.getLong("profile_id"))
                .recordDate(rs.getString("record_date"))
                .dailyCaloriesNorm(rs.getInt("daily_calories_norm"))
                .entries(new ArrayList<>())
                .build();
    }

    static DailyRecordEntry extractDailyRecordEntry(ResultSet rs) throws SQLException {
        return DailyRecordEntry.builder()
                .entryId(rs.getLong("entry_id"))
                .recordId(rs.getLong("record_id"))
                .quantity(rs.getInt("quantity"))
                .food(EntityExtractor.extractFood(rs))
                .build();
    }

}
