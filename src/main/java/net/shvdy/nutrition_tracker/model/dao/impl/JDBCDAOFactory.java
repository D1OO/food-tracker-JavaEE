package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.model.dao.*;

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
        return new JDBCUserDAO(dataSource, new Extractor(), QUERIES);
    }

    @Override
    public DailyRecordDAO getDailyRecordDAO() {
        return new JDBCDailyRecordDAO(dataSource, QUERIES);
    }

    @Override
    public FoodDAO getFoodDAO() {
        return new JDBCFoodDAO(dataSource, QUERIES);
    }

    @Override
    public ArticleDAO getArticleDAO() {
        return new JDBCArticleDAO(dataSource, QUERIES);
    }

}

