package net.shvdy.nutrition_tracker.model.dao.resultset_mapper;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordListMapper implements ResultSetMapper<List<DailyRecord>> {

    @Override
    public List<DailyRecord> map(ResultSet resultSet) throws SQLException {
        ArrayList<DailyRecordEntry> entries = new ArrayList<>();
        HashMap<Long, DailyRecord> paginatedRecords = new HashMap<>();

        while (resultSet.next()) {
            paginatedRecords.putIfAbsent(resultSet.getLong("record_id"), extractRecordFromResultSet(resultSet));
            entries.add(extractEntryFromResultSet(resultSet));
        }

        entries.forEach(entry -> paginatedRecords.get(entry.getRecordId())
                .getEntries().add(entry));

        return new ArrayList<>(paginatedRecords.values());
    }

    private DailyRecordEntry extractEntryFromResultSet(ResultSet resultSet) throws SQLException {
        return Builder.buildDailyRecordEntry(resultSet);
    }

    private DailyRecord extractRecordFromResultSet(ResultSet resultSet) throws SQLException {
        return Builder.buildDailyRecord(resultSet);
    }

}

