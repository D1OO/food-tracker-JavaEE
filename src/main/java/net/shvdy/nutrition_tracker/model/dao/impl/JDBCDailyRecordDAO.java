package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCDailyRecordDAO implements DailyRecordDAO {

	private DataSource dataSource;
	private ResultSetMapper<DailyRecord> resultSetMapper;
	private Properties queries;

	public JDBCDailyRecordDAO(DataSource dataSource, ResultSetMapper<DailyRecord> resultSetMapper, Properties queries) {
		this.dataSource = dataSource;
		this.resultSetMapper = resultSetMapper;
		this.queries = queries;
	}

	@Override
	public void create(DailyRecord dailyRecord) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement insertUserStatement = connection
					 .prepareStatement(queries.getProperty("userdao.INSERT_DAILY_RECORD_SQL"));
			 PreparedStatement insertUserProfileStatement = connection
					 .prepareStatement(queries.getProperty("userdao.INSERT_USER_PROFILE_SQL"))) {

//			connection.setAutoCommit(false);
//
//			insertUserStatement.setString(1, user.getUsername());
//			insertUserStatement.setString(2, user.getPassword());
//			insertUserStatement.setBoolean(3, true);
//			insertUserStatement.setBoolean(4, true);
//			insertUserStatement.setBoolean(5, true);
//			insertUserStatement.setBoolean(6, true);
//			insertUserStatement.setString(7, user.getRole().name());
//			insertUserStatement.executeUpdate();

//			insertUserProfileStatement.setLong(1, user.getId());
//			insertUserProfileStatement.setString(2, user.getUserProfile().getFirstName());
//			insertUserProfileStatement.setString(3, user.getUserProfile().getLastName());
			insertUserProfileStatement.executeUpdate();

			connection.commit();
		}
	}

	public Optional<DailyRecord> findByRecordDate(String recordDate) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.SELECT_BY_RECORDDATE_SQL"))) {

			statement.setString(1, recordDate);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(resultSetMapper.map(resultSet));
				}
			}
		}
		return Optional.empty();
	}
}
