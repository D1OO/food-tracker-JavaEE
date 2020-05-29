package net.shvdy.nutrition_tracker.dto;

import net.shvdy.nutrition_tracker.model.entity.Role;

import java.util.List;

public class UserDTO {
    private Long userId;
    private String username;
    private Role role;
    private String firstNameLocalisation;
    private String lastName;
    private int dailyCaloriesNorm;
    private List<FoodDTO> userFood;

    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }

    public UserDTO(Long userId, String username, Role role, String firstNameLocalisation, String lastName, int dailyCaloriesNorm, List<FoodDTO> userFood) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.firstNameLocalisation = firstNameLocalisation;
        this.lastName = lastName;
        this.dailyCaloriesNorm = dailyCaloriesNorm;
        this.userFood = userFood;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstNameLocalisation() {
        return firstNameLocalisation;
    }

    public void setFirstNameLocalisation(String firstNameLocalisation) {
        this.firstNameLocalisation = firstNameLocalisation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDailyCaloriesNorm() {
        return dailyCaloriesNorm;
    }

    public void setDailyCaloriesNorm(int dailyCaloriesNorm) {
        this.dailyCaloriesNorm = dailyCaloriesNorm;
    }

    public List<FoodDTO> getUserFood() {
        return userFood;
    }

    public void setUserFood(List<FoodDTO> userFood) {
        this.userFood = userFood;
    }

    public static class UserDTOBuilder {
        private Long userId;
        private String username;
        private Role role;
        private String firstNameLocalisation;
        private String lastName;
        private int dailyCaloriesNorm;
        private List<FoodDTO> userFood;

        UserDTOBuilder() {
        }

        public UserDTOBuilder id(Long id) {
            this.userId = id;
            return this;
        }

        public UserDTOBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDTOBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserDTOBuilder firstNameLocalisation(String firstNameLocalisation) {
            this.firstNameLocalisation = firstNameLocalisation;
            return this;
        }

        public UserDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTOBuilder userFood(List<FoodDTO> userFoodDTO) {
            this.userFood = userFoodDTO;
            return this;
        }

        public UserDTOBuilder dailyCaloriesNorm(int dailyCaloriesNorm) {
            this.dailyCaloriesNorm = dailyCaloriesNorm;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this.userId, this.username, this.role, this.firstNameLocalisation, this.lastName,
                    this.dailyCaloriesNorm, this.userFood);
        }
    }
}
