package net.shvdy.food_tracker.model.service.mapper;

import net.shvdy.food_tracker.dto.UserDTO;
import net.shvdy.food_tracker.model.entity.User;

public class UserMapperImpl implements UserMapper {

    @Override
	public UserDTO entityToDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.role(user.getRole())
				.firstName(user.getUserProfile().getFirstName())
				.lastName(user.getUserProfile().getLastName())
				.build();
	}
}
