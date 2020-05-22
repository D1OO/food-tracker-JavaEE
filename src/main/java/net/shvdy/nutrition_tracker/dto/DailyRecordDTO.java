package net.shvdy.nutrition_tracker.dto;

import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;

import java.time.LocalDate;
import java.time.format.TextStyle;
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
	private int dailyCaloriesNorm;
	//	UserProfile userProfile;
	private List<DailyRecordEntryDTO> entries;
	private int totalCalories;
	private int percentage;
	private int totalCarbs;
	private int totalProt;
	private int totalFats;
	private String dateHeader;

	public DailyRecordDTO(Long recordId, String recordDate, int dailyCaloriesNorm,
						  List<DailyRecordEntryDTO> entries, int totalCalories, int percentage, int totalCarbs,
						  int totalProt, int totalFats, String dateHeader) {
		this.recordId = recordId;
		this.recordDate = recordDate;
		this.dailyCaloriesNorm = dailyCaloriesNorm;
		this.entries = entries;
		this.totalCalories = totalCalories;
		this.percentage = percentage;
		this.totalCarbs = totalCarbs;
		this.totalProt = totalProt;
		this.totalFats = totalFats;
		this.dateHeader = dateHeader;
	}

	public static DailyRecordDTOBuilder builder() {
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

	public int getDailyCaloriesNorm() {
		return dailyCaloriesNorm;
	}

	public void setDailyCaloriesNorm(int dailyCaloriesNorm) {
		this.dailyCaloriesNorm = dailyCaloriesNorm;
	}

//	public UserProfile getUserProfile() {
//		return userProfile;
//	}
//
//	public void setUserProfile(UserProfile userProfile) {
//		this.userProfile = userProfile;
//	}

	public List<DailyRecordEntryDTO> getEntries() {
		return entries;
	}

	public void setEntries(List<DailyRecordEntryDTO> entries) {
		this.entries = entries;
	}

	public int getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(int totalCalories) {
		this.totalCalories = totalCalories;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(int totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	public int getTotalProt() {
		return totalProt;
	}

	public void setTotalProt(int totalProt) {
		this.totalProt = totalProt;
	}

	public int getTotalFats() {
		return totalFats;
	}

	public void setTotalFats(int totalFats) {
		this.totalFats = totalFats;
	}

	public String getDateHeader() {
		return dateHeader;

	}

	public void setDateHeader(String dateHeader) {
		this.dateHeader = dateHeader;
	}

	//dateStringAsDayOfweek

	public static class DailyRecordDTOBuilder {

		private Long recordId;
		private String recordDate;
		private int dailyCaloriesNorm;
		//		UserProfile userProfile;
		private List<DailyRecordEntryDTO> entries;
		private int totalCalories;
		private int percentage;
		private int totalCarbs;
		private int totalProt;
		private int totalFats;
		private String dateHeader;

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

		public DailyRecordDTOBuilder dailyCaloriesNorm(int dailyCaloriesNorm) {
			this.dailyCaloriesNorm = dailyCaloriesNorm;
			return this;
		}

//		public DailyRecordDTOBuilder userProfile(UserProfile userProfile) {
//			this.userProfile = userProfile;
//			return this;
//		}

		public DailyRecordDTOBuilder entries(List<DailyRecordEntryDTO> entries) {
			this.entries = entries;
			return this;
		}

		public DailyRecordDTOBuilder totalCalories(int totalCalories) {
			this.totalCalories = totalCalories;
			return this;
		}

		public DailyRecordDTOBuilder percentage(int percentage) {
			this.percentage = percentage;
			return this;
		}

		public DailyRecordDTOBuilder totalCarbs(int totalCarbs) {
			this.totalCarbs = totalCarbs;
			return this;
		}

		public DailyRecordDTOBuilder totalProt(int totalProt) {
			this.totalProt = totalProt;
			return this;
		}

		public DailyRecordDTOBuilder totalFats(int totalFats) {
			this.totalFats = totalFats;
			return this;
		}

		public DailyRecordDTOBuilder dateHeader(String dateHeader) {
			this.dateHeader = dateHeader;
			return this;
		}

		public DailyRecordDTO build() {
			return new DailyRecordDTO(this.recordId, this.recordDate, this.dailyCaloriesNorm, this.entries,
					this.totalCalories, this.percentage, this.totalCarbs, this.totalProt, this.totalFats, this.dateHeader);
		}
	}
}
