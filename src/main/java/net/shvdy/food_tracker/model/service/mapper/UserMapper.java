package net.shvdy.food_tracker.model.service.mapper;

import net.shvdy.food_tracker.dto.UserDTO;
import net.shvdy.food_tracker.model.entity.User;

public interface UserMapper {

    UserDTO entityToDTO(User user);
}
