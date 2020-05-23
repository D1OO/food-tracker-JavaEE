package net.shvdy.nutrition_tracker.dto;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodDTO {

	private Long foodId;
	private String name;
	private int calories;
	private int proteins;
	private int fats;
	private int carbohydrates;
	private Long profileId;

	public FoodDTO(Long foodId, String name, int calories, int proteins, int fats, int carbohydrates, Long profileId) {
		this.foodId = foodId;
		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
		this.profileId = profileId;
	}

	public static FoodDTOBuilder builder() {
		return new FoodDTOBuilder();
	}

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
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

	public int getProteins() {
		return proteins;
	}

	public void setProteins(int proteins) {
		this.proteins = proteins;
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

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public static final class FoodDTOBuilder {
		private Long foodId;
		private String name;
		private int calories;
		private int proteins;
		private int fats;
		private int carbohydrates;
		private Long profileId;

		private FoodDTOBuilder() {
		}

		public FoodDTOBuilder foodId(Long foodId) {
			this.foodId = foodId;
			return this;
		}

		public FoodDTOBuilder name(String name) {
			this.name = name;
			return this;
		}

		public FoodDTOBuilder calories(int calories) {
			this.calories = calories;
			return this;
		}

		public FoodDTOBuilder proteins(int proteins) {
			this.proteins = proteins;
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

		public FoodDTOBuilder profileId(Long profileId) {
			this.profileId = profileId;
			return this;
		}

		public FoodDTO build() {
			return new FoodDTO(foodId, name, calories, proteins, fats, carbohydrates, profileId);
		}
	}
}
