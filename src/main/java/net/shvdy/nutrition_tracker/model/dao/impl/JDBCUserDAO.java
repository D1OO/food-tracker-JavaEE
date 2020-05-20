package net.shvdy.nutrition_tracker.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;
import java.util.Properties;

import javax.sql.DataSource;

import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.User;

public class JDBCUserDAO implements UserDAO {

	private DataSource dataSource;
	private ResultSetMapper<User> resultSetMapper;
	private Properties queries;

	public JDBCUserDAO(DataSource dataSource, ResultSetMapper<User> resultSetMapper, Properties queries) {
		this.dataSource = dataSource;
		this.resultSetMapper = resultSetMapper;
		this.queries = queries;
	}

	@Override
	public void create(User user) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement insertUserStatement = connection
					 .prepareStatement(queries.getProperty("userdao.INSERT_USER_SQL"));
			 PreparedStatement insertUserProfileStatement = connection
					 .prepareStatement(queries.getProperty("userdao.INSERT_USER_PROFILE_SQL"))) {

			connection.setAutoCommit(false);

			insertUserStatement.setString(1, user.getUsername());
			insertUserStatement.setString(2, user.getPassword());
			insertUserStatement.setBoolean(3, true);
			insertUserStatement.setBoolean(4, true);
			insertUserStatement.setBoolean(5, true);
			insertUserStatement.setBoolean(6, true);
			insertUserStatement.setString(7, user.getRole().name());
			insertUserStatement.executeUpdate();

			insertUserProfileStatement.setLong(1, user.getId());
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
					 .prepareStatement(queries.getProperty("userdao.SELECT_BY_USERNAME_SQL"))) {

			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(resultSetMapper.map(resultSet));
				}
			}
		}
		return Optional.empty();
	}
}
