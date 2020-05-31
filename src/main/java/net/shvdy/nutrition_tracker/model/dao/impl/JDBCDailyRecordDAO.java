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
	private final Properties queries;

	public JDBCDailyRecordDAO(DataSource dataSource, ResultSetMapper<List<DailyRecord>> resultSetMapper,
							  Properties queries) {
		this.dataSource = dataSource;
		this.resultSetMapper = resultSetMapper;
		this.queries = queries;
	}

	public List<DailyRecord> findByDatePeriodAndQuantity(Long profileId, String periodStartDate, String periodEndDate)
			throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.SELECT_BY_DATE_PERIOD_AND_QUANTITY"))) {

			statement.setLong(1, profileId);
			statement.setString(2, periodStartDate);
			statement.setString(3, periodEndDate);

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

			Optional<Long> dailyRecordID = Optional.ofNullable(dailyRecord.getRecordId());
			setLongOrNull(insertDailyRecord, 1, dailyRecordID);
			insertDailyRecord.setLong(2, dailyRecord.getUserProfileId());
			insertDailyRecord.setString(3, dailyRecord.getRecordDate());
			insertDailyRecord.setInt(4, dailyRecord.getDailyCaloriesNorm());
			insertDailyRecord.executeUpdate();

			if (dailyRecordID.isEmpty()) {
				ResultSet generatedKeys = insertDailyRecord.getGeneratedKeys();
				if (generatedKeys.next())
					dailyRecord.setRecordId(generatedKeys.getLong(1));
			}

			for (DailyRecordEntry entry : dailyRecord.getEntries()) {
				insertEntries.setLong(1, entry.getFood().getFoodId());
				insertEntries.setLong(2, Optional.ofNullable(dailyRecord.getRecordId()).orElseThrow());
				insertEntries.setInt(3, entry.getQuantity());
				setLongOrNull(insertEntries, 4, Optional.ofNullable(entry.getEntryId()));

				insertEntries.addBatch();
			}

			insertEntries.executeBatch();
			connection.commit();
		}
	}

	private void setLongOrNull(PreparedStatement statement, int index, Optional<Long> value) throws SQLException {
		if (value.isPresent()) {
			statement.setLong(index, value.get()); //setLong doesn't accept null values
		} else {
			statement.setNull(index, Types.BIGINT);
		}
	}

}
