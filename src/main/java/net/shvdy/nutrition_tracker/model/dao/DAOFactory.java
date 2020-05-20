package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.model.dao.jdbc.JDBCDAOFactory;

public abstract class DAOFactory {

    private static DAOFactory DAOFactory;

    public abstract UserDAO getUserDAO();

    public static DAOFactory getInstance() {
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
