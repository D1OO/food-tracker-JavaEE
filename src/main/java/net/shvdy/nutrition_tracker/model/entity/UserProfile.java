package net.shvdy.nutrition_tracker.model.entity;


import java.util.List;

public class UserProfile {
    User user;
    private Long profileId;
    private String firstNameLocalisation;
    private String firstNameEN;
    private String firstNameRU;
    private String lastName;
    private Lifestyle lifestyle;
    private int age;
    private int height;
    private int weight;
    private List<Food> userFood;

    public UserProfile(User user, Long profileId, String lastName, String firstNameLocalisation, String firstNameEN,
                       String firstNameRU, Lifestyle lifestyle, int age, int height, int weight, List<Food> userFood) {
        this.user = user;
        this.profileId = profileId;
        this.lastName = lastName;
        this.firstNameLocalisation = firstNameLocalisation;
        this.firstNameEN = firstNameEN;
        this.firstNameRU = firstNameRU;
        this.lifestyle = lifestyle;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.userFood = userFood;
    }

    public static UserProfileBuilder builder() {
        return new UserProfileBuilder();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getFirstNameLocalisation() {
        return firstNameLocalisation;
    }

    public void setFirstNameLocalisation(String firstNameLocalisation) {
        this.firstNameLocalisation = firstNameLocalisation;
    }

    public String getFirstNameEN() {
        return firstNameEN;
    }

    public void setFirstNameEN(String firstNameEN) {
        this.firstNameEN = firstNameEN;
    }

    public String getFirstNameRU() {
        return firstNameRU;
    }

    public void setFirstNameRU(String firstNameRU) {
        this.firstNameRU = firstNameRU;
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

        Lifestyle(float factor) {
            this.factor = factor;
        }

        public float getFactor() {
            return factor;
        }
    }

    public static class UserProfileBuilder {
        User user;
        private Long profileId;
        private String firstNameLocalisation;
        private String firstNameEN;
        private String firstNameRU;
        private String lastName;
        private Lifestyle lifestyle;
        private int age;
        private int height;
        private int weight;
        private List<Food> userFood;

        UserProfileBuilder() {
        }

        public UserProfileBuilder user(User user) {
            this.user = user;
            return this;
        }

        public UserProfileBuilder profileId(Long profileId) {
            this.profileId = profileId;
            return this;
        }

        public UserProfileBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserProfileBuilder firstNameLocalised(String firstNameLocalised) {
            this.firstNameLocalisation = firstNameLocalised;
            return this;
        }

        public UserProfileBuilder firstNameEN(String firstNameEN) {
            this.firstNameEN = firstNameEN;
            return this;
        }

        public UserProfileBuilder firstNameRU(String firstNameRU) {
            this.firstNameRU = firstNameRU;
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
            return new UserProfile(this.user, this.profileId, this.lastName, this.firstNameLocalisation,
                    this.firstNameEN, this.firstNameRU, this.lifestyle, this.age, this.height, this.weight, this.userFood);
        }
    }

}


