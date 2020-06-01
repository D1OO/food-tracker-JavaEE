package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.ArticleDAO;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.EntityExtractor;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.ResultSetMapperLocalised;
import net.shvdy.nutrition_tracker.model.entity.Article;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCArticleDAO implements ArticleDAO {

    private DataSource dataSource;
    private ResultSetMapperLocalised<List<Article>> resultSetMapper;
    private final Properties queries;

    public JDBCArticleDAO(DataSource dataSource, ResultSetMapperLocalised<List<Article>> resultSetMapper,
                          Properties queries) {
        this.dataSource = dataSource;
        this.resultSetMapper = resultSetMapper;
        this.queries = queries;
    }

    @Override
    public int save(Article article) throws SQLException, IOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertArticle = connection
                     .prepareStatement(queries.getProperty("article_dao.INSERT_ARTICLE_SQL"),
                             Statement.RETURN_GENERATED_KEYS)) {

            setLongOrNull(insertArticle, 1, article.getArticleId());
            insertArticle.setString(2, article.getTitleEN());
            insertArticle.setString(3, article.getTitleRU());
            insertArticle.setLong(4, article.getAuthorId());
            insertArticle.setString(5, article.getDate());
            insertArticle.setString(6, article.getTextEN());
            insertArticle.setString(7, article.getTextRU());
            insertArticle.setBlob(8, article.getImage());
            insertArticle.executeUpdate();

            if (Optional.ofNullable(article.getArticleId()).isEmpty()) {
                ResultSet generatedKeys = insertArticle.getGeneratedKeys();
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
            }

            article.getImage().close();
            return article.getArticleId();
        }
    }

    @Override
    public List<Article> findPaginatedLocalised(Locale locale) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(setLocaleColumnParameter(queries
                             .getProperty("article_dao.SELECT_BY_DATE_AND_QUANTITY"), locale))) {

            return resultSetMapper.mapLocalised(statement.executeQuery(), locale);
        }
    }

    @Override
    public Optional<Article> findByIDLocalised(int articleId, Locale locale) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(setLocaleColumnParameter(queries.getProperty("article_dao.SELECT_BY_ID"), locale))) {

            statement.setInt(1, articleId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(EntityExtractor.extractArticle(resultSet, locale));
            }
            return Optional.empty();
        }
    }

    private String setLocaleColumnParameter(String statement, Locale locale) {
        return statement
                .replace("title_?", "title_" + locale.getLanguage())
                .replace("text_?", "text_" + locale.getLanguage())
                .replace("first_name_?", "first_name_" + locale.getLanguage());
    }

    private void setLongOrNull(PreparedStatement statement, int index, int value) throws SQLException {
        try {
            statement.setLong(index, value);
        } catch (NullPointerException e) {
            statement.setNull(index, Types.BIGINT);
        }
    }
}
