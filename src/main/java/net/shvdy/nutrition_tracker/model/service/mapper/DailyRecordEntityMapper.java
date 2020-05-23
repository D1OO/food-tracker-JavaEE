package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 21.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordEntityMapper {

	public DailyRecordDTO entityToDTO(DailyRecord dailyRecord, Locale locale) {

		return DailyRecordDTO.builder()
				.recordId(dailyRecord.getRecordId())
				.recordDate(dailyRecord.getRecordDate())
				.dailyCaloriesNorm(dailyRecord.getDailyCaloriesNorm())
				.userProfileId(dailyRecord.getUserProfileId())
				.entries(mapEntries(dailyRecord.getEntries()))
				.percentage(getPercentage(dailyRecord.getEntries(), dailyRecord.getDailyCaloriesNorm()))
				.totalCalories(getTotalCalories(dailyRecord.getEntries()))
				.totalCarbs(getTotalCarbs(dailyRecord.getEntries()))
				.totalFats(getTotalFats(dailyRecord.getEntries()))
				.totalProt(getTotalProteins(dailyRecord.getEntries()))
				.dateHeader(getShortDateHeader(dailyRecord.getRecordDate(), locale))
				.build();
	}

	private List<DailyRecordEntryDTO> mapEntries(List<DailyRecordEntry> entries) {
		return entries.stream().map(entry -> DailyRecordEntryDTO.builder()
				.food(entry.getFood())
				.quantity(entry.getQuantity())
				.entryCalories(entry.getFood().getCalories() * entry.getQuantity() / 100)
				.entryProt(entry.getFood().getProteins() * entry.getQuantity() / 100)
				.entryFats(entry.getFood().getFats() * entry.getQuantity() / 100)
				.entryCarbs(entry.getFood().getCarbohydrates() * entry.getQuantity() / 100)
				.build())
				.collect(Collectors.toList());
	}

	private String getShortDateHeader(String recordDate, Locale locale) {
		return (LocalDate.parse(recordDate).getDayOfMonth()) + " " +
				LocalDate.parse(recordDate).getDayOfWeek().getDisplayName(TextStyle.SHORT, locale);
	}

	private int getPercentage(List<DailyRecordEntry> entries, int dailyCaloriesNorm) {
		return entries == null ? 0 : (int) (getTotalCalories(entries) / (double) dailyCaloriesNorm * 100);
	}

	private int getTotalCalories(List<DailyRecordEntry> entries) {
		return entries == null ? -1 : entries.stream()
				.mapToInt(x -> x.getFood().getCalories() * x.getQuantity() / 100).sum();
	}

	private int getTotalFats(List<DailyRecordEntry> entries) {
		return entries == null ? 0 : entries.stream()
				.mapToInt(x -> x.getFood().getFats() * x.getQuantity() / 100).sum();
	}

	private int getTotalProteins(List<DailyRecordEntry> entries) {
		return entries == null ? 0 : entries.stream()
				.mapToInt(x -> x.getFood().getProteins() * x.getQuantity() / 100).sum();
	}

	private int getTotalCarbs(List<DailyRecordEntry> entries) {
		return entries == null ? 0 : entries.stream()
				.mapToInt(x -> x.getFood().getCarbohydrates() * x.getQuantity() / 100).sum();
	}
}
