package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import net.shvdy.nutrition_tracker.model.entity.Article;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleMapper implements  ResultSetMapper<Article> {
	@Override
	public Article map(ResultSet resultSet) throws SQLException {
		return null;
	}
}
