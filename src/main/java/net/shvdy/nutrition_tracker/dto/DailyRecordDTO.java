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

	public DailyRecordDTO(Long recordId, String recordDate, UserProfile userProfile, List<DailyRecordEntry> entries) {
		this.recordId = recordId;
		this.recordDate = recordDate;
		this.userProfile = userProfile;
		this.entries = entries;
	}

	public static DailyRecordDTOBuilder builder(){
		return new DailyRecordDTOBuilder();
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<DailyRecordEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<DailyRecordEntry> entries) {
		this.entries = entries;
	}




	//getPercentage
	//getTotalCalories
	//dateStringAsDayOfweek

	public static class DailyRecordDTOBuilder {

		private Long recordId;
		private String recordDate;
		UserProfile userProfile;
		private List<DailyRecordEntry> entries;

		DailyRecordDTOBuilder() {
		}

		public DailyRecordDTOBuilder recordId(Long recordId) {
			this.recordId = recordId;
			return this;
		}

		public DailyRecordDTOBuilder recordDate(String recordDate) {
			this.recordDate = recordDate;
			return this;
		}

		public DailyRecordDTOBuilder userProfile(UserProfile userProfile) {
			this.userProfile = userProfile;
			return this;
		}

		public DailyRecordDTOBuilder entries(List<DailyRecordEntry> entries) {
			this.entries = entries;
			return this;
		}

		public DailyRecordDTO build() {
			return new DailyRecordDTO(this.recordId, this.recordDate, this.userProfile, this.entries);
		}
	}
}
