package net.shvdy.nutrition_tracker.dto;

import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.util.List;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordDTO {

	private Long recordId;
	private String recordDate;
	UserProfile userProfile;
	private List<DailyRecordEntry> entries;
}
