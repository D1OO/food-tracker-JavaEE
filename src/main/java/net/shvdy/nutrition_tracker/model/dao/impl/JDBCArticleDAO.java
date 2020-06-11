package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.ArticleDAO;
import net.shvdy.nutrition_tracker.model.entity.Article;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 2.0
 */
public class JDBCArticleDAO implements ArticleDAO {
    private static final BasicRowProcessor ROW_PROCESSOR = new BasicRowProcessor(new BeanProcessor());
    private static final ResultSetHandler<Article> RESULT_SET_HANDLER =
            new BeanHandler<>(Article.class, ROW_PROCESSOR);
    private static final ResultSetHandler<List<Article>> LIST_RESULT_SET_HANDLER =
            new BeanListHandler<>(Article.class, ROW_PROCESSOR);

    private static final Logger log = LogManager.getLogger(JDBCArticleDAO.class);

    private final Properties queries;
    private static QueryRunner queryRunner;

    public JDBCArticleDAO(DataSource dataSource, Properties queries) {
        this.queries = queries;
        queryRunner = new QueryRunner(dataSource);
    }

    @Override
    public int save(Article article) {
        int id = 0;
        try {
            id = queryRunner.insert(queries.getProperty("article_dao.INSERT_ARTICLE_SQL"), new ScalarHandler<BigInteger>(),
                    article.getArticleId(), article.getTitleEN(), article.getTitleRU(), article.getAuthorId(),
                    article.getDate(), article.getTextEN(), article.getTextRU(), article.getImageStream()).intValue();
            article.getImageStream().close();
        } catch (SQLException e) {
            log.error("JDBCArticleDAO save: " + e);
            throw new SQLRuntimeException(e);
        } catch (IOException e) {
            log.warn("JDBCArticleDAO save: Couldn't close inputstream " + e);
        }
        return id;
    }

    @Override
    public List<Article> findPaginatedLocalised(Locale locale) {
        try {
            return queryRunner.query(prepareQueryLocaleParam(queries
                            .getProperty("article_dao.SELECT_BY_DATE_AND_QUANTITY"), locale),
                    LIST_RESULT_SET_HANDLER);
        } catch (SQLException e) {
            log.error("JDBCArticleDAO findPaginatedLocalised: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public Optional<Article> findByIDLocalised(int articleId, Locale locale) {
        try {
            return Optional.ofNullable(queryRunner.query(
                    prepareQueryLocaleParam(queries.getProperty("article_dao.SELECT_BY_ID"), locale),
                    RESULT_SET_HANDLER, articleId));
        } catch (SQLException e) {
            log.error("JDBCArticleDAO findByIDLocalised: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public List<Article> findRandomLocalised(int quantity, Locale locale) {
        try {
            return queryRunner.query(prepareQueryLocaleParam(queries
                            .getProperty("article_dao.SELECT_RANDOM"), locale),
                    LIST_RESULT_SET_HANDLER, quantity);
        } catch (SQLException e) {
            log.error("JDBCArticleDAO findRandomLocalised: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    private String prepareQueryLocaleParam(String statement, Locale locale) {
        return statement
                .replace("title_?", "title_" + locale.getLanguage())
                .replace("text_?", "text_" + locale.getLanguage());
    }

    private static class BeanProcessor extends GenerousBeanProcessor {

        @Override
        public <T> T toBean(ResultSet rs, Class<? extends T> type) throws SQLException {
            Article a = (Article) super.toBean(rs, type);
            a.setImageStream((rs.getBlob("imageBytes").getBinaryStream()));
            return (T) a;
        }

        @Override
        public <T> List<T> toBeanList(ResultSet rs, Class<? extends T> type) throws SQLException {
            List<Article> results = new ArrayList<>();
            if (!rs.next()) {
                return new ArrayList<>();
            } else {
                do {
                    results.add((Article) toBean(rs, type));
                } while (rs.next());
                return (List<T>) results;
            }
        }
    }
}
