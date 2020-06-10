package net.shvdy.nutrition_tracker.dto;

import net.shvdy.nutrition_tracker.model.entity.Role;

import java.util.List;

public class UserDTO {

    private Long userId;
    private String username;
    private Role role;
    private String firstName;
    private String lastName;
    private int dailyCaloriesNorm;
    private UserProfileDTO userProfileDTO;
    private List<FoodDTO> userFood;

    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }

    public UserDTO(Long userId, String username, Role role, String firstName, String lastName,
                   int dailyCaloriesNorm, UserProfileDTO userProfileDTO, List<FoodDTO> userFood) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dailyCaloriesNorm = dailyCaloriesNorm;
        this.userProfileDTO = userProfileDTO;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public UserProfileDTO getUserProfileDTO() {
        return userProfileDTO;
    }

    public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
        this.userProfileDTO = userProfileDTO;
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
        private String firstName;
        private String lastName;
        private int dailyCaloriesNorm;
        private UserProfileDTO userProfileDTO;
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

        public UserDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
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

        public UserDTOBuilder userProfileDTO(UserProfileDTO userProfileDTO) {
            this.userProfileDTO = userProfileDTO;
            return this;
        }

        public UserDTOBuilder dailyCaloriesNorm(int dailyCaloriesNorm) {
            this.dailyCaloriesNorm = dailyCaloriesNorm;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this.userId, this.username, this.role, this.firstName, this.lastName,
                    this.dailyCaloriesNorm, this.userProfileDTO, this.userFood);
        }
    }
}
