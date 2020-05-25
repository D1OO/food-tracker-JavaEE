package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.dao.resultset_mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;
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

	public void save(DailyRecord dailyRecord) throws SQLException, NoSuchElementException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement insertDailyRecord = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.INSERT_DAILY_RECORD_SQL"),
							 Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement insertEntries = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.INSERT_ENTRIES_SQL"))) {

			connection.setAutoCommit(false);

			setLongOrNull(insertDailyRecord, 1, dailyRecord.getRecordId());
			insertDailyRecord.setLong(2, dailyRecord.getUserProfileId());
			insertDailyRecord.setString(3, dailyRecord.getRecordDate());
			insertDailyRecord.setInt(4, dailyRecord.getDailyCaloriesNorm());
			insertDailyRecord.executeUpdate();

			if (dailyRecord.getRecordId() == null) {
				ResultSet generatedKeys = insertDailyRecord.getGeneratedKeys();
				if (generatedKeys.next())
					dailyRecord.setRecordId(generatedKeys.getLong(1));
			}

			for (DailyRecordEntry entry : dailyRecord.getEntries()) {
				insertEntries.setLong(1, entry.getFood().getFoodId());
				insertEntries.setLong(2, Optional.ofNullable(dailyRecord.getRecordId()).orElseThrow());
				insertEntries.setInt(3, entry.getQuantity());
				setLongOrNull(insertEntries, 4, entry.getEntryId());

				insertEntries.addBatch();
			}

			insertEntries.executeBatch();

			connection.commit();
		}
	}

	private void setLongOrNull(PreparedStatement statement, int index, Long value) throws SQLException {
		try {
			statement.setLong(index, value);
		} catch (NullPointerException e) {
			statement.setNull(index, Types.BIGINT);
		}
	}

	@Override
	public void create(DailyRecord entity) throws SQLException {

	}
}
