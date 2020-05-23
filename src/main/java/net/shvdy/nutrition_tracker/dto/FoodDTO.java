package net.shvdy.nutrition_tracker.dto;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodDTO {

	private String name;
	private int calories;
	private int prot;
	private int fats;
	private int carbohydrates;

	public FoodDTO(String name, int calories, int prot, int fats, int carbohydrates) {
		this.name = name;
		this.calories = calories;
		this.prot = prot;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public static FoodDTOBuilder builder() {
		return new FoodDTOBuilder();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getProt() {
		return prot;
	}

	public void setProt(int prot) {
		this.prot = prot;
	}

	public int getFats() {
		return fats;
	}

	public void setFats(int fats) {
		this.fats = fats;
	}

	public int getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}


	public static final class FoodDTOBuilder {
		private String name;
		private int calories;
		private int prot;
		private int fats;
		private int carbohydrates;

		private FoodDTOBuilder() {
		}

		public FoodDTOBuilder name(String name) {
			this.name = name;
			return this;
		}

		public FoodDTOBuilder calories(int calories) {
			this.calories = calories;
			return this;
		}

		public FoodDTOBuilder prot(int prot) {
			this.prot = prot;
			return this;
		}

		public FoodDTOBuilder fats(int fats) {
			this.fats = fats;
			return this;
		}

		public FoodDTOBuilder carbohydrates(int carbohydrates) {
			this.carbohydrates = carbohydrates;
			return this;
		}

		public FoodDTO build() {
			return new FoodDTO(name, calories, prot, fats, carbohydrates);
		}
	}
}
