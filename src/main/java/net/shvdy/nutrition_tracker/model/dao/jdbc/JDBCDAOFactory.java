package net.shvdy.nutrition_tracker.model.dao.jdbc;

import javax.sql.DataSource;


import net.shvdy.nutrition_tracker.model.dao.ConnectionPoolHolder;
import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCDAOFactory extends DAOFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private final Properties DAO_SQLqueries = readProperties("/DAO_SQL_queries.properties");

    @Override
    public UserDAO getUserDAO() {
        return new JDBCUserDAO(dataSource, new UserMapper(), DAO_SQLqueries);
    }

    private Properties readProperties(String fileInClasspath) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileInClasspath);

        try {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (IOException e) {
            System.err.println("Could not read properties from file " + fileInClasspath + " in classpath. " + e);
        }

        return null;
    }
}

