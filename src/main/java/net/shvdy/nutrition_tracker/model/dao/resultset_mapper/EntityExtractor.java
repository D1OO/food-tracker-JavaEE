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
public class EntityExtractor {

    public static Article extractArticle(ResultSet rs, Locale locale) throws SQLException {
        return Article.builder()
                .articleId(rs.getInt("article_id"))
                .authorId(rs.getLong("article_id"))
                .date(rs.getString("date_created"))
                .authorFirstName(rs.getString("first_name_" + locale.getLanguage()))
                .authorLastName(rs.getString("last_name"))
                .titleLocalisation(rs.getString("title_" + locale.getLanguage()))
                .textLocalisation(rs.getString("text_" + locale.getLanguage()))
                .image(rs.getBlob("image").getBinaryStream())
                .build();
    }

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
