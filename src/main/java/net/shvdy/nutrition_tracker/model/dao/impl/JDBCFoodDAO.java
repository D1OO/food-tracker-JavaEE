package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.exception.SQLRuntimeException;
import net.shvdy.nutrition_tracker.model.dao.FoodDAO;
import net.shvdy.nutrition_tracker.model.entity.Food;

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
    private final Properties queries;

    public JDBCFoodDAO(DataSource dataSource, Properties queries) {
        this.dataSource = dataSource;
        this.queries = queries;
    }

    @Override
    public Long createForProfile(Food food, Long profileId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertFoodStatement = connection
                     .prepareStatement(queries.getProperty("food_dao.INSERT_FOOD_SQL"),
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
                } else {
                    throw new SQLException("Failed to retrieve DB-generated ID value");
                }
            }

            insertFoodForProfileStatement.setLong(1, profileId);
            insertFoodForProfileStatement.setLong(2, generatedFoodId);
            insertFoodForProfileStatement.executeUpdate();

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            return generatedFoodId;

        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCFoodDAO createForProfile: " + e);
            throw new SQLRuntimeException(e);
        }
    }
}
