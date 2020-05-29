package net.shvdy.nutrition_tracker.model.entity;

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
    private Long userProfileId;
    private int dailyCaloriesNorm;
    private List<DailyRecordEntry> entries;

    public DailyRecord(Long recordId, String recordDate, int dailyCaloriesNorm, Long userProfileId,
                       List<DailyRecordEntry> entries) {
        this.recordId = recordId;
        this.recordDate = recordDate;
        this.dailyCaloriesNorm = dailyCaloriesNorm;
        this.userProfileId = userProfileId;
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

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
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
        private Long userProfileId;
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

        public DailyRecordBuilder userProfileId(Long userProfileId) {
            this.userProfileId = userProfileId;
            return this;
        }

        public DailyRecordBuilder entries(List<DailyRecordEntry> entries) {
            this.entries = entries;
            return this;
        }

        public DailyRecord build() {
            return new DailyRecord(this.recordId, this.recordDate,
                    this.dailyCaloriesNorm, this.userProfileId, this.entries);
        }
    }
}
