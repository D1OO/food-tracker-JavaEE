package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.dao.FoodDAO;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.DailyRecordMapper;
import net.shvdy.nutrition_tracker.model.dao.mapper.FoodMapper;
import net.shvdy.nutrition_tracker.model.dao.mapper.UserMapper;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCDAOFactory extends DAOFactory {

	private final DataSource dataSource;

	private final Properties DAO_SQLqueries;

	public JDBCDAOFactory() throws NamingException, IOException {
		dataSource = ConnectionPoolHolder.getDataSource();
		DAO_SQLqueries = readProperties("/DAO_SQL_queries.properties");
	}

	@Override
	public UserDAO getUserDAO() {
		return new JDBCUserDAO(dataSource, new UserMapper(), DAO_SQLqueries);
	}

	@Override
	public DailyRecordDAO getDailyRecordDAO() {
		return new JDBCDailyRecordDAO(dataSource, new DailyRecordMapper(), DAO_SQLqueries);
	}

	@Override
	public FoodDAO getFoodDAO() {
		return new JDBCFoodDAO(dataSource, new FoodMapper(), DAO_SQLqueries);
	}

	private Properties readProperties(String fileInClasspath) throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileInClasspath);
		Properties properties = new Properties();

		try {
			properties.load(is);
		} catch (IOException e) {
			System.err.println("Could not read properties from file " + fileInClasspath + " in classpath. " + e);
			throw e;
		}

		return properties;
	}
}

