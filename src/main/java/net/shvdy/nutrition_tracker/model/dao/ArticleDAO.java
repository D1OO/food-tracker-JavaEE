package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.Article;

import java.sql.SQLException;
import java.util.List;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public interface ArticleDAO extends GenericDAO<Article> {

	int save(Article article) throws SQLException;

	List<Article> findPaginated() throws SQLException;

	Article findByID(int articleID) throws SQLException;
}
