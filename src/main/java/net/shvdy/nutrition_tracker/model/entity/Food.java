package net.shvdy.nutrition_tracker.model.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class Food {
	private Long foodId;
	private String name;
	private int calories;
	private int proteins;
	private int fats;
	private int carbohydrates;

	public Food(Long foodId, String name, int calories, int proteins, int fats, int carbohydrates) {
		this.foodId = foodId;
		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public static FoodBuilder builder() {
		return new FoodBuilder();
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

	@Override
	public String toString() {
		//Jackson (Java object to JSON String mapping)
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	public static class FoodBuilder {
		private Long foodId;
		private String name;
		private int calories;
		private int proteins;
		private int fats;
		private int carbohydrates;

		FoodBuilder() {
		}

		public FoodBuilder food_id(Long food_id) {
			this.foodId = food_id;
			return this;
		}

		public FoodBuilder name(String name) {
			this.name = name;
			return this;
		}

		public FoodBuilder calories(int calories) {
			this.calories = calories;
			return this;
		}

		public FoodBuilder proteins(int proteins) {
			this.proteins = proteins;
			return this;
		}

		public FoodBuilder fats(int fats) {
			this.fats = fats;
			return this;
		}

		public FoodBuilder carbohydrates(int carbohydrates) {
			this.carbohydrates = carbohydrates;
			return this;
		}

		public Food build() {
			return new Food(this.foodId, this.name, this.calories, this.proteins, this.fats,
					this.carbohydrates);
		}
	}
}
