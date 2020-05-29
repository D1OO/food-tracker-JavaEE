package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.ArticleDAO;
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
    private Properties queries;

    public JDBCArticleDAO(DataSource dataSource, ResultSetMapperLocalised<List<Article>> resultSetMapper, Properties queries) {
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
            insertArticle.setString(2, article.getTitle());
            insertArticle.setLong(3, article.getAuthorId());
            insertArticle.setString(4, article.getDate());
            insertArticle.setString(5, article.getText());
            insertArticle.setBlob(6, article.getImage());
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
                     .prepareStatement(queries.getProperty("article_dao.SELECT_BY_DATE_AND_QUANTITY"))) {

            return resultSetMapper.mapLocalised(statement.executeQuery(), locale);
        }
    }

    @Override
    public Article findByIDLocalised(int articleId, Locale locale) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("article_dao.SELECT_BY_ID"))) {

            statement.setInt(1, articleId);
            return resultSetMapper.mapLocalised(statement.executeQuery(), locale).get(0); //TODO f
        }
    }

    private void setLongOrNull(PreparedStatement statement, int index, int value) throws SQLException {
        try {
            statement.setLong(index, value);
        } catch (NullPointerException e) {
            statement.setNull(index, Types.BIGINT);
        }
    }
}
