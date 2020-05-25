package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import net.shvdy.nutrition_tracker.model.entity.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleMapper implements  ResultSetMapper<List<Article>> {
	@Override
	public List<Article> map(ResultSet resultSet) throws SQLException {
		ArrayList<Article> articles = new ArrayList<>();
		while (resultSet.next()) {
			articles.add(extractArticleFromResultSet(resultSet));
		}
		return articles;
	}

	private Article extractArticleFromResultSet(ResultSet resultSet) throws SQLException {
		return Article.builder()
				.articleId(resultSet.getLong("article_id"))
				.authorId(resultSet.getLong("article_id"))
				.date(resultSet.getString("date_created"))
				.authorFirstName(resultSet.getString("first_name"))
				.authorLastName(resultSet.getString("last_name"))
				.title(resultSet.getString("title"))
				.text(resultSet.getString("text"))
				.image(resultSet.getBlob("image").getBinaryStream())
				.build();
	}

}
