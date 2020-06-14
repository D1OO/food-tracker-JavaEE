package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.FoodDAO;
import net.shvdy.nutrition_tracker.model.entity.Food;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCFoodDAO implements FoodDAO {

    private static final Logger log = LogManager.getLogger(JDBCFoodDAO.class);
    private final Properties queries;
    private DataSource dataSource;


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
                     .prepareStatement(queries.getProperty("food_dao.INSERT_FOOD_FOR_PROFILE_SQL"))) {

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
            log.error("JDBCFoodDAO createForProfile: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public List<Food> findByNameStart(String nameStartsWith) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement findFood = connection
                     .prepareStatement(queries.getProperty("food_dao.SELECT_FOOD_SQL"))) {

            findFood.setString(1, "%" + nameStartsWith.toLowerCase() + "%");

            try (ResultSet resultSet = findFood.executeQuery()) {
                if (resultSet.next()) {
                    return extractFood(resultSet);
                } else
                    return new ArrayList<>();
            }

        } catch (SQLException e) {
            log.error("JDBCFoodDAO createForProfile: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    private List<Food> extractFood(ResultSet rs) throws SQLException {
        List<Food> userFood = new ArrayList<>();
        do {
            userFood.add(Food.builder().food_id(rs.getLong("food_id")).name(rs.getString("name"))
                    .calories(rs.getInt("calories")).fats(rs.getInt("fats")).proteins(rs.getInt("proteins"))
                    .carbohydrates(rs.getInt("carbohydrates")).build());
        } while (rs.next());
        return userFood;
    }
}
