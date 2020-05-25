package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.ArticleDAO;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.Article;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCArticleDAO implements ArticleDAO {

	private DataSource dataSource;
	private ResultSetMapper<List<Article>> resultSetMapper;
	private Properties queries;

	public JDBCArticleDAO(DataSource dataSource, ResultSetMapper<List<Article>> resultSetMapper, Properties queries) {
		this.dataSource = dataSource;
		this.resultSetMapper = resultSetMapper;
		this.queries = queries;
	}

	@Override
	public void create(Article entity) throws SQLException {

	}

	@Override
	public Long save(Article article) throws SQLException {
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

			if (article.getArticleId() == null) {
				ResultSet generatedKeys = insertArticle.getGeneratedKeys();
				if (generatedKeys.next())
					return generatedKeys.getLong(1);
			}
			return article.getArticleId();
		}
	}

	@Override
	public List<Article> findPaginated() throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection
					 .prepareStatement(queries.getProperty("article_dao.SELECT_BY_DATE_AND_QUANTITY"))) {

			return resultSetMapper.map(statement.executeQuery());
		}
	}

	private void setLongOrNull(PreparedStatement statement, int index, Long value) throws SQLException {
		try {
			statement.setLong(index, value);
		} catch (NullPointerException e) {
			statement.setNull(index, Types.BIGINT);
		}
	}
}
