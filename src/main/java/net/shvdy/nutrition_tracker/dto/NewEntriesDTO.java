package net.shvdy.nutrition_tracker.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class NewEntriesDTO {
	private Long recordId;
	private Long profileId;
	private String recordDate;
	private int currentDailyCaloriesNorm;
	//    private String newEntriesContainerJSON;
	private List<DailyRecordEntryDTO> entries;

	public NewEntriesDTO() {
	}

	public NewEntriesDTO(Long recordId, Long profileId, String recordDate, int currentDailyCaloriesNorm,
						 List<DailyRecordEntryDTO> entries) {
		this.recordId = recordId;
		this.profileId = profileId;
		this.recordDate = recordDate;
		this.currentDailyCaloriesNorm = currentDailyCaloriesNorm;
		this.entries = entries;
	}

	public static NewEntriesDTOBuilder builder() {
		return new NewEntriesDTOBuilder();
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public int getCurrentDailyCaloriesNorm() {
		return currentDailyCaloriesNorm;
	}

	public void setCurrentDailyCaloriesNorm(int currentDailyCaloriesNorm) {
		this.currentDailyCaloriesNorm = currentDailyCaloriesNorm;
	}

	public List<DailyRecordEntryDTO> getEntries() {
		return entries;
	}

	public void setEntries(List<DailyRecordEntryDTO> entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		//Jackson (Java object to JSON String mapping)
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	public static final class NewEntriesDTOBuilder {
		private Long recordId;
		private Long profileId;
		private String recordDate;
		private int currentDailyCaloriesNorm;
		private List<DailyRecordEntryDTO> entries;

		private NewEntriesDTOBuilder() {
		}

		public NewEntriesDTOBuilder recordId(Long recordId) {
			this.recordId = recordId;
			return this;
		}

		public NewEntriesDTOBuilder profileId(Long profileId) {
			this.profileId = profileId;
			return this;
		}

		public NewEntriesDTOBuilder recordDate(String recordDate) {
			this.recordDate = recordDate;
			return this;
		}

		public NewEntriesDTOBuilder entries(List<DailyRecordEntryDTO> entries) {
			this.entries = entries;
			return this;
		}

		public NewEntriesDTOBuilder currentDailyCaloriesNorm(int currentDailyCaloriesNorm) {
			this.currentDailyCaloriesNorm = currentDailyCaloriesNorm;
			return this;
		}

		public NewEntriesDTO build() {
			NewEntriesDTO newEntriesDTO = new NewEntriesDTO();
			newEntriesDTO.setRecordId(recordId);
			newEntriesDTO.setProfileId(profileId);
			newEntriesDTO.setRecordDate(recordDate);
			newEntriesDTO.setCurrentDailyCaloriesNorm(currentDailyCaloriesNorm);
			newEntriesDTO.setEntries(entries);
			return newEntriesDTO;
		}
	}
}
