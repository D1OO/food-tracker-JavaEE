package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

public class JDBCUserDAO implements UserDAO {

    private DataSource dataSource;
    private ResultSetMapper<User> resultSetMapper;
    private Properties queries;

    public JDBCUserDAO(DataSource dataSource, ResultSetMapper<User> resultSetMapper, Properties queries) {
        this.dataSource = dataSource;
        this.resultSetMapper = resultSetMapper;
        this.queries = queries;
    }

    public void create(User user) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertUserStatement = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_USER_SQL"));
             PreparedStatement insertUserProfileStatement = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_USER_PROFILE_SQL"))) {

            connection.setAutoCommit(false);

            prepareInsert(insertUserStatement, user);
            insertUserStatement.executeUpdate();

            insertUserProfileStatement.setLong(1, getUserIdByEmail(connection, user));
            insertUserProfileStatement.setString(2, user.getUserProfile().getFirstName());
            insertUserProfileStatement.setString(3, user.getUserProfile().getLastName());
            insertUserProfileStatement.executeUpdate();

            connection.commit();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("user_dao.SELECT_BY_USERNAME_SQL"))) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSetMapper.map(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private Long getUserIdByEmail(Connection connection, User user) throws SQLException {
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT id FROM user WHERE email = '" + user.getUsername() + "'");
        if (rs.next()) {
            return rs.getLong("id");
        } else
            throw new SQLException();
    }

    private void prepareInsert(PreparedStatement insertUserStatement, User user) throws SQLException {
        insertUserStatement.setString(1, user.getUsername());
        insertUserStatement.setString(2, user.getPassword());
        insertUserStatement.setBoolean(3, true);
        insertUserStatement.setBoolean(4, true);
        insertUserStatement.setBoolean(5, true);
        insertUserStatement.setBoolean(6, true);
        insertUserStatement.setString(7, user.getRole().name());
    }
}
