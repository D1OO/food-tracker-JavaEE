package net.shvdy.nutrition_tracker.model.entity;

import java.util.List;

/**
 * 20.03.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class DailyRecord {

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@Column(name = "record_id")
	private Long recordId;

//	@NotNull
//	@Column(name = "record_date")
	private String recordDate;

//	@ManyToOne
//	@JoinColumn(name = "profile_id")
	UserProfile userProfile;
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dailyRecord", cascade = CascadeType.ALL)
	private List<DailyRecordEntry> entries;

	public DailyRecord() {
	}

	public DailyRecord(Long recordId, String recordDate, UserProfile userProfile, List<DailyRecordEntry> entries) {
		this.recordId = recordId;
		this.recordDate = recordDate;
		this.userProfile = userProfile;
		this.entries = entries;
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

	public String toString() {
		return "DailyRecord{" +
				"recordId=" + recordId +
				", recordDate='" + recordDate + '\'' +
//				", course='" + course + '\'' +
//				", room=" + room +
				//", students=" + students +
				'}';
	}
}
