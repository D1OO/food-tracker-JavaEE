package net.shvdy.nutrition_tracker.dto;

import net.shvdy.nutrition_tracker.model.entity.Food;
import net.shvdy.nutrition_tracker.model.entity.Role;

import java.util.List;

public class UserDTO {

    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private List<FoodDTO> userFoodDTO;

    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }

    public UserDTO(Long id, Role role, String firstName, String lastName, List<FoodDTO> userFoodDTO) {
        this.id = id;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userFoodDTO = userFoodDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<FoodDTO> getUserFoodDTO() {
        return userFoodDTO;
    }

    public void setUserFoodDTO(List<FoodDTO> userFoodDTO) {
        this.userFoodDTO = userFoodDTO;
    }

    public static class UserDTOBuilder {

        private Long id;
        private Role role;
        private String firstName;
        private String lastName;
        private List<FoodDTO> userFoodDTO;


        UserDTOBuilder() {
        }

        public UserDTOBuilder id(Long id) {
            this.id = id;
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

        public UserDTOBuilder userFoodDTO(List<FoodDTO> userFoodDTO) {
            this.userFoodDTO = userFoodDTO;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this.id, this.role, this.firstName, this.lastName, this.userFoodDTO);
        }
    }
}
