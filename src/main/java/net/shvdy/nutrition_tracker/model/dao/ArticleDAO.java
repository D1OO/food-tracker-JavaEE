package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.Article;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;

import java.sql.SQLException;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public interface ArticleDAO  extends GenericDAO<Article> {

	Long save(Article article) throws SQLException;
}
