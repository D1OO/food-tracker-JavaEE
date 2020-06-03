package net.shvdy.nutrition_tracker.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 20.03.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class DailyRecord {
    private Long recordId;
    private String recordDate;
    private Long profileId;
    private int dailyCaloriesNorm;
    private List<DailyRecordEntry> entries = new ArrayList<>();

    public DailyRecord() {
    }

    public DailyRecord(Long recordId, String recordDate, int dailyCaloriesNorm, Long profileId,
                       List<DailyRecordEntry> entries) {
        this.recordId = recordId;
        this.recordDate = recordDate;
        this.dailyCaloriesNorm = dailyCaloriesNorm;
        this.profileId = profileId;
        this.entries = entries;
    }

    public static DailyRecordBuilder builder() {
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

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<DailyRecordEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<DailyRecordEntry> entries) {
        this.entries = entries;
    }

    public static class DailyRecordBuilder {

        private Long recordId;
        private String recordDate;
        private int dailyCaloriesNorm;
        private Long profileId;
        private List<DailyRecordEntry> entries;

        DailyRecordBuilder() {
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

        public DailyRecordBuilder profileId(Long profileId) {
            this.profileId = profileId;
            return this;
        }

        public DailyRecordBuilder entries(List<DailyRecordEntry> entries) {
            this.entries = entries;
            return this;
        }

        public DailyRecord build() {
            return new DailyRecord(this.recordId, this.recordDate,
                    this.dailyCaloriesNorm, this.profileId, this.entries);
        }
    }
}
