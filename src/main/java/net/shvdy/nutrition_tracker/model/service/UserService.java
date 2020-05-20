package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.LoginDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.User;

import java.sql.SQLException;

public interface UserService {

    void save(User createUserDto) throws SQLException;

    User findByUsername(String username) throws SQLException;

    UserDTO findByLoginDTO(LoginDTO loginDto) throws SQLException;
}
