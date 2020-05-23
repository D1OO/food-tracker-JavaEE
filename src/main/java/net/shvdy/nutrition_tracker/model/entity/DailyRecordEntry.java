package net.shvdy.nutrition_tracker.model.entity;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class DailyRecordEntry implements Entity {

	private Long entryId;
	private int quantity;
	private Food food;

	public DailyRecordEntry() {
	}

	public DailyRecordEntry(Long entryId, int quantity, Food food) {
		this.entryId = entryId;
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
		private Food food;
		private int quantity;

		DailyRecordEntryBuilder() {
		}

		public DailyRecordEntryBuilder entryId(Long entryId) {
			this.entryId = entryId;
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
			return new DailyRecordEntry(this.entryId, this.quantity, this.food);
		}

	}

}