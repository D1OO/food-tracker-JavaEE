package net.shvdy.food_tracker.model.dao.jdbc;

import javax.sql.DataSource;


import net.shvdy.food_tracker.model.dao.ConnectionPoolHolder;
import net.shvdy.food_tracker.model.dao.DAOFactory;
import net.shvdy.food_tracker.model.dao.UserDAO;
import net.shvdy.food_tracker.model.dao.mapper.UserMapper;

public class JDBCDAOFactory extends DAOFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDAO getUserDAO() {
        return new JDBCUserDAO(dataSource, new UserMapper());
    }
}

