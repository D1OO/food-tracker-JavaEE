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

	public List<DailyRecordDTO> findPaginated(Long profileId, String date, int quantity,
											  Locale currentLocale) throws SQLException {
		return insertAbsentDays(date, quantity, profileId, currentLocale,
				dailyRecordDAO.findFromDateByQuantity(date, quantity, profileId).stream()
						.map(x -> dailyRecordMapper.recordEntityToDTO(x, currentLocale))
						.collect(Collectors.toMap(DailyRecordDTO::getRecordDate, Function.identity())));
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

	private List<DailyRecordDTO> insertAbsentDays(String day, int size, Long profileId, Locale locale,
												  Map<String, DailyRecordDTO> weeklyRecords) {
		IntStream.range(0, size).mapToObj(n -> LocalDate.parse(day).minusDays(n).toString())
				.forEach(date -> weeklyRecords.putIfAbsent(date, DailyRecordDTO.builder()
						.recordDate(date)
						.userProfileId(profileId)
						.dateHeader(dailyRecordMapper.getShortDateHeader(date, locale))
						.entries(new ArrayList<>())
						.build()));

		return new ArrayList<>(weeklyRecords.values()).stream()
				.sorted(Comparator.comparing(DailyRecordDTO::getRecordDate).reversed())
				.limit(size)
				.collect(Collectors.toList());
	}
}
