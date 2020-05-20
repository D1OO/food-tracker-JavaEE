package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.Entity;
import net.shvdy.nutrition_tracker.model.entity.User;

public class UserEntityMapper {

	public UserDTO entityToDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.role(user.getRole())
				.firstName(user.getUserProfile().getFirstName())
				.lastName(user.getUserProfile().getLastName())
				.build();
	}

}
