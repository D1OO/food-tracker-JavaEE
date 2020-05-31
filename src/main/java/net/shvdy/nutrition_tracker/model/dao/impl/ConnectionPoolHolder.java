package net.shvdy.nutrition_tracker.model.dao.impl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolHolder {

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() throws NamingException {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    InitialContext context = new InitialContext();
                    dataSource = (DataSource) context.lookup("java:comp/env/jdbc/nutrition_tracker");
                }
            }
        }
        return dataSource;
    }

}
