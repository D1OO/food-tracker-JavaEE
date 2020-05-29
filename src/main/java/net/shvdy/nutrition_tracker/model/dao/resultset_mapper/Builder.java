package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import net.shvdy.nutrition_tracker.model.entity.Article;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * 27.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Builder {

    static Food buildFood(ResultSet rs) throws SQLException {
        return Food.builder()
                .food_id(rs.getLong("food_id"))
                .name(rs.getString("name"))
                .calories(rs.getInt("calories"))
                .fats(rs.getInt("fats"))
                .proteins(rs.getInt("proteins"))
                .carbohydrates(rs.getInt("carbohydrates"))
                .build();
    }

    static DailyRecord buildDailyRecord(ResultSet rs) throws SQLException {
        return DailyRecord.builder()
                .recordId(rs.getLong("record_id"))
                .userProfileId(rs.getLong("profile_id"))
                .recordDate(rs.getString("record_date"))
                .dailyCaloriesNorm(rs.getInt("daily_calories_norm"))
                .entries(new ArrayList<>())
                .build();
    }

    static DailyRecordEntry buildDailyRecordEntry(ResultSet rs) throws SQLException {
        return DailyRecordEntry.builder()
                .entryId(rs.getLong("entry_id"))
                .recordId(rs.getLong("record_id"))
                .quantity(rs.getInt("quantity"))
                .food(Builder.buildFood(rs))
                .build();
    }

    static Article buildArticle(ResultSet rs, Locale locale) throws SQLException {
        return Article.builder()
                .articleId(rs.getInt("article_id"))
                .authorId(rs.getLong("article_id"))
                .date(rs.getString("date_created"))
                .authorFirstName(rs.getString("first_name_" + locale.getLanguage()))
                .authorLastName(rs.getString("last_name"))
                .title(rs.getString("title"))
                .text(rs.getString("text"))
                .image(rs.getBlob("image").getBinaryStream())
                .build();
    }
}
