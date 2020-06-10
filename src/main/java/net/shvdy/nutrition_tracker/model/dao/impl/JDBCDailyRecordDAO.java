package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Food;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCDailyRecordDAO implements DailyRecordDAO {
    private static final BasicRowProcessor ROW_PROCESSOR = new BasicRowProcessor(new DailyRecordBeanProcessor());
    private static final ResultSetHandler<DailyRecord> RESULT_SET_HANDLER =
            new BeanHandler<>(DailyRecord.class, ROW_PROCESSOR);
    private static final ResultSetHandler<List<DailyRecord>> LIST_RESULT_SET_HANDLER =
            new BeanListHandler<>(DailyRecord.class, ROW_PROCESSOR);

    private Properties queries;
    private QueryRunner queryRunner;
    private DataSource dataSource;

    public JDBCDailyRecordDAO(DataSource dataSource, Properties queries) {
        this.queries = queries;
        this.dataSource = dataSource;
        queryRunner = new QueryRunner(dataSource);
    }

    public List<DailyRecord> findByDatePeriodAndQuantity(Long profileId, String periodStartDate, String periodEndDate) {
        try {
            return queryRunner.query(queries.getProperty("daily_record_dao.SELECT_BY_DATE_PERIOD_AND_QUANTITY"),
                    LIST_RESULT_SET_HANDLER,
                    profileId, periodStartDate, periodEndDate);
        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCDailyRecordDAO findByDatePeriodAndQuantity: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    public void save(DailyRecord dailyRecord) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            Optional.ofNullable(queryRunner.insert(queries.getProperty("daily_record_dao.INSERT_DAILY_RECORD_SQL"),
                    new ScalarHandler<BigInteger>(),
                    dailyRecord.getRecordId(), dailyRecord.getProfileId(), dailyRecord.getRecordDate(),
                    dailyRecord.getDailyCaloriesNorm())).ifPresent(x -> dailyRecord.setRecordId(x.longValue()));

            queryRunner.insertBatch(connection, queries.getProperty("daily_record_dao.INSERT_ENTRIES_SQL"),
                    RESULT_SET_HANDLER,
                    dailyRecord.getEntries().stream()
                            .map(u -> new Object[]{u.getFood().getFoodId(), dailyRecord.getRecordId(),
                                    u.getQuantity(), u.getEntryId()})
                            .toArray(Object[][]::new));

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            ContextHolder.logger().error("JDBCDailyRecordDAO save: " + e);
            throw new SQLRuntimeException(e);
        }
    }

    private static class DailyRecordBeanProcessor extends GenerousBeanProcessor {

        @Override
        public <T> List<T> toBeanList(ResultSet rs, Class<? extends T> type) throws SQLException {
            HashMap<Long, DailyRecord> results = new HashMap<>();
            if (!rs.next()) {
                return new ArrayList<>();
            } else {
                do {
                    Long recordId = rs.getLong("record_id");
                    results.putIfAbsent(recordId, (DailyRecord) toBean(rs, type));
                    results.get(recordId).getEntries().add(extractDailyRecordEntry(rs));
                } while (rs.next());
                return (List<T>) new ArrayList<>(results.values());
            }
        }

        private DailyRecordEntry extractDailyRecordEntry(ResultSet rs) throws SQLException {
            return DailyRecordEntry.builder().entryId(rs.getLong("entry_id")).recordId(rs.getLong("record_id"))
                    .quantity(rs.getInt("quantity")).food(extractFood(rs)).build();
        }

        private Food extractFood(ResultSet rs) throws SQLException {
            return Food.builder().food_id(rs.getLong("food_id")).name(rs.getString("name"))
                    .calories(rs.getInt("calories")).fats(rs.getInt("fats")).proteins(rs.getInt("proteins"))
                    .carbohydrates(rs.getInt("carbohydrates")).build();
        }

    }

}
