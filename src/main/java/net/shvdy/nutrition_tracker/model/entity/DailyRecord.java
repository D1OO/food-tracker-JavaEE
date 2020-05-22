package net.shvdy.nutrition_tracker.model.entity;

import java.util.List;

/**
 * 20.03.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class DailyRecord implements Entity {

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@Column(name = "record_id")
	private Long recordId;

//	@NotNull
//	@Column(name = "record_date")
	private String recordDate;
	private int dailyCaloriesNorm;

//	@ManyToOne
//	@JoinColumn(name = "profile_id")
	UserProfile userProfile;
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dailyRecord", cascade = CascadeType.ALL)
	private List<DailyRecordEntry> entries;

	public DailyRecord() {
	}

	public DailyRecord(Long recordId, String recordDate, int dailyCaloriesNorm,
					   UserProfile userProfile, List<DailyRecordEntry> entries) {
		this.recordId = recordId;
		this.recordDate = recordDate;
		this.dailyCaloriesNorm = dailyCaloriesNorm;
		this.userProfile = userProfile;
		this.entries = entries;
	}

	public static DailyRecordBuilder builder(){
		return new DailyRecordBuilder();
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

	public String toString() {
		return "DailyRecord{" +
				"recordId=" + recordId +
				", recordDate='" + recordDate + '\'' +
//				", course='" + course + '\'' +
//				", room=" + room +
				//", students=" + students +
				'}';
	}

	public static class DailyRecordBuilder {

		private Long recordId;
		private String recordDate;
		private int dailyCaloriesNorm;
		UserProfile userProfile;
		private List<DailyRecordEntry> entries;

		DailyRecordBuilder(){
		}

		public DailyRecordBuilder recordId(Long recordId) {
			this.recordId = recordId;
			return this;
		}

		public DailyRecordBuilder recordDate(String recordDate) {
			this.recordDate = recordDate;
			return this;
		}

		public DailyRecordBuilder dailyCaloriesNorm(int dailyCaloriesNorm) {
			this.dailyCaloriesNorm = dailyCaloriesNorm;
			return this;
		}

		public DailyRecordBuilder userProfile(UserProfile userProfile) {
			this.userProfile = userProfile;
			return this;
		}

		public DailyRecordBuilder entries(List<DailyRecordEntry> entries) {
			this.entries = entries;
			return this;
		}

		public DailyRecord build() {
			return new DailyRecord(this.recordId, this.recordDate,
					this.dailyCaloriesNorm, this.userProfile, this.entries);
		}
	}
}
