package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Food;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 21.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordEntityMapper {

    public List<DailyRecordEntry> entriesListDTOToEntity(List<DailyRecordEntryDTO> newEntriesDTO) throws RuntimeException {
        return newEntriesDTO.stream()
                .map(x -> DailyRecordEntry.builder()
                        .quantity(x.getQuantity())
                        .food(readFromJSONString(x.getFoodDTOJSON(), Food.class)).build())
                .collect(Collectors.toList());
    }

    public DailyRecordDTO recordEntityToDTO(DailyRecord dailyRecord, Locale locale) {
        return DailyRecordDTO.builder()
                .recordId(dailyRecord.getRecordId())
                .recordDate(dailyRecord.getRecordDate())
                .dailyCaloriesNorm(dailyRecord.getDailyCaloriesNorm())
                .userProfileId(dailyRecord.getUserProfileId())
                .entries(entriesListEntityToDTO(dailyRecord.getEntries()))
                .percentage(getPercentage(dailyRecord.getEntries(), dailyRecord.getDailyCaloriesNorm()))
                .totalCalories(getTotalCalories(dailyRecord.getEntries()))
                .totalCarbs(getTotalCarbs(dailyRecord.getEntries()))
                .totalFats(getTotalFats(dailyRecord.getEntries()))
                .totalProt(getTotalProteins(dailyRecord.getEntries()))
                .dateHeader(getShortDateHeader(dailyRecord.getRecordDate(), locale))
                .build();
    }

    private <T> T readFromJSONString(String jsonString, Class<T> type) {
        try {
            return ContextHolder.getObjectMapper().readValue(jsonString, type);
        } catch (IOException e) {
            ContextHolder.getLogger().error(e.getMessage());
            throw new RuntimeException();
        }
    }

    private List<DailyRecordEntryDTO> entriesListEntityToDTO(List<DailyRecordEntry> entries) {
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

    public String getShortDateHeader(String recordDate, Locale locale) {
        return new StringBuilder()
                .append(LocalDate.parse(recordDate).getDayOfMonth())
                .append(" ")
                .append(LocalDate.parse(recordDate).getDayOfWeek().getDisplayName(TextStyle.SHORT, locale)).toString();
    }

    private int getPercentage(List<DailyRecordEntry> entries, int dailyCaloriesNorm) {
        return Optional.ofNullable(entries).isEmpty() ?
                0 : (int) (getTotalCalories(entries) / (double) dailyCaloriesNorm * 100);
    }

    private int getTotalCalories(List<DailyRecordEntry> entries) {
        return Optional.ofNullable(entries).isEmpty() ?
                0 : entries.stream().mapToInt(x -> x.getFood().getCalories() * x.getQuantity() / 100).sum();
    }

    private int getTotalFats(List<DailyRecordEntry> entries) {
        return Optional.ofNullable(entries).isEmpty() ?
                0 : entries.stream().mapToInt(x -> x.getFood().getFats() * x.getQuantity() / 100).sum();
    }

    private int getTotalProteins(List<DailyRecordEntry> entries) {
        return Optional.ofNullable(entries).isEmpty() ?
                0 : entries.stream().mapToInt(x -> x.getFood().getProteins() * x.getQuantity() / 100).sum();
    }

    private int getTotalCarbs(List<DailyRecordEntry> entries) {
        return Optional.ofNullable(entries).isEmpty() ?
                0 : entries.stream().mapToInt(x -> x.getFood().getCarbohydrates() * x.getQuantity() / 100).sum();
    }
}
