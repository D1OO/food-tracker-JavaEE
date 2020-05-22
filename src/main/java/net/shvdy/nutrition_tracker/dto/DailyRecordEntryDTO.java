package net.shvdy.nutrition_tracker.dto;

import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;
import net.shvdy.nutrition_tracker.model.entity.Entity;
import net.shvdy.nutrition_tracker.model.entity.Food;

/**
 * 21.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordEntryDTO {

	private Long entryId;
	private Food food;
	private int quantity;
	private DailyRecord dailyRecord;

	private int entryCalories;
	private int entryCarbs;
	private int entryFats;
	private int entryProt;

	public DailyRecordEntryDTO() {
	}

	public DailyRecordEntryDTO(Long entryId, Food food, int quantity, DailyRecord dailyRecord,
							   int entryCalories, int entryCarbs, int entryFats, int entryProt) {
		this.entryId = entryId;
		this.food = food;
		this.quantity = quantity;
		this.dailyRecord = dailyRecord;
		this.entryCalories = entryCalories;
		this.entryCarbs = entryCarbs;
		this.entryFats = entryFats;
		this.entryProt = entryProt;
	}

	public static DailyRecordEntryDTOBuilder builder() {
		return new DailyRecordEntryDTOBuilder();
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

	public DailyRecord getDailyRecord() {
		return dailyRecord;
	}

	public void setDailyRecord(DailyRecord dailyRecord) {
		this.dailyRecord = dailyRecord;
	}

	public int getEntryCalories() {
		return entryCalories;
	}

	public void setEntryCalories(int entryCalories) {
		this.entryCalories = entryCalories;
	}

	public int getEntryCarbs() {
		return entryCarbs;
	}

	public void setEntryCarbs(int entryCarbs) {
		this.entryCarbs = entryCarbs;
	}

	public int getEntryFats() {
		return entryFats;
	}

	public void setEntryFats(int entryFats) {
		this.entryFats = entryFats;
	}

	public int getEntryProt() {
		return entryProt;
	}

	public void setEntryProt(int entryProt) {
		this.entryProt = entryProt;
	}

	public String toString() {
		return "DailyRecordEntry{" +
				"recordId=" + entryId +
				", food='" + food + '\'' +
				", quantity='" + quantity + '\'' +
//				", course='" + course + '\'' +
//				", room=" + room +
				//", students=" + students +
				'}';
	}

	public static class DailyRecordEntryDTOBuilder {

		private Long entryId;
		private Food food;
		private int quantity;
		private DailyRecord dailyRecord;
		private int entryCalories;
		private int entryCarbs;
		private int entryFats;
		private int entryProt;

		private DailyRecordEntryDTOBuilder() {
		}

		public DailyRecordEntryDTOBuilder entryId(Long entryId) {
			this.entryId = entryId;
			return this;
		}

		public DailyRecordEntryDTOBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public DailyRecordEntryDTOBuilder food(Food food) {
			this.food = food;
			return this;
		}

		public DailyRecordEntryDTOBuilder dailyRecord(DailyRecord dailyRecord) {
			this.dailyRecord = dailyRecord;
			return this;
		}

		public DailyRecordEntryDTOBuilder entryCalories(int entryCalories) {
			this.entryCalories = entryCalories;
			return this;
		}

		public DailyRecordEntryDTOBuilder entryCarbs(int entryCarbs) {
			this.entryCarbs = entryCarbs;
			return this;
		}

		public DailyRecordEntryDTOBuilder entryFats(int entryFats) {
			this.entryFats = entryFats;
			return this;
		}

		public DailyRecordEntryDTOBuilder entryProt(int entryProt) {
			this.entryProt = entryProt;
			return this;
		}

		public DailyRecordEntryDTO build() {
			return new DailyRecordEntryDTO(this.entryId, this.food, this.quantity,  this.dailyRecord, this.entryCalories,
					this.entryCarbs, this.entryFats, this.entryProt);
		}

	}

}