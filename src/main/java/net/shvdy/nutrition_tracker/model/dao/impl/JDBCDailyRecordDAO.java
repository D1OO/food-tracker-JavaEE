package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCDailyRecordDAO implements DailyRecordDAO {

	private DataSource dataSource;
	private ResultSetMapper<List<DailyRecord>> resultSetMapper;
	private Properties queries;

	public JDBCDailyRecordDAO(DataSource dataSource, ResultSetMapper<List<DailyRecord>> resultSetMapper,
							  Properties queries) {
		this.dataSource = dataSource;
		this.resultSetMapper = resultSetMapper;
		this.queries = queries;
	}

	public List<DailyRecord> findFromDateByQuantity(String recordDate, int quantity, Long profileId) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.SELECT_BY_DATE_AND_QUANTITY"))) {

			statement.setLong(1, profileId);
			statement.setString(2, recordDate);
			statement.setInt(3, quantity);

			return resultSetMapper.map(statement.executeQuery());
		}
	}

	public void save(DailyRecord dailyRecord) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement insertDailyRecord = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.INSERT_DAILY_RECORD_SQL"));
			 PreparedStatement insertEntries = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.INSERT_ENTRIES_SQL"))) {

			connection.setAutoCommit(false);

			insertDailyRecord.setLong(1, dailyRecord.getRecordId());
			insertDailyRecord.setLong(2, dailyRecord.getUserProfileId());
			insertDailyRecord.setString(3, dailyRecord.getRecordDate());
			insertDailyRecord.setInt(4, dailyRecord.getDailyCaloriesNorm());

			for (DailyRecordEntry entry : dailyRecord.getEntries()) {
				insertEntries.setLong(1, entry.getFood().getFoodId());
				insertEntries.setLong(2, dailyRecord.getRecordId());
				insertEntries.setInt(3, entry.getQuantity());
				try {
					insertEntries.setLong(4, entry.getEntryId());
				} catch (NullPointerException e) {
					insertEntries.setNull(4, Types.BIGINT);
				}

				insertEntries.addBatch();
			}

			insertDailyRecord.executeUpdate();
			insertEntries.executeBatch();

			connection.commit();
		}
	}

	@Override
	public void create(DailyRecord entity) throws SQLException {

	}
}
