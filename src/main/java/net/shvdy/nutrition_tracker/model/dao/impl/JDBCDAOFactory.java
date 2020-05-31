package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.model.dao.*;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.ArticleMapper;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.DailyRecordListMapper;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.UserMapper;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

public class JDBCDAOFactory extends DAOFactory {

    private final DataSource dataSource;
    public static final Properties QUERIES = PropertiesContainer.DotProperties.DAO_SQL_QUERIES.getProp();

    public JDBCDAOFactory() throws NamingException {
        dataSource = ConnectionPoolHolder.getDataSource();
    }

    @Override
    public UserDAO getUserDAO() {
        return new JDBCUserDAO(dataSource, new UserMapper(), QUERIES);
    }

    @Override
    public DailyRecordDAO getDailyRecordDAO() {
        return new JDBCDailyRecordDAO(dataSource, new DailyRecordListMapper(), QUERIES);
    }

    @Override
    public FoodDAO getFoodDAO() {
        return new JDBCFoodDAO(dataSource, QUERIES);
    }

    @Override
    public ArticleDAO getArticleDAO() {
        return new JDBCArticleDAO(dataSource, new ArticleMapper(), QUERIES);
    }

}

