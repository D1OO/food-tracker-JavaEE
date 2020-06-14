package net.shvdy.nutrition_tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.model.dao.FoodDAO;
import net.shvdy.nutrition_tracker.model.dao.impl.JDBCFoodDAO;
import net.shvdy.nutrition_tracker.model.entity.Food;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * 31.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class JDBCFoodDAOTest {

    @Mock
    private DataSource ds;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;
    private Food food;

    @BeforeAll
    static void init() {
        PropertiesContainer.readProperties();
        ContextHolder.injectObjectMapper(new ObjectMapper());
    }

    @BeforeEach
    void setUp() throws Exception {
        assertNotNull(ds);
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        food = Food.builder().food_id(1L).name("Mockito").calories(22).proteins(3).fats(33).carbohydrates(11).build();
        when(rs.getLong("food_id")).thenReturn(food.getFoodId());
        when(rs.getString("name")).thenReturn(food.getName());
        lenient().when(rs.getInt("calories")).thenReturn(food.getCalories());
        lenient().when(rs.getInt("fats")).thenReturn(food.getFats());
        lenient().when(rs.getInt("proteins")).thenReturn(food.getProteins());
        lenient().when(rs.getInt("carbohydrates")).thenReturn(food.getCarbohydrates());
    }

    @Test
    public void findFood() throws Exception {
        FoodDAO dao = new JDBCFoodDAO(ds, PropertiesContainer.DotProperties.DAO_SQL_QUERIES.getProp());

        List<Food> foodList = dao.findByNameStart("Mockito");

        assertEquals(ContextHolder.objectMapper().writeValueAsString(foodList.get(0)),
                ContextHolder.objectMapper().writeValueAsString(food));
    }
}

