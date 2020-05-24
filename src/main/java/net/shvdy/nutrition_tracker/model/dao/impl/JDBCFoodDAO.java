package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.FoodDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCFoodDAO implements FoodDAO {

	private DataSource dataSource;
	private ResultSetMapper<Food> resultSetMapper;
	private Properties queries;

	public JDBCFoodDAO(DataSource dataSource, ResultSetMapper<Food> resultSetMapper, Properties queries) {
		this.dataSource = dataSource;
		this.resultSetMapper = resultSetMapper;
		this.queries = queries;
	}

	@Override
	public Long createForProfile(Food food, Long profileId) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement insertFoodStatement = connection
					 .prepareStatement(queries.getProperty("fooddao.INSERT_FOOD_SQL"),
							 Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement insertFoodForProfileStatement = connection
					 .prepareStatement(queries.getProperty("fooddao.INSERT_FOOD_FOR_PROFILE_SQL"))) {

			connection.setAutoCommit(false);

			insertFoodStatement.setString(1, food.getName());
			insertFoodStatement.setInt(2, food.getCalories());
			insertFoodStatement.setInt(3, food.getProteins());
			insertFoodStatement.setInt(4, food.getFats());
			insertFoodStatement.setInt(5, food.getCarbohydrates());
			insertFoodStatement.executeUpdate();

			long generatedFoodId;
			try (ResultSet generatedKeys = insertFoodStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					generatedFoodId = generatedKeys.getLong(1);
				}
				else {
					throw new SQLException("Creating food failed, no ID obtained.");
				}
			}

			insertFoodForProfileStatement.setLong(1, profileId);
			insertFoodForProfileStatement.setLong(2, generatedFoodId);
			insertFoodForProfileStatement.executeUpdate();

			connection.commit();
			return generatedFoodId;
		}

	}

	@Override
	public void create(Food entity) throws SQLException {

	}
}
