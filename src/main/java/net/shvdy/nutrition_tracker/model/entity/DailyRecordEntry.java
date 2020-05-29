package net.shvdy.nutrition_tracker.model.entity;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class DailyRecordEntry {
    private Long entryId;
    private Long recordId;
    private int quantity;
    private Food food;

    public DailyRecordEntry(Long entryId, Long recordId, int quantity, Food food) {
        this.entryId = entryId;
        this.recordId = recordId;
        this.food = food;
        this.quantity = quantity;
    }

    public static DailyRecordEntryBuilder builder() {
        return new DailyRecordEntryBuilder();
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static class DailyRecordEntryBuilder {

        private Long entryId;
        private Long recordId;
        private Food food;
        private int quantity;

        DailyRecordEntryBuilder() {
        }

        public DailyRecordEntryBuilder entryId(Long entryId) {
            this.entryId = entryId;
            return this;
        }

        public DailyRecordEntryBuilder recordId(Long recordId) {
            this.recordId = recordId;
            return this;
        }

        public DailyRecordEntryBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public DailyRecordEntryBuilder food(Food food) {
            this.food = food;
            return this;
        }

        public DailyRecordEntry build() {
            return new DailyRecordEntry(this.entryId, this.recordId, this.quantity, this.food);
        }

    }

}