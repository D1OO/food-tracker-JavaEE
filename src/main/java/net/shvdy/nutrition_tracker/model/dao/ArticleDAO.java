package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.entity.Article;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public interface ArticleDAO extends GenericDAO<Article> {

    int save(Article article) throws SQLException, IOException;

    List<Article> findPaginatedLocalised(Locale locale) throws SQLException;

    Optional<Article> findByIDLocalised(int articleID, Locale locale) throws SQLException;
}
