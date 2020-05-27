package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.dto.FoodDTO;
import net.shvdy.nutrition_tracker.model.dao.impl.JDBCDAOFactory;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public abstract class DAOFactory {

    private static DAOFactory DAOFactory;

    public abstract UserDAO getUserDAO();
    public abstract DailyRecordDAO getDailyRecordDAO();
    public abstract FoodDAO getFoodDAO();
    public abstract ArticleDAO getArticleDAO();

    public static DAOFactory getJDBCInstance() throws IOException, NamingException {
        if (DAOFactory == null) {
            synchronized (DAOFactory.class) {
                if (DAOFactory == null) {
                    DAOFactory = new JDBCDAOFactory();
                }
            }
        }
        return DAOFactory;
    }
}
