package net.shvdy.nutrition_tracker.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;
import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.service.mapper.DailyRecordEntityMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

	public DailyRecordDTO findByDate(String date, Locale locale) throws SQLException {
		return dailyRecordMapper.entityToDTO(
				dailyRecordDAO.findByRecordDate(date)
						.orElse(new DailyRecord()),
				locale);

//		return userDao.findByUsername(username)
//				.orElseThrow(() -> new UserNotFoundException(String.format("Username '%s' not found", username)));
	}

	public void saveNewEntries(NewEntriesDTO newEntriesDTO) throws IOException, SQLException {
		DailyRecord newDailyRecord;

		newDailyRecord = DailyRecord.builder()
				.recordId(newEntriesDTO.getRecordId())
				.recordDate(newEntriesDTO.getRecordDate())
				.userProfileId(newEntriesDTO.getProfileId())
				.entries(mapNewEntriesDTO(newEntriesDTO.getEntries()))
				.build();

		dailyRecordDAO.save(newDailyRecord);
	}

	private List<DailyRecordEntry> mapNewEntriesDTO(List<DailyRecordEntryDTO> newEntriesDTO) throws IOException {
		List<DailyRecordEntry> dailyRecordEntryList = new ArrayList<>();
		ObjectMapper JsonMapper = new ObjectMapper();

		for (DailyRecordEntryDTO dailyRecordEntryDTO : newEntriesDTO) {
			System.out.println(dailyRecordEntryDTO.getQuantity());
			dailyRecordEntryList.add(DailyRecordEntry.builder()
					.quantity(dailyRecordEntryDTO.getQuantity())
					.food(JsonMapper.readValue(dailyRecordEntryDTO.getFoodDTOJSON(), Food.class)).build());
		}

		return dailyRecordEntryList;
	}

}
