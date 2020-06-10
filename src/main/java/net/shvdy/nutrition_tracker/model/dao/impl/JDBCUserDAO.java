package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.entity.Notification;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class JDBCUserDAO implements UserDAO {

    private DataSource dataSource;
    private Extractor resultSetMapper;
    private final Properties queries;

    public JDBCUserDAO(DataSource dataSource, Extractor resultSetMapper, Properties queries) {
        this.dataSource = dataSource;
        this.resultSetMapper = resultSetMapper;
        this.queries = queries;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("user_dao.SELECT_BY_USERNAME_SQL"))) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSetMapper.extractUser(resultSet));
                }
            }
        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO findByUsernameLocalised: " + e);
            throw new SQLRuntimeException(e);
        }
        return Optional.empty();
    }

    public void create(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertUser = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_USER_SQL"));
             PreparedStatement insertUserProfile = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_USER_PROFILE_SQL"))) {

            connection.setAutoCommit(false);

            insertUser.setString(1, user.getUsername());
            insertUser.setString(2, user.getPassword());
            insertUser.setBoolean(3, true);
            insertUser.setBoolean(4, true);
            insertUser.setBoolean(5, true);
            insertUser.setBoolean(6, true);
            insertUser.setString(7, user.getRole().name());
            insertUser.executeUpdate();

            insertUserProfile.setLong(1, getUserIdByEmail(connection, user));
            insertUserProfile.setString(2, user.getUserProfile().getFirstName());
            insertUserProfile.setString(3, user.getUserProfile().getLastName());
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

            updateProfile.setString(1, userProfile.getFirstName());
            updateProfile.setString(2, userProfile.getLastName());
            updateProfile.setInt(3, userProfile.getAge());
            updateProfile.setInt(4, userProfile.getHeight());
            updateProfile.setInt(5, userProfile.getWeight());
            updateProfile.setString(6, userProfile.getLifestyle().name());
            updateProfile.setLong(7, userProfile.getProfileId());
            updateProfile.executeUpdate();

        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO updateProfile: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public List<User> findGroup(String adminUsername) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("userdao.SELECT_GROUP_SQL"))) {

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
    public Set<Notification> findNotifications(UserDTO receiver) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries
                             .getProperty("userdao.SELECT_NOTIFICATIONS_SQL"))) {

            statement.setLong(1, receiver.getUserId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetMapper.extractNotifications(resultSet, receiver);
                }
            }
        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO findNotifications: " + e);
            throw new SQLRuntimeException(e);
        }
        return new HashSet<>();
    }

    public void createGroupInvitation(Notification n) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_INVITATION_SQL"))) {

            statement.setLong(1, n.getSender().getUserId());
            statement.setString(2, n.getReceiver().getUsername());
            statement.setString(3, n.getDateTime());
            statement.setString(4, n.getMessage());
            statement.executeUpdate();

        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO createGroupInvitation: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    public void acceptGroupInvitation(Notification n) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("userdao.INSERT_USER_TO_GROUP_SQL"));
             PreparedStatement statement2 = connection
                     .prepareStatement(queries.getProperty("userdao.DELETE_NOTIFICATION_SQL"))) {
            connection.setAutoCommit(false);

            statement.setLong(1, n.getSender().getUserId());
            statement.setLong(2, n.getReceiver().getUserId());
            statement.executeUpdate();

            statement2.setLong(1, n.getSender().getUserId());
            statement2.setLong(2, n.getReceiver().getUserId());
            statement2.executeUpdate();

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO acceptGroupInvitation: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    public void declineGroupInvitation(Notification n) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("userdao.DELETE_INVITATION_SQL"))) {

            statement.setLong(1, n.getSender().getUserId());
            statement.setLong(2, n.getReceiver().getUserId());
            statement.setString(3, n.getDateTime());
            statement.executeUpdate();

        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCUserDAO declineGroupInvitation: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    private Long getUserIdByEmail(Connection connection, User user) throws SQLException {
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT id FROM user WHERE email = '" + user.getUsername() + "'");
        if (rs.next()) {
            return rs.getLong("id");
        } else
            throw new SQLException("Failed to retrieve DB-generated user ID");
    }

}
