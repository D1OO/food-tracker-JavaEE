package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.model.dao.*;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.ArticleMapper;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.DailyRecordListMapper;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.FoodMapper;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.UserMapper;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class JDBCDAOFactory extends DAOFactory {

	private final DataSource dataSource;
	private final Properties DAO_SQL_queries;

	public JDBCDAOFactory() throws NamingException {
		dataSource = ConnectionPoolHolder.getDataSource();
		DAO_SQL_queries = PropertiesContainer.Props.DAO_SQL_QUERIES.getProp();
	}

	@Override
	public UserDAO getUserDAO() {
		return new JDBCUserDAO(dataSource, new UserMapper(), DAO_SQL_queries);
	}

	@Override
	public DailyRecordDAO getDailyRecordDAO() {
		return new JDBCDailyRecordDAO(dataSource, new DailyRecordListMapper(), DAO_SQL_queries);
	}

	@Override
	public FoodDAO getFoodDAO() {
		return new JDBCFoodDAO(dataSource, new FoodMapper(), DAO_SQL_queries);
	}

	@Override
	public ArticleDAO getArticleDAO() {
		return new JDBCArticleDAO(dataSource, new ArticleMapper(), DAO_SQL_queries);
	}

}

