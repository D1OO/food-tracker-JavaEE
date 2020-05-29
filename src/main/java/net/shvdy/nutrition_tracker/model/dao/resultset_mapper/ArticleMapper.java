package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import net.shvdy.nutrition_tracker.model.entity.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleMapper implements ResultSetMapperLocalised<List<Article>> {
    @Override
    public List<Article> mapLocalised(ResultSet resultSet, Locale locale) throws SQLException {
        ArrayList<Article> articles = new ArrayList<>();
        while (resultSet.next()) {
            articles.add(extractArticleFromResultSet(resultSet, locale));
        }
        return articles;
    }

    private Article extractArticleFromResultSet(ResultSet resultSet, Locale locale) throws SQLException {
        return Builder.buildArticle(resultSet, locale);
    }

}
