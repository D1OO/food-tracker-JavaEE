package net.shvdy.nutrition_tracker.model.entity;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class DailyRecordEntry implements Entity {

	//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@Column(name = "entry_id")
	private Long entryId;

	//	@NotNull
//	@ManyToOne
//	@JoinColumn(name = "food_id")
	private Food food;

	//	@NotNull
	private int quantity;

	//	@ManyToOne(cascade = CascadeType.ALL, optional = false)
//	@JoinColumn(name = "record_id", insertable = true)
	private DailyRecord dailyRecord;

	public DailyRecordEntry() {
	}

	public DailyRecordEntry(Long entryId, int quantity, Food food, DailyRecord dailyRecord) {
		this.entryId = entryId;
		this.food = food;
		this.quantity = quantity;
		this.dailyRecord = dailyRecord;
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

	public DailyRecord getDailyRecord() {
		return dailyRecord;
	}

	public void setDailyRecord(DailyRecord dailyRecord) {
		this.dailyRecord = dailyRecord;
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

	public static class DailyRecordEntryBuilder {

		private Long entryId;
		private Food food;
		private int quantity;
		private DailyRecord dailyRecord;

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

		public DailyRecordEntryBuilder dailyRecord(DailyRecord dailyRecord) {
			this.dailyRecord = dailyRecord;
			return this;
		}

		public DailyRecordEntry build() {
			return new DailyRecordEntry(this.entryId, this.quantity, this.food, this.dailyRecord);
		}

	}

}