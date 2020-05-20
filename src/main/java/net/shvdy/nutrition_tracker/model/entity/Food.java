package net.shvdy.nutrition_tracker.model.entity;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class Food {
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@NotNull
	private Long food_id;
//	@NotNull
	private String name;
//	@NotNull
	private int calories;
//	@NotNull
	private int proteins;
//	@NotNull
	private int fats;
//	@NotNull
	private int carbohydrates;

//	@ManyToOne
//	@JoinColumn(name = "profile_id")
	UserProfile userProfile;

	public Food() {
	}

	public Food(Long food_id, String name, int calories, int proteins, int fats, int carbohydrates, UserProfile userProfile) {
		this.food_id = food_id;
		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
		this.userProfile = userProfile;
	}

	public Long getFood_id() {
		return food_id;
	}

	public void setFood_id(Long food_id) {
		this.food_id = food_id;
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String toString() {
		return "DailyRecordEntry{" +
				"foodId=" + food_id +
				", name='" + name + '\'' +
				", calories=" + calories +
				", proteins=" + proteins +
				", fats=" + fats +
				", carbohydrates=" + carbohydrates +
//				", course='" + course + '\'' +
//				", room=" + room +
//				", students=" + students +
				'}';
	}
}
