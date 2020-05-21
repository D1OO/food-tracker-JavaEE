package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.dao.impl.JDBCDAOFactory;

import javax.naming.NamingException;
import java.io.IOException;

public abstract class DAOFactory {

    private static DAOFactory DAOFactory;

    public abstract UserDAO getUserDAO();
    public abstract DailyRecordDAO getDailyRecordDAO();

    public static DAOFactory getInstance() throws IOException, NamingException {
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
