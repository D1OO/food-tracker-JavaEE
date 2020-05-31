package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;
import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.service.mapper.DailyRecordEntityMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordService {

    private final DailyRecordDAO dailyRecordDAO;
    private final DailyRecordEntityMapper dailyRecordMapper;

    public DailyRecordService(DailyRecordDAO dailyRecordDAO, DailyRecordEntityMapper dailyRecordMapper) {
        this.dailyRecordDAO = dailyRecordDAO;
        this.dailyRecordMapper = dailyRecordMapper;
    }

    public void saveNewEntries(NewEntriesDTO newEntriesDTO) throws SQLException, RuntimeException {
        dailyRecordDAO.save(DailyRecord.builder()
                .recordId(newEntriesDTO.getRecordId())
                .recordDate(newEntriesDTO.getRecordDate())
                .userProfileId(newEntriesDTO.getProfileId())
                .dailyCaloriesNorm(newEntriesDTO.getCurrentDailyCaloriesNorm())
                .entries(dailyRecordMapper.entriesListDTOToEntity(newEntriesDTO.getEntries()))
                .build());
    }

    public List<DailyRecordDTO> findPaginated(Long profileId, String periodEndDate, int quantity,
                                              Locale currentLocale) throws SQLException {
        return insertAbsentDays(profileId, periodEndDate, quantity, currentLocale,
                dailyRecordDAO.findByDatePeriodAndQuantity(profileId,
                        LocalDate.parse(periodEndDate).minusDays(quantity - 1).toString(),
                        periodEndDate).stream()
                        .map(entity -> dailyRecordMapper.recordEntityToDTO(entity, currentLocale))
                        .collect(Collectors.toMap(DailyRecordDTO::getRecordDate, Function.identity())));
    }


    private List<DailyRecordDTO> insertAbsentDays(Long profileId, String periodEndDate, int size, Locale locale,
                                                  Map<String, DailyRecordDTO> weeklyRecords) {
        IntStream.range(0, size)
                .mapToObj(n -> LocalDate.parse(periodEndDate).minusDays(n).toString())
                .forEach(day -> weeklyRecords.putIfAbsent(day, createEmptyRecord(profileId, day, locale)));
        return new ArrayList<>(weeklyRecords.values()).stream()
                .sorted(Comparator.comparing(DailyRecordDTO::getRecordDate).reversed())
                .collect(Collectors.toList());
    }

    private DailyRecordDTO createEmptyRecord(Long profileId, String date, Locale locale) {
        return DailyRecordDTO.builder()
                .recordDate(date)
                .userProfileId(profileId)
                .dateHeader(dailyRecordMapper.getShortDateHeader(date, locale))
                .entries(new ArrayList<>())
                .build();
    }

}
