package net.shvdy.nutrition_tracker.model.entity;


import java.util.List;

public class UserProfile {
    private Long profileId;
    private String firstName;
    private String lastName;
    private Lifestyle lifestyle;
    private int age;
    private int height;
    private int weight;
    private List<Food> userFood;

    public UserProfile(Long profileId, String lastName, String firstName, Lifestyle lifestyle,
                       int age, int height, int weight, List<Food> userFood) {
        this.profileId = profileId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.lifestyle = lifestyle;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.userFood = userFood;
    }

    public static UserProfileBuilder builder() {
        return new UserProfileBuilder();
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Food> getUserFood() {
        return userFood;
    }

    public void setUserFood(List<Food> userFood) {
        this.userFood = userFood;
    }

    public enum Lifestyle {
        SEDENTARY(1.2f),
        LIGHTLY_ACTIVE(1.375f),
        MODERATELY_ACTIVE(1.55f),
        VERY_ACTIVE(1.725f),
        EXTRA_ACTIVE(1.9f);

        float factor;
        private String propertiesKey;

        Lifestyle(float factor) {
            this.factor = factor;
        }

        public float getFactor() {
            return factor;
        }

        public String getPropertiesKey() {
            return "enum.lifestyle." + this.toString().toLowerCase();
        }
    }

    public static class UserProfileBuilder {
        private Long profileId;
        private String firstName;
        private String lastName;
        private Lifestyle lifestyle;
        private int age;
        private int height;
        private int weight;
        private List<Food> userFood;

        private UserProfileBuilder() {
        }

        public UserProfileBuilder profileId(Long profileId) {
            this.profileId = profileId;
            return this;
        }

        public UserProfileBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserProfileBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserProfileBuilder lifestyle(Lifestyle lifestyle) {
            this.lifestyle = lifestyle;
            return this;
        }

        public UserProfileBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserProfileBuilder height(int height) {
            this.height = height;
            return this;
        }

        public UserProfileBuilder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public UserProfileBuilder userFood(List<Food> userFood) {
            this.userFood = userFood;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this.profileId, this.lastName, this.firstName,
                    this.lifestyle, this.age, this.height, this.weight, this.userFood);
        }
    }

}


