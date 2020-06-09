package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.exception.SQLRuntimeException;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class JDBCUserDAO implements UserDAO {

    private DataSource dataSource;
    private UserMapper resultSetMapper;
    private final Properties queries;

    public JDBCUserDAO(DataSource dataSource, UserMapper resultSetMapper, Properties queries) {
        this.dataSource = dataSource;
        this.resultSetMapper = resultSetMapper;
        this.queries = queries;
    }

    public void create(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertUserStatement = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_USER_SQL"));
             PreparedStatement insertUserProfile = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_USER_PROFILE_SQL"))) {

            connection.setAutoCommit(false);

            prepareInsert(insertUserStatement, user);
            insertUserStatement.executeUpdate();

            insertUserProfile.setLong(1, getUserIdByEmail(connection, user));
            insertUserProfile.setString(2, user.getUserProfile().getFirstNameEN());
            insertUserProfile.setString(3, user.getUserProfile().getFirstNameRU());
            insertUserProfile.setString(4, user.getUserProfile().getLastName());
            insertUserProfile.executeUpdate();

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO create: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public void updateProfile(UserProfile userProfile) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateProfile = connection
                     .prepareStatement(queries.getProperty("userdao.UPDATE_USER_PROFILE_SQL"))) {

            updateProfile.setString(1, userProfile.getFirstNameEN());
            updateProfile.setString(2, userProfile.getFirstNameRU());
            updateProfile.setString(3, userProfile.getLastName());
            updateProfile.setInt(4, userProfile.getAge());
            updateProfile.setInt(5, userProfile.getHeight());
            updateProfile.setInt(6, userProfile.getWeight());
            updateProfile.setString(7, userProfile.getLifestyle().name());
            updateProfile.setLong(8, userProfile.getProfileId());
            updateProfile.executeUpdate();

        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO updateProfile: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public List<User> findGroup(String adminUsername, Locale locale) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(prepareQueryLocaleParam(queries
                             .getProperty("userdao.SELECT_GROUP_SQL"), locale))) {

            statement.setString(1, adminUsername);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetMapper.extractGroup(resultSet);
                }
            }
        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO findGroup: " + e);
            throw new SQLRuntimeException(e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<User> findByUsernameLocalised(String username, Locale locale) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("user_dao.SELECT_BY_USERNAME_SQL"))) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSetMapper.extractUser(resultSet, locale));
                }
            }
        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO findByUsernameLocalised: " + e);
            throw new SQLRuntimeException(e);
        }
        return Optional.empty();
    }

    private Long getUserIdByEmail(Connection connection, User user) throws SQLException {
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT id FROM user WHERE email = '" + user.getUsername() + "'");
        if (rs.next()) {
            return rs.getLong("id");
        } else
            throw new SQLException("Failed to retrieve DB-generated user ID");
    }

    private String prepareQueryLocaleParam(String statement, Locale locale) {
        return statement.replace("first_name_?", "first_name_" + locale.getLanguage());
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
