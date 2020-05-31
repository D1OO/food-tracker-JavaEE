package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.ResultSetMapperLocalised;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

public class JDBCUserDAO implements UserDAO {

    private DataSource dataSource;
    private ResultSetMapperLocalised<User> resultSetMapper;
    private final Properties queries;

    public JDBCUserDAO(DataSource dataSource, ResultSetMapperLocalised<User> resultSetMapper, Properties queries) {
        this.dataSource = dataSource;
        this.resultSetMapper = resultSetMapper;
        this.queries = queries;
    }

    public void create(User user) throws SQLException {
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

            connection.commit();
        }
    }

    @Override
    public void updateProfile(UserProfile userProfile) throws SQLException {
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
        }
    }

    @Override
    public Optional<User> findByUsernameLocalised(String username, Locale locale) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(queries.getProperty("user_dao.SELECT_BY_USERNAME_SQL"))) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSetMapper.mapLocalised(resultSet, locale));
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
